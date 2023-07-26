package com.marmot.common.list.web.aggService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.marmot.common.list.sdk.request.CommonListAddReq;
import com.marmot.common.list.web.domain.entity.CommonList;
import com.marmot.common.list.web.domain.entity.ListUpdateRule;
import com.marmot.common.list.web.service.CommonListService;
import com.marmot.common.list.web.service.CommonListTypeService;
import com.marmot.common.list.web.service.ListUpdateRuleService;
import com.marmot.common.list.web.utils.ExtMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/25
 * @Desc:
 */
@Slf4j
@Service
public class CommonListAggService {

    @Autowired
    private CommonListService listService;


    @Autowired
    private ListUpdateRuleService updateRuleService;



    public boolean saveOrUpdate(CommonList commonList){
        //todo 查询更新唯一键规则
        //默认规则，biz_key必须唯一
        Set<String> uniqField = new HashSet<>();
        uniqField.add("biz_key");
        uniqField = uniqField.stream().map(str -> StrUtil.toCamelCase(str)).collect(Collectors.toSet());
        Map<String, Object>  uniqMap = ExtMapUtil.holdByKey(BeanUtil.beanToMap(commonList), uniqField);
        return listService.saveOrUpdate(commonList, uniqMap);
    }

}
