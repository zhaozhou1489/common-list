package com.marmot.common.list.sdk.enums;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/25
 * @Desc:
 */
public enum TypeStatusEnum {
    NORMAL(0,"正常"),
    ;
    private int status;
    private String desc;

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    TypeStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}
