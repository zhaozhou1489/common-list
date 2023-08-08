package com.marmot.common.list.web.utils;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/25
 * @Desc:
 */
public class ExtMapUtil {

    public static Map<String, Object> holdByKey(Map<String, Object> map, Set<String> keys){
        Map<String, Object> retMap = new HashMap<>();
        if (MapUtils.isEmpty(map) || CollectionUtils.isEmpty(keys)){
            return retMap;
        }
        map.entrySet().stream().forEach(en -> {
            if (keys.contains(en.getKey())){
                retMap.put(StrUtil.toUnderlineCase(en.getKey()),en.getValue());
            }
        });
        return retMap;
    }
}
