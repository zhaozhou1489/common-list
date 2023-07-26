package com.marmot.common.list.sdk.query.utils;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marmot.common.list.sdk.query.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/18
 * @Desc:
 */
public class QueryUtil {

    public static <T extends BaseQuery> QueryWrapper transQueries(QueryWrapper wrapper, List<T> queries){
        queries.stream().forEach(q -> {
            q.setField(StrUtil.toUnderlineCase(q.getField()));
            transQuery(wrapper, q);
        });
        return wrapper;
    }



    public static <T extends BaseQuery> QueryWrapper transQuery(QueryWrapper wrapper, T query){
        if (query instanceof EqualQuery){
            EqualQuery q = (EqualQuery) query;
            transEqualQuery(wrapper, q);
        }else if (query instanceof LikeQuery){
            LikeQuery q = (LikeQuery) query;
            transLikeQuery(wrapper,q);

        }else if (query instanceof RangeQuery){
            RangeQuery q = (RangeQuery) query;
            transRangeQuery(wrapper,q);

        }else if (query instanceof InQuery){
            InQuery q = (InQuery) query;
            transInQuery(wrapper,q);
        }
        return wrapper;
    }



    public static QueryWrapper transEqualQuery(QueryWrapper wrapper, EqualQuery query){
        wrapper.eq(query.getField(), query.getValue());
        return wrapper;
    }

    public static QueryWrapper transLikeQuery(QueryWrapper wrapper, LikeQuery query){
        if (query.isLeft() && query.isRight()){
            wrapper.like(query.getField(), query.getLikeValue());
        }else if (query.isLeft()){
            wrapper.likeLeft(query.getField(), query.getLikeValue());
        }else {
            wrapper.likeRight(query.getField(), query.getLikeValue());
        }

        return wrapper;
    }

    public static QueryWrapper transRangeQuery(QueryWrapper wrapper, RangeQuery query){
        if (StringUtils.isNotBlank(query.getMax())){
            if (query.getIncludeMax()){
                wrapper.le(query.getField(), query.getMax());
            }else {
                wrapper.lt(query.getField(), query.getMax());
            }
        }
        if (StringUtils.isNotBlank(query.getMin())){
            if (query.getIncludeMin()){
                wrapper.ge(query.getField(), query.getMin());
            }else {
                wrapper.gt(query.getField(), query.getMin());
            }
        }

        return wrapper;
    }

    public static QueryWrapper transInQuery(QueryWrapper wrapper, InQuery query){
        wrapper.in(query.getField(), query.getValues());
        return wrapper;
    }

    public static QueryWrapper setLimit(QueryWrapper wrapper, Limit limit){
        if (limit != null){
            wrapper.last("limit " + limit.getOffset() + " , " + limit.getCount());
        }
        return wrapper;
    }




}
