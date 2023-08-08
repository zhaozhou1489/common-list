package com.marmot.common.list.web.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/24
 * @Desc: 名单类型
 */
@Data
@NoArgsConstructor
public class CommonListType {

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * @Desc 系统code
     **/
    private String sysCode;

    /**
     * @Desc 类型名称
     **/
    private String typeName;

    /**
     * @Desc 类型描述
     **/
    private String typeDesc;

    /**
     * @Desc long类型扩展字段
     **/
    private Long longParam;

    /**
     * @Desc string类型扩展字段
     **/
    private String strParam;
    /**
     * @Desc 状态
     **/
    private Integer status;

    /**
     * @Desc 操作人id
     **/
    private String operatorId;

    /**
     * @Desc 操作人名称
     **/
    private String operatorName;

    private Long createStamp;

    private Long updateStamp;
}
