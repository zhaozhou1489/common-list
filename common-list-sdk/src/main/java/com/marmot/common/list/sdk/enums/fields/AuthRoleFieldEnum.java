package com.marmot.common.list.sdk.enums.fields;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum AuthRoleFieldEnum {
    roleCode("roleCode"),
    roleName("roleName"),
    roleDesc("roleDesc"),
    isDefault("isDefault"),
    STATUS("status"),
    LONG_PARAM("longParam"),
    STR_PARAM("strParam"),
    EXTRA("extra"),
    CREATE_TIME("createTime"),
    UPDATE_TIME("updateTime"),
    OPERATOR_ID("operatorId"),
    OPERATOR_NAME("operatorName"),
    ;
    private String field;

    public String getField() {
        return field;
    }

    AuthRoleFieldEnum(String field) {
        this.field = field;
    }

    public static Set<String> allFields() {
        return Arrays.stream(AuthRoleFieldEnum.values()).map(e -> e.getField()).collect(Collectors.toSet());
    }
}
