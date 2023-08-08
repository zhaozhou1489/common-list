package com.marmot.common.list.web.aggService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.marmot.common.list.sdk.request.CommonListAddReq;
import com.marmot.common.list.web.domain.entity.CommonList;
import com.marmot.common.list.web.domain.entity.ListUpdateRule;
import com.marmot.common.list.web.service.CommonListService;
import com.marmot.common.list.web.service.CommonListTypeService;
import com.marmot.common.list.web.service.ListUpdateRuleService;
import com.marmot.common.list.web.utils.ExtMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        //查询数据校验规则，若没有，则使用默认规则
        //默认规则，biz_key必须唯一
        List<ListUpdateRule> updateRuleList = updateRuleService.listByTypeId(commonList.getSysCode(), commonList.getTypeId());
        Set<String> uniqField = new HashSet<>();
        Map<String, Object>  uniqMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(updateRuleList)){
            uniqField = new HashSet<>(JSON.parseArray(updateRuleList.get(0).getRule(),String.class));
            uniqMap = ExtMapUtil.holdByKey(BeanUtil.beanToMap(commonList), uniqField);
        }
        if (MapUtils.isEmpty(uniqMap)){
            uniqField.add("sysCode");
            uniqField.add("bizKey");
            uniqMap = ExtMapUtil.holdByKey(BeanUtil.beanToMap(commonList), uniqField);
        }
        return listService.saveOrUpdate(commonList, uniqMap);
    }

}
