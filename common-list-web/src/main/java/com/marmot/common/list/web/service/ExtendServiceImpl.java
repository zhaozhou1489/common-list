package com.marmot.common.list.web.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/25
 * @Desc: todo
 */
public class ExtendServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> {

    @Transactional
    public boolean saveOrUpdate(T entity, Map<String, Object> queryCond){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.allEq(queryCond,true);
        wrapper.last("for update");
        T data = baseMapper.selectOne(wrapper);
        if (data != null){
            CopyOptions options = new CopyOptions();
            options.setIgnoreNullValue(true);
            options.setIgnoreProperties("id");
            BeanUtil.copyProperties(entity,data,options);
            return baseMapper.updateById(data) > 0;
        }else {
            return baseMapper.insert(entity) > 0;
        }
    }
}
