package com.marmot.common.list.sdk.enums;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/24
 * @Desc:
 */
public enum ErrorEnum {
    SUCCESS(0,"成功"),
    FAIL(1,"失败"),
    SYS_ERROR(2,"系统错误"),
    PARAM_ERROR(3,"参数错误"),

    TYPE_NOT_EXIST(1000, "List type dose not exist"),

    LIST_NOT_EXIST(2000, "List dose not exist"),

    ;
    private int code;
    private String msg;


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
