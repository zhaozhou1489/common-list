package com.marmot.common.list.sdk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/18
 * @Desc: 名单类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonListDto {
    private Long id;

    @JsonProperty(value = "sys_code")
    private String sysCode;

    @JsonProperty(value = "customer_id")
    private String customerId;

    @JsonProperty(value = "type_id")
    private Long typeId;

    @JsonProperty(value = "biz_key")
    private String bizKey;

    private String reason;

    @JsonProperty(value = "long_param1")
    private Long longParam1;

    @JsonProperty(value = "long_param2")
    private Long longParam2;

    @JsonProperty(value = "str param1")
    private String str_param1;

    @JsonProperty(value = "str_param2")
    private String str_param2;

    private String extra;

    @JsonProperty(value = "operator_id")
    private String operatorId;

    @JsonProperty(value = "operator_name")
    private String operatorName;

    @JsonProperty(value = "create_time")
    private Long createTime;
}
