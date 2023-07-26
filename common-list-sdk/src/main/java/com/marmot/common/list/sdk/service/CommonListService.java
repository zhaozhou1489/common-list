package com.marmot.common.list.sdk.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/21
 * @Desc:
 */
@Slf4j
@Service
public class CommonListService {/*


    @Autowired
    private CommonListClient listClient;


    public ResponseResult<List<CommonListTypeDto>> allTypes(String sysCode){
        CommonListTypeReq req = new CommonListTypeReq(sysCode);
        return listClient.allTypes(req);
    }


    public ResponseResult listAdd(CommonListAddReq req){
        return listClient.listAdd(req);
    }

    public ResponseResult listDelete(CommonListDeleteReq req){
        return listClient.listDelete(req);
    }

    public ResponseResult<PageData<CommonListDto>> listPage(CommonListPageReq req){
        return listClient.listPage(req);
    }

    *//**
     * @Desc 查询list
     * @Param [sysCode, customerId, typeId, qb]
     * @return com.radar.commons.utils.result.ResponseResult<java.util.List<com.build.banking.sdk.dto.CommonListDto>>
     **//*
    public ResponseResult<List<CommonListDto>> listFind(String sysCode, String customerId, Long typeId, QueryBuilder qb){
        CommonListFindReq req = new CommonListFindReq(sysCode, customerId, typeId, qb.getQueryParam());
        return listClient.listFind(req);
    }

    *//**
     * @Desc 查询单个
     * @Param [sysCode, customerId, typeId, qb]
     * @return com.radar.commons.utils.result.ResponseResult<com.build.banking.sdk.dto.CommonListDto>
     **//*
    public ResponseResult<CommonListDto> listFindOne(String sysCode, String customerId, Long typeId, QueryBuilder qb){
        qb.setLimit(0,1);
        ResponseResult<List<CommonListDto>> listResp = this.listFind(sysCode, customerId,typeId,qb);
        if (!listResp.getCode().equals(0)){
            return new ResponseResult(listResp.getCode(), listResp.getMsg());
        }
        return CollectionUtils.isEmpty(listResp.getData()) ?
                new ResponseResult<>(0,"no data"):
                new ResponseResult<>(0,"success", listResp.getData().get(0));
    }

    *//**
     * @Desc 查询是否存在
     * @Param [sysCode, customerId, typeId, qb]
     * @return com.radar.commons.utils.result.ResponseResult<java.lang.Boolean>
     **//*
    public ResponseResult<Boolean> listExist(String sysCode, String customerId, Long typeId, QueryBuilder qb){
        ResponseResult<CommonListDto> dtoResp = this.listFindOne(sysCode, customerId,typeId,qb);
        if (!dtoResp.getCode().equals(0)){
            return new ResponseResult<>(dtoResp.getCode(), dtoResp.getMsg());
        }
        return new ResponseResult<Boolean>(0, "success", dtoResp.getData()!= null);
    }*/
}
