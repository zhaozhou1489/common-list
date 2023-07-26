package com.marmot.common.list.sdk.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import javax.validation.constraints.NotBlank;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/24
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonListTypeAllReq {
    @NotBlank(message = "[sysCode] should not blank")
    private String sysCode;
}
