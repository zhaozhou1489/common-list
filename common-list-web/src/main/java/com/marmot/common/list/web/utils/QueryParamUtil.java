package com.marmot.common.list.web.utils;

import cn.hutool.json.JSONUtil;
import com.marmot.common.list.sdk.enums.QueryOrderEnum;
import com.marmot.common.list.sdk.query.*;
import com.marmot.common.list.sdk.query.utils.QueryParser;
import com.marmot.common.list.sdk.query.utils.QueryValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/25
 * @Desc:
 */
@Slf4j
public class QueryParamUtil {


    private static final long LIMIT_COUNT_MAX = 10000;

    /**
     * @Desc 转换并校验查询参数
     * @Param
     * @return
     **/
    public static String transQueryParam(QueryParam queryParam, QueryCond queryCond, Set<String> validFields) {
        String errMsg = "";
        List<BaseQuery> queries = new LinkedList<>();

        queryCond.setQueries(new LinkedList<>());
        if (queryParam == null){
            return "";
        }
        //解析及校验查询参数
        errMsg = StringUtils.isNotBlank(errMsg = QueryParser.parseQueries(queryParam.getQueries(), queries)) ? errMsg : QueryValidator.validQueries(queries);
        if (StringUtils.isNotBlank(errMsg)) {
            return errMsg;
        }

        if (queryParam.getLimit() != null && queryParam.getLimit().getCount() > LIMIT_COUNT_MAX) {
            return "limit count should less than " + LIMIT_COUNT_MAX;
        }
        if (queryParam.getLimit() == null){
            queryParam.setLimit(new Limit(0L, LIMIT_COUNT_MAX));
        }

        if (CollectionUtils.isNotEmpty(queryParam.getOrders())){
            queryParam.getOrders().forEach(order -> order.setSortStr(QueryOrderEnum.of(order.getSortStr()).getOrderStr()));
        }
        queryCond.setQueries(queries);
        queryCond.setLimit(queryParam.getLimit());
        queryCond.setOrders(queryParam.getOrders());

        //校验字段是否支持
        return validFieldOfQueryCond(queryCond, validFields);
    }


    public static void initDefaultLimit(QueryCond queryCond){
        if (queryCond != null && queryCond.getLimit() == null){
            queryCond.setLimit(new Limit(0L,LIMIT_COUNT_MAX));
        }
    }


    private static String validFieldOfQueryCond(QueryCond queryCond, Set<String> validFields){
        if (CollectionUtils.isEmpty(validFields)){
            return "";
        }
        //查询字段检查
        Set<String> queryInvalidFields = CollectionUtils.isEmpty(queryCond.getQueries()) ? new HashSet<>() :
                queryCond.getQueries().stream().map(BaseQuery::getField).filter(field -> !validFields.contains(field)).collect(Collectors.toSet());
        //排序字段检查
        Set<String> orderInvalidFields = CollectionUtils.isEmpty(queryCond.getOrders()) ? new HashSet<>():
                queryCond.getOrders().stream().map(Order::getField).filter(field -> !validFields.contains(field)).collect(Collectors.toSet());
        Set<String> invalidFields = Stream.of(queryInvalidFields,orderInvalidFields).flatMap(Set::stream).collect(Collectors.toSet());
        return CollectionUtils.isEmpty(invalidFields) ? "": "fields " + JSONUtil.toJsonStr(invalidFields) + " are not support";
    }
}
