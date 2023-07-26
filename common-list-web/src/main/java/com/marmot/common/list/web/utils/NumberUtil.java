package com.marmot.common.list.web.utils;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/21
 * @Desc:
 */
public class NumberUtil {

    public static <T extends Number> boolean isPositive(T num){
        if (num == null){
            return false;
        }
        return num.intValue() > 0;
    }

}
