package com.marmot.common.list.sdk.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:zhaozhou
 * @Date: 2023/08/01
 * @Desc: 排序参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String field;
    private Integer order;
    private String sortStr;
}
