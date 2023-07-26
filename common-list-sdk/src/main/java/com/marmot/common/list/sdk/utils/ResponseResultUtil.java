package com.marmot.common.list.sdk.utils;

import com.marmot.common.list.sdk.common.ResponseResult;
import com.marmot.common.list.sdk.enums.ErrorEnum;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/24
 * @Desc:
 */
public class ResponseResultUtil {

    public static <T> ResponseResult<T> newResponseResult(int code, String msg,T data){
        return new ResponseResult<T>(code, msg, data);
    }


    public static<T> ResponseResult<T> success(T data){
        return newResponseResult(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), data);
    }

    public static ResponseResult success(){
        return success(null);
    }

    public static ResponseResult fail(int code, String msg){
        return newResponseResult(code, msg, null);
    }

    public static ResponseResult fail(String msg){
        return newResponseResult(ErrorEnum.FAIL.getCode(), msg, null);
    }

    public static ResponseResult fail(){
        return newResponseResult(ErrorEnum.FAIL.getCode(), ErrorEnum.FAIL.getMsg(), null);
    }

    public static ResponseResult fail(ErrorEnum errorEnum){
        return newResponseResult(errorEnum.getCode(), errorEnum.getMsg(), null);
    }
    public static ResponseResult fail(ErrorEnum errorEnum, String msg){
        return newResponseResult(errorEnum.getCode(), errorEnum.getMsg(), null);
    }


}
