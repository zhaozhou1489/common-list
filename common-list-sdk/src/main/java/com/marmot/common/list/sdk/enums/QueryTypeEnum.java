package com.marmot.common.list.sdk.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum QueryTypeEnum {
    EQUAL("equal"),
    RANGE("range"),
    SET("in"),
    LIKE("like"),
    ;

    private String name;

    public String getName() {
        return name;
    }

    QueryTypeEnum(String name) {
        this.name = name;
    }

    public static boolean valid(String name) {
        return of(name) != null;
    }

    public static QueryTypeEnum of(String name){
        if (StringUtils.isBlank(name)) return null;
        return Arrays.stream(QueryTypeEnum.values()).filter(t -> t.getName().equals(name)).findFirst().orElse(null);
    }
}
