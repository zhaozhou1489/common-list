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
import com.marmot.common.list.web.service.CommonListService;
import com.marmot.common.list.web.service.CommonListTypeService;
import com.marmot.common.list.web.utils.NumberUtil;
import com.marmot.common.list.web.utils.PageDataUtil;
import com.marmot.common.list.web.utils.QueryParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

        Page<CommonList> page = listService.commonListPage(req.getSysCode(), req.getTypeId(), cond, req.getPageNo(),req.getPageSize());
        PageData<CommonListDto> pageData = PageDataUtil.transWithCopy(page, CommonListDto.class);
        return ResponseResultUtil.success(pageData);
    }

    /**
     * @Desc 添加/更新名单
     * @Param
     * @return
     **/
    @PostMapping(value = "/modify")
    public ResponseResult listAdd(@Validated @RequestBody CommonListAddReq req){
        CommonList commonList = BeanUtil.copyProperties(req, CommonList.class);
        commonList.setModifyTime(System.currentTimeMillis());
        return listAggService.saveOrUpdate(commonList) ? ResponseResultUtil.success():ResponseResultUtil.fail();
    }


    /**
     * @Desc 删除名单
     * @Param
     * @return
     **/
    @PostMapping(value = "/delete")
    public ResponseResult listDelete(@Validated @RequestBody CommonListDeleteReq req){
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
    public ResponseResult<List<CommonListDto>> findList(@Validated @RequestBody CommonListFindReq req){

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
