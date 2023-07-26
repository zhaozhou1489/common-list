package com.marmot.common.list.web.domain.cond;

import com.marmot.common.list.sdk.query.BaseQuery;
import com.marmot.common.list.sdk.query.Limit;
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
}
