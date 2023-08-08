package com.marmot.common.list.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marmot.common.list.sdk.query.QueryCond;
import com.marmot.common.list.sdk.query.utils.QueryUtil;
import com.marmot.common.list.web.domain.entity.CommonList;
import com.marmot.common.list.web.mapper.CommonListMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommonListService extends ExtendServiceImpl<CommonListMapper, CommonList> {


    public CommonList getByListId(Long listId){
        return this.getById(listId);
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
        QueryUtil.transQueryCond(wrapper,cond);
        return baseMapper.selectPage(new Page<CommonList>(pageNo, pageSize),wrapper);
    }


    public List<CommonList> findCommonList(String syscode, Long typeId, QueryCond queryCond){
        QueryWrapper<CommonList> qw = new QueryWrapper();
        qw.eq("sys_code",syscode);
        qw.eq("type_id", typeId);
        QueryUtil.transQueryCond(qw,queryCond);
        return baseMapper.selectList(qw);
    }

    public CommonList getCommonList(String sysCode, long id) {
        QueryWrapper<CommonList> wrapper = new QueryWrapper();
        wrapper.eq(StringUtils.isNotBlank(sysCode), "sys_code", sysCode)
                .eq("id", id);
        return baseMapper.selectOne(wrapper);
    }



}
