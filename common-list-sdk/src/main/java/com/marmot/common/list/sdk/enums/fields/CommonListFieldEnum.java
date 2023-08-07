package com.marmot.common.list.sdk.enums.fields;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum CommonListFieldEnum {
    ID("id"),
    SYS_CODE("sysCode"),
    TYPE_ID("typeId"),
    BIZ_KEY("bizKey"),
    REASON("reason"),
    LONG_PARAM1("longParam1"),
    LONG_PARAM2("longParam2"),
    STR_PARAM1("strParam1"),
    STR_PARAM2("strParam2"),
    EXTRA("extra"),
    MODIFY_TIME("modifyTime"),
    OPERATOR_ID("operatorId"),
    OPERATOR_NAME("operatorName"),
    ;
    private String field;

    public String getField() {
        return field;
    }

    CommonListFieldEnum(String field) {
        this.field = field;
    }

    public static Set<String> allFields() {
        return Arrays.stream(CommonListFieldEnum.values()).map(e -> e.getField()).collect(Collectors.toSet());
    }
}
