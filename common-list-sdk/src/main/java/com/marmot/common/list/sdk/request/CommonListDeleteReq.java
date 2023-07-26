package com.marmot.common.list.sdk.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/18
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonListDeleteReq {

    @NotBlank(message = "[sys_code] should not blank")
    @JsonProperty(value = "sys_code")
    private String sysCode;


    @NotNull(message = "[list_id] should not empty")
    @Positive(message = "[list_id] is invalid")
    @JsonProperty(value = "list_id")
    private Long listId;


}
