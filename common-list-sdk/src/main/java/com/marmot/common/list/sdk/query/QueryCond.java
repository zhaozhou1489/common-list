package com.marmot.common.list.sdk.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/24
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryCond {
    private List<? extends BaseQuery> queries;
    private Limit limit;
    private List<Order> orders;
}
