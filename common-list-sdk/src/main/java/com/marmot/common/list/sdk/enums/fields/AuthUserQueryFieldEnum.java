package com.marmot.common.list.sdk.enums.fields;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum AuthUserQueryFieldEnum {
    USER_ID("userId"),
    USER_NAME("userName"),
    STATUS("status"),
    LONG_PARAM1("strParam1"),
    LONG_PARAM2("longParam2"),
    STR_PARAM1("strParam1"),
    STR_PARAM2("strParam2"),
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

    AuthUserQueryFieldEnum(String field) {
        this.field = field;
    }

    public static Set<String> allFields() {
        return Arrays.stream(AuthUserQueryFieldEnum.values()).map(e -> e.getField()).collect(Collectors.toSet());
    }
}
