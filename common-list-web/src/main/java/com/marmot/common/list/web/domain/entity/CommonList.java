package com.marmot.common.list.web.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/24
 * @Desc: 名单
 */
@Data
@NoArgsConstructor
public class CommonList {
    private Long id;

    /**
     * @Desc 系统code
     **/
    private String sysCode;

    /**
     * @Desc 类型id
     **/
    private Long typeId;

    /**
     * @Desc 业务key（一般情况为唯一）
     **/
    private String bizKey;

    /**
     * @Desc 添加原因
     **/
    private String reason;

    /**
     * @Desc long类型扩展参数1
     **/
    private Long longParam1;

    /**
     * @Desc long类型扩展参数2
     **/
    private Long longParam2;

    /**
     * @Desc string类型扩展参数1
     **/
    private String strParam1;

    /**
     * @Desc string类型扩展参数2
     **/
    private String strParam2;

    /**
     * @Desc 其他扩展参数
     **/
    private String extra;

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
