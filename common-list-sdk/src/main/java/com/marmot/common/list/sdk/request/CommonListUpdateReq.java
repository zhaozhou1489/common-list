package com.marmot.common.list.sdk.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/18
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonListUpdateReq {

    @NotNull(message = "[id] should not null")
    @Positive(message = "[id] value is invalid")
    private Long id;

    /**
     * @Desc 不能更改
     **/
    @NotBlank(message = "[sys_code] should not blank")
    @JsonProperty(value = "sys_code")
    private String sysCode;


    /**
     * @Desc 不能更改
     **/
    @NotNull(message = "[type_id] should not null")
    @Positive(message = "[type_id] value is invalid")
    @JsonProperty(value = "type_id")
    private Long typeId;


    /**
     * @Desc 不能更改
     **/
    @NotBlank(message = "[biz_key] should not blank")
    @Size(min = 1, max = 200, message = "[biz_key] should less than 200")
    @JsonProperty(value = "biz_key")
    private String bizKey;

    private String reason;

    @JsonProperty(value = "long_param1")
    private Long longParam1;

    @JsonProperty(value = "long_param2")
    private Long longParam2;

    @JsonProperty(value = "str_param1")
    private String strParam1;

    @JsonProperty(value = "str_param2")
    private String strParam2;

    private String extra;

    @NotBlank(message = "[operator_id] should not blank")
    @JsonProperty(value = "operator_id")
    private String operatorId;

    @NotBlank(message = "[operator_name] should not blank")
    @JsonProperty(value = "operator_name")
    private String operatorName;

}
