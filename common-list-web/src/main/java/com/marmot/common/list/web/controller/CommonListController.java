package com.marmot.common.list.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marmot.common.list.sdk.common.PageData;
import com.marmot.common.list.sdk.common.ResponseResult;
import com.marmot.common.list.sdk.dto.CommonListDto;
import com.marmot.common.list.sdk.enums.ErrorEnum;
import com.marmot.common.list.sdk.enums.fields.CommonListFieldEnum;
import com.marmot.common.list.sdk.query.QueryCond;
import com.marmot.common.list.sdk.request.*;
import com.marmot.common.list.sdk.utils.ResponseResultUtil;
import com.marmot.common.list.web.aggService.CommonListAggService;
import com.marmot.common.list.web.constant.ApiPath;
import com.marmot.common.list.web.domain.entity.CommonList;
import com.marmot.common.list.web.domain.entity.CommonListType;
import com.marmot.common.list.web.service.CommonListService;
import com.marmot.common.list.web.service.CommonListTypeService;
import com.marmot.common.list.web.utils.NumberUtil;
import com.marmot.common.list.web.utils.PageDataUtil;
import com.marmot.common.list.web.utils.QueryParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/25
 * @Desc: 名单类型
 */
@Slf4j
@RestController
@RequestMapping(value = ApiPath.LIST_PREFIX)
public class CommonListController {

    @Autowired
    private CommonListService listService;

    @Autowired
    private CommonListAggService listAggService;

    @Autowired
    private CommonListTypeService listTypeService;

    /**
     * @Desc 分页获取名单
     * @Param
     * @return
     **/
    @PostMapping("/page")
    public ResponseResult<PageData<CommonListDto>> page(@Validated @RequestBody CommonListPageReq req) {
        int pageNo = NumberUtil.isPositive(req.getPageNo()) ? req.getPageNo():1;
        int pageSize = NumberUtil.isPositive(req.getPageSize()) ? req.getPageSize():10;
        req.setPageNo(pageNo);
        req.setPageSize(pageSize);

        QueryCond cond = new QueryCond();
        String errMsg = QueryParamUtil.transQueryParam(req.getQueryParam(),cond, CommonListFieldEnum.allFields());
        if (StringUtils.isNotBlank(errMsg)){
            log.warn("query param error, req={}, errMsg={}", JSONUtil.toJsonStr(req), errMsg);
            return ResponseResultUtil.fail(ErrorEnum.PARAM_ERROR, errMsg);
        }

        CommonListType type = listTypeService.getByTypeId(req.getTypeId());
        if (type == null){
            log.warn("can not find list type, req={}", req);
            return ResponseResultUtil.fail(ErrorEnum.PARAM_ERROR,"Param [typeId] is invalid");
        }

        Page<CommonList> page = listService.commonListPage(req.getSysCode(), req.getTypeId(), cond, req.getPageNo(),req.getPageSize());
        PageData<CommonListDto> pageData = PageDataUtil.transWithCopy(page, CommonListDto.class);
        return ResponseResultUtil.success(pageData);
    }

    /**
     * @Desc 添加/更新名单
     * @Param
     * @return
     **/
    @PostMapping(value = "/add")
    public ResponseResult add(@Validated @RequestBody CommonListAddReq req){
        CommonList commonList = BeanUtil.copyProperties(req, CommonList.class);
        commonList.setModifyTime(System.currentTimeMillis());
        CommonListType type = listTypeService.getByTypeId(req.getTypeId());
        if (type == null){
            log.warn("can not find list type, req={}", req);
            return ResponseResultUtil.fail(ErrorEnum.PARAM_ERROR,"Param [typeId] is invalid");
        }
        return listAggService.saveOrUpdate(commonList) ? ResponseResultUtil.success():ResponseResultUtil.fail();
    }


    /**
     * @Desc 添加/更新名单
     * @Param
     * @return
     **/
    @PostMapping(value = "/update")
    @Transactional
    public ResponseResult update(@Validated @RequestBody CommonListUpdateReq req){
        CommonList commonList = BeanUtil.copyProperties(req, CommonList.class,"sysCode","typeId","bizKey");
        commonList.setModifyTime(System.currentTimeMillis());
        //校验typeId是否一致
        CommonListType type = listTypeService.getByTypeId(req.getTypeId());
        if (type == null){
            log.warn("can not find list type, req={}", req);
            return ResponseResultUtil.fail(ErrorEnum.PARAM_ERROR,"Param [typeId] is invalid");
        }
        //查询旧list信息
        CommonList oldData = listService.getByListId(req.getId());
        if (oldData == null){
            log.warn("can not find list data, id={}", req.getId());
            return ResponseResultUtil.fail(ErrorEnum.LIST_NOT_EXIST);
        }
        //校验sysCode、typeId、bizKey是否与库中一致
        if (!oldData.getSysCode().equals(req.getSysCode()) || !oldData.getTypeId().equals(req.getTypeId()) || !oldData.getBizKey().equals(req.getBizKey())){
            log.warn("update list req data invalid, req={}, oldData={}", req,oldData);
            return ResponseResultUtil.fail(ErrorEnum.PARAM_ERROR, "[sysCode]|[typeId]|[bizKey] has some one dose not match with old data");
        }
        return listService.updateById(commonList) ? ResponseResultUtil.success():ResponseResultUtil.fail();
    }


    /**
     * @Desc 删除名单
     * @Param
     * @return
     **/
    @PostMapping(value = "/delete")
    public ResponseResult delete(@Validated @RequestBody CommonListDeleteReq req){
        CommonList commonList = listService.getCommonList(req.getSysCode(),req.getListId());
        if (commonList == null){
            return ResponseResultUtil.fail(ErrorEnum.LIST_NOT_EXIST);
        }

        listService.deleteByListId(commonList.getId());
        return ResponseResultUtil.success();
    }


    /**
     * @Desc 名单查询
     * @Param
     * @return
     **/
    @PostMapping(value = "/find")
    public ResponseResult<List<CommonListDto>> find(@Validated @RequestBody CommonListFindReq req){

        //解析查询参数
        QueryCond cond = new QueryCond();
        String errMsg = QueryParamUtil.transQueryParam(req.getQueryParam(),cond, CommonListFieldEnum.allFields());
        if (StringUtils.isNotBlank(errMsg)){
            log.warn("query param error, req={}, errMsg={}", JSONUtil.toJsonStr(req), errMsg);
            return ResponseResultUtil.fail(ErrorEnum.PARAM_ERROR, errMsg);
        }

        //数据查询
        List<CommonList> commonLists = listService.findCommonList(req.getSysCode(),  req.getTypeId(),cond);
        List<CommonListDto> dtos = BeanUtil.copyToList(commonLists, CommonListDto.class);
        return ResponseResultUtil.success(dtos);
    }

}
