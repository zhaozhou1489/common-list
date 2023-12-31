package com.marmot.common.list.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marmot.common.list.sdk.query.QueryCond;
import com.marmot.common.list.sdk.query.utils.QueryUtil;
import com.marmot.common.list.web.domain.entity.CommonList;
import com.marmot.common.list.web.domain.entity.ListUpdateRule;
import com.marmot.common.list.web.mapper.CommonListMapper;
import com.marmot.common.list.web.mapper.ListUpdateRuleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ListUpdateRuleService extends ServiceImpl<ListUpdateRuleMapper, ListUpdateRule> {

    public List<ListUpdateRule> listByTypeId(String sysCode, Long typeId){
        QueryWrapper<ListUpdateRule> wrapper = new QueryWrapper<>();
        wrapper.eq("sys_code", sysCode)
                .eq("type_id", typeId);
        return baseMapper.selectList(wrapper);
    }

}
