package com.marmot.common.list.sdk.enums;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/18
 * @Desc: todo
 */
public enum CommonListFieldEnum {
    bizKey("bizKey"),
    reason("reason"),
    longParam1("longParam1"),
    longParam2("longParam2"),
    strParam1("strParam1"),
    strParam2("strParam2"),
    extra("extra"),
    operatorId("operatorId"),
    operatorName("operatorName"),
    createTime("createTime"),

    ;
    private String field;

    public String getField() {
        return field;
    }

    CommonListFieldEnum(String field) {
        this.field = field;
    }
}
