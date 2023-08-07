package com.marmot.common.list.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marmot.common.list.web.domain.entity.CommonListType;
import com.marmot.common.list.web.mapper.CommonListTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommonListTypeService extends ServiceImpl<CommonListTypeMapper, CommonListType> {


    public CommonListType getByTypeId(Long typeId){
        return this.getOne(lambdaQuery().eq(CommonListType::getId, typeId));
    }

    public boolean existByTypeId(String sysCode, long typeId){
        QueryWrapper<CommonListType> wrapper = new QueryWrapper<>();
        wrapper.eq("sys_code", sysCode).eq("id", typeId);
        return this.getOne(wrapper) != null;
    }

    public List<CommonListType> listBySysCode(String sysCode){
        return baseMapper.selectList("sys_code", sysCode);
    }


    public boolean deleteById(long id){
        return baseMapper.deleteById(id) > 0;
    }

}
