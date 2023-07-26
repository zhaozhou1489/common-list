package com.marmot.common.list.web.utils;

import com.marmot.common.list.sdk.query.BaseQuery;
import com.marmot.common.list.sdk.query.QueryParam;
import com.marmot.common.list.sdk.query.utils.QueryParser;
import com.marmot.common.list.sdk.query.utils.QueryValidator;
import com.marmot.common.list.web.domain.cond.QueryCond;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/25
 * @Desc:
 */
@Slf4j
public class QueryParamUtil {

    private static final long LIMIT_COUNT_MAX = 10000;

    public static String parse(QueryParam queryParam, QueryCond cond){
        String errMsg = "";
        List<BaseQuery> queries = null;
        try {
            //解析及校验查询参数
            queries = QueryParser.parseQueries(queryParam.getQueries());
        }catch (Exception e){
            log.error("parse req error, queryParam={}", JsonUtils.toJsonString(queryParam), e);
            errMsg = e.getMessage();
        }
        if (StringUtils.isNotBlank(errMsg)){
            return errMsg;
        }
        cond.setQueries(queries);
        cond.setLimit(queryParam.getLimit());
        return null;
    }


    public static String valid(QueryCond cond){
        String errMsg = "";
        try {
            //解析及校验查询参数
            errMsg = QueryValidator.validQueries(cond.getQueries());
        }catch (Exception e){
            log.error("parse req error, queryParam={}", JsonUtils.toJsonString(cond), e);
            errMsg = e.getMessage();
        }
        if (StringUtils.isNotBlank(errMsg)){
            return errMsg;
        }
        if (cond.getLimit() !=null && cond.getLimit().getCount() > LIMIT_COUNT_MAX){
            return "limit count should less than " + LIMIT_COUNT_MAX;
        }
        return null;
    }
}
