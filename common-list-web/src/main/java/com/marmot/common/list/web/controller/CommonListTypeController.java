package com.marmot.common.list.web.controller;

import cn.hutool.core.bean.BeanUtil;
import com.marmot.common.list.sdk.common.ResponseResult;
import com.marmot.common.list.sdk.dto.CommonListTypeDto;
import com.marmot.common.list.sdk.enums.ErrorEnum;
import com.marmot.common.list.sdk.enums.TypeStatusEnum;
import com.marmot.common.list.sdk.request.CommonListTypeAddReq;
import com.marmot.common.list.sdk.request.CommonListTypeAllReq;
import com.marmot.common.list.sdk.request.CommonListTypeDeleteReq;
import com.marmot.common.list.sdk.request.CommonListTypeUpdateReq;
import com.marmot.common.list.sdk.utils.ResponseResultUtil;
import com.marmot.common.list.web.constant.ApiPath;
import com.marmot.common.list.web.domain.entity.CommonListType;
import com.marmot.common.list.web.service.CommonListTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/25
 * @Desc: 名单类型
 */
@Slf4j
@RestController
@RequestMapping(value = ApiPath.TYPE_PREFIX)
public class CommonListTypeController {

    @Autowired
    private CommonListTypeService listTypeService;

    /**
     * @Desc 获取全部的类型
     * @Param
     * @return
     **/
    @PostMapping("/all")
    public ResponseResult<List<CommonListTypeDto>> allTypes(@Validated @RequestBody CommonListTypeAllReq req) {
        List<CommonListType> allTypes = listTypeService.listBySysCode(req.getSysCode());
        List<CommonListTypeDto> typeDtos = BeanUtil.copyToList(allTypes, CommonListTypeDto.class);
        return ResponseResultUtil.success(typeDtos);
    }

    /**
     * @Desc 添加类型
     * @Param
     * @return
     **/
    @PostMapping("/add")
    public ResponseResult add(@Validated @RequestBody CommonListTypeAddReq req) {
        CommonListType listType = BeanUtil.copyProperties(req, CommonListType.class);
        listType.setStatus(TypeStatusEnum.NORMAL.getStatus());
        listType.setCreateStamp(System.currentTimeMillis());
        listType.setUpdateStamp(System.currentTimeMillis());
        listTypeService.save(listType);
        return ResponseResultUtil.success();
    }

    /**
     * @Desc 更改类型
     * @Param
     * @return
     **/
    @PostMapping("/update")
    public ResponseResult update(@Validated @RequestBody CommonListTypeUpdateReq req) {
        CommonListType listType = listTypeService.getByTypeId(req.getId());
        if (listType == null) {
            return ResponseResultUtil.fail(ErrorEnum.TYPE_NOT_EXIST);
        }
        CommonListType newListType = BeanUtil.copyProperties(req, CommonListType.class);
        newListType.setUpdateStamp(System.currentTimeMillis());
        newListType.setStatus(listType.getStatus());
        return listTypeService.updateById(newListType) ? ResponseResultUtil.success() : ResponseResultUtil.fail();
    }


    /**
     * @Desc 删除类型
     * @Param
     * @return
     **/
    @PostMapping("/delete")
    public ResponseResult delete(@Validated @RequestBody CommonListTypeDeleteReq req) {
        CommonListType listType = listTypeService.getByTypeId(req.getId());
        if (listType == null) {
            return ResponseResultUtil.fail(ErrorEnum.TYPE_NOT_EXIST);
        }
        if (!listType.getSysCode().equals(req.getSysCode())){
            return ResponseResultUtil.fail(ErrorEnum.NO_RIGHT_TO_ACCESS);
        }
        return listTypeService.deleteById(req.getId()) ? ResponseResultUtil.success() : ResponseResultUtil.fail();
    }
}
