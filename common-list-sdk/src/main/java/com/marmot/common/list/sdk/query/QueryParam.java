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
public class QueryParam {
    //查询方式，如equal，like等
    private List<String> queries;

    //limit
    private Limit limit;

    //排序方式
    private List<Order> orders;
}
