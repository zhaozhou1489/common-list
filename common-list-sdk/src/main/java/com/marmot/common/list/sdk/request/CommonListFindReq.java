package com.marmot.common.list.sdk.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marmot.common.list.sdk.query.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/18
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonListFindReq {
    @NotBlank(message = "[sys_code] should not blank")
    @JsonProperty(value = "sys_code")
    private String sysCode;


    @NotNull(message = "[type_id] should not empty")
    @Positive(message = "[type_id] value is invalid")
    @JsonProperty(value = "type_id")
    private Long typeId;

    @NotNull(message = "[queryParam] should not empty")
    @JsonProperty(value = "query_param")
    private QueryParam queryParam;

}
