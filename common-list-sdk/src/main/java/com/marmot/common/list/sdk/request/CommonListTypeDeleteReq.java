package com.marmot.common.list.sdk.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/24
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonListTypeDeleteReq {


    @NotNull(message = "[id] should be not null")
    private Long id;

    @NotBlank(message = "[sysCode] should be not blank")
    private String sysCode;
}
