package com.marmot.common.list.web.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/25
 * @Desc: 每种类型名单字段输入配置
 */
@Data
@NoArgsConstructor
public class ListTypeFieldConfig {
    private Long id;

    /**
     * @Desc 类型id
     **/
    private Long typeId;

    /**
     * @Desc 字段
     **/
    private String field;

    /**
     * @Desc 是否必填，1：必填；0：非必填
     **/
    private Integer required;

    /**
     * @Desc 校验规则
     **/
    private String regex;

    /**
     * @Desc 错误提示
     **/
    private String errorText;

    /**
     * @Desc 操作人id
     **/
    private String operatorId;

    /**
     * @Desc 操作人名称
     **/
    private String operatorName;

    private Long modifyTime;
}
