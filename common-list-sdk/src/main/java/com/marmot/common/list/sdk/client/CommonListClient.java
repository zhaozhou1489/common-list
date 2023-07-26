package com.marmot.common.list.sdk.client;



import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "build-common-list", url="${feign.host.build_common}")
public interface CommonListClient {
//PostMapping
//    @PostMapping("/common/list/allTypes")
//    ResponseResult<List<CommonListTypeDto>> allTypes(@RequestBody CommonListTypeReq req);
//
//    @PostMapping("/common/list/add")
//    ResponseResult listAdd(@RequestBody CommonListAddReq req);
//
//
//    @PostMapping("/common/list/delete")
//    ResponseResult listDelete( @RequestBody CommonListDeleteReq req);
//
//    @PostMapping("/common/list/page")
//    ResponseResult<PageData<CommonListDto>> listPage(@RequestBody CommonListPageReq req);
//
//    @PostMapping("/common/list/find")
//    ResponseResult<List<CommonListDto>> listFind(@RequestBody CommonListFindReq req);


}


