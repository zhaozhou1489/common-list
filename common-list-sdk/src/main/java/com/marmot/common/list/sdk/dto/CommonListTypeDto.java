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
public class CommonListTypeDto {
    private Long id;

    private String sysCode;

    private String typeName;

    private String typeDesc;

    private Long longParam;

    private String strParam;

    private Integer status;

    private Long createStamp;

    private Long updateStamp;

    private String operatorId;

    private String operatorName;
}
