package com.marmot.common.list.web.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marmot.common.list.sdk.common.PageData;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/25
 * @Desc: todo
 */
public class PageDataUtil {

    public static <Source,Dest> PageData<Dest> trans(Page<Source> page, Function<Source,Dest> transferFun){
        PageData<Dest> pageData = new PageData<>();
        pageData.setPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setPages(page.getPages());
        pageData.setTotal(page.getTotal());
        if (CollectionUtils.isNotEmpty(page.getRecords()) && transferFun != null){
            pageData.setRecords(page.getRecords().stream().map(d -> transferFun.apply(d)).collect(Collectors.toList()));
        }
        return pageData;
    }

    public static <Source,Dest> PageData<Dest> transWithCopy(Page<Source> page, Class<Dest> destClass){
        PageData<Dest> pageData = new PageData<>();
        pageData.setPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setPages(page.getPages());
        pageData.setTotal(page.getTotal());
        if (CollectionUtils.isNotEmpty(page.getRecords()) && destClass != null){
            pageData.setRecords(page.getRecords().stream().map(d -> BeanUtil.copyProperties(d, destClass)).collect(Collectors.toList()));
        }
        return pageData;
    }
}
