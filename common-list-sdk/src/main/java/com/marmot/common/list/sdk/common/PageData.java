package com.marmot.common.list.sdk.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/25
 * @Desc: todo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData<T> {
    private long total;
    private long page;
    private long pages;
    private long pageSize;
    private List<T> records;
}
