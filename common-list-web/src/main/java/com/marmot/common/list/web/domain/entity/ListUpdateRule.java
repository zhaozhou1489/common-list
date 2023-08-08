package com.marmot.common.list.web.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/24
 * @Desc: 每种类型的名单更新校验规则
 */
@Data
@NoArgsConstructor
public class ListUpdateRule {

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private String sysCode;

    /**
     * @Desc 类型id
     **/
    private Long typeId;

    /**
     * @Desc 规则类型
     **/
    private Integer ruleType;

    /**
     * @Desc 规则名称
     **/
    private String ruleName;

    /**
     * @Desc 规则描述
     **/
    private String ruleDesc;

    /**
     * @Desc 规则
     **/
    private String rule;

    private Long createTime;

    private Long updateTime;

    /**
     * @Desc 操作人id
     **/
    private String operatorId;

    /**
     * @Desc 操作人名称
     **/
    private String operatorName;
}
