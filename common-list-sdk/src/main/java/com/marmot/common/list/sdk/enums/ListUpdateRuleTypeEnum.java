package com.marmot.common.list.sdk.enums;

/**
 * @Author:zhaozhou
 * @Date: 2023/08/07
 * @Desc: todo
 */
public enum ListUpdateRuleTypeEnum {
    UNIQ(1,"唯一规则","rule中对应的字段必须唯一");


    private int rule;
    private String name;
    private String desc;

    public int getRule() {
        return rule;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    ListUpdateRuleTypeEnum(int rule, String name, String desc) {
        this.rule = rule;
        this.name = name;
        this.desc = desc;
    }
}
