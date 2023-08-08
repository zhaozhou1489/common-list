package com.marmot.common.list.sdk.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/24
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonListTypeAddReq {
    /**
     * @Desc 系统code
     **/
    @NotBlank(message = "[sysCode] should not blank")
    @Size(max = 100, message = "[sysCode] length needs to be less than 100")
    private String sysCode;

    /**
     * @Desc 类型名称
     **/
    @NotBlank(message = "[sysCode] should not blank")
    @Size(max = 200, message = "[typeName] length needs to be less than 200")
    private String typeName;

    /**
     * @Desc 类型描述
     **/
    @NotBlank(message = "[sysCode] should not blank")
    @Size(max = 10000, message = "[typeDesc] length needs to be less than 10000")
    private String typeDesc;

    /**
     * @Desc long类型扩展字段
     **/
    private Long longParam;

    /**
     * @Desc string类型扩展字段
     **/
    @Size(max = 200, message = "[strParam] length needs to be less than 200")
    private String strParam;

    /**
     * @Desc 操作人id
     **/
    @Size(max = 50, message = "[strParam] length needs to be less than 50")
    @NotBlank(message = "[operatorId] should not blank")
    private String operatorId;

    /**
     * @Desc 操作人名称
     **/
    @Size(max = 200, message = "[strParam] length needs to be less than 200")
    @NotBlank(message = "[operatorName] should not blank")
    private String operatorName;

}
