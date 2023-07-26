package com.marmot.common.list.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marmot.common.list.sdk.query.utils.QueryUtil;
import com.marmot.common.list.web.domain.cond.QueryCond;
import com.marmot.common.list.web.domain.entity.CommonList;
import com.marmot.common.list.web.mapper.CommonListMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ListUpdateRuleService extends ServiceImpl<CommonListMapper, CommonList> {


    public CommonList getByListId(Long listId){
        return this.getOne(lambdaQuery().eq(CommonList::getId, listId));
    }

    public boolean deleteByListId(Long listId){
        QueryWrapper<CommonList> wrapper = new QueryWrapper();
        wrapper.eq("id", listId);
        return baseMapper.delete(wrapper) > 0;
    }

    public Page<CommonList> commonListPage(String sysCode, Long typeId, QueryCond cond, int pageNo, int pageSize){
        QueryWrapper<CommonList> wrapper = new QueryWrapper<>();
        wrapper.eq("sys_code", sysCode)
                .eq("type_id", typeId);
        QueryUtil.transQueries(wrapper, cond.getQueries());
        QueryUtil.setLimit(wrapper,cond.getLimit());
        wrapper.orderByDesc("create_stamp");
        return baseMapper.selectPage(new Page<CommonList>(pageNo, pageSize),wrapper);
    }


    public List<CommonList> findCommonList(String syscode, String customerId, Long typeId, QueryCond queryCond){
        QueryWrapper<CommonList> qw = new QueryWrapper();
        qw.eq("sys_code",syscode);
        qw.eq("customer_id", customerId);
        qw.eq("type_id", typeId);
        QueryUtil.transQueries(qw, queryCond.getQueries());
        QueryUtil.setLimit(qw, queryCond.getLimit());
        qw.orderByDesc("create_time");
        return baseMapper.selectList(qw);
    }

    public CommonList getListInfo(String sysCode, String customerId, long id) {
        QueryWrapper<CommonList> wrapper = new QueryWrapper();
        wrapper.eq(StringUtils.isNotBlank(sysCode), "sys_code", sysCode)
                .eq(StringUtils.isNotBlank(customerId),"customer_id", customerId)
                .eq("id", id);
        return baseMapper.selectOne(wrapper);
    }
}
