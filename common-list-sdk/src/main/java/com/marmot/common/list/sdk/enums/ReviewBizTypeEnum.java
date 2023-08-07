package com.marmot.common.list.sdk.enums;

import java.util.Arrays;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/06
 * @Desc:
 */
public enum ReviewBizTypeEnum {
    CARD_APPLICATION(1);


    private Integer bizType;

    ReviewBizTypeEnum(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getBizType() {
        return bizType;
    }

    public static ReviewBizTypeEnum of(Integer bizType) {
        if (bizType == null) return null;
        return Arrays.stream(ReviewBizTypeEnum.values()).filter(t -> t.getBizType().equals(bizType)).findFirst().orElse(null);
    }
}
