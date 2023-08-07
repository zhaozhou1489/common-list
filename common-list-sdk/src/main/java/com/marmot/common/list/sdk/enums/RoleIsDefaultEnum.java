package com.marmot.common.list.sdk.enums;

public enum RoleIsDefaultEnum {
    DEFAULT(0,"default"),
    NON_DEFAULT(1, "non-default"),
    ;
    private int value;
    private String desc;

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    RoleIsDefaultEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
