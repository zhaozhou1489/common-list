package com.marmot.common.list.sdk.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/24
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {
    private Integer code;

    private String msg;

    private T   data;

}
