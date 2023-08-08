package com.marmot.common.list.web.config;

import com.marmot.common.list.sdk.common.ResponseResult;
import com.marmot.common.list.sdk.enums.ErrorEnum;
import com.marmot.common.list.sdk.utils.ResponseResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import javax.validation.ValidationException;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.marmot.common.list.sdk.enums.ErrorEnum.*;


/**
 * 全局异常处理器
 *
 * @author tsr
 */
@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 处理所有异常，主要是提供给 Filter 使用
     * 因为 Filter 不走 SpringMVC 的流程，但是我们又需要兜底处理异常，所以这里提供一个全量的异常处理过程，保持逻辑统一。
     *
     * @param request 请求
     * @param ex 异常
     * @return 通用返回
     */
    public ResponseResult<?> allExceptionHandler(HttpServletRequest request, Throwable ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            return missingServletRequestParameterExceptionHandler((MissingServletRequestParameterException) ex);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return methodArgumentTypeMismatchExceptionHandler((MethodArgumentTypeMismatchException) ex);
        }

        if (ex instanceof NoHandlerFoundException) {
            return noHandlerFoundExceptionHandler(request, (NoHandlerFoundException) ex);
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return httpRequestMethodNotSupportedExceptionHandler((HttpRequestMethodNotSupportedException) ex);
        }
        return defaultExceptionHandler(request, ex);
    }

    /**
     * 处理 SpringMVC 请求参数缺失
     *
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseResult<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return ResponseResultUtil.fail(ErrorEnum.BAD_REQUEST, String.format("Missing request parameter:%s", ex.getParameterName()));
    }

    /**
     * 处理 SpringMVC 请求参数类型错误
     *
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseResult<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        log.warn("[methodArgumentTypeMismatchExceptionHandler]", ex);
        return ResponseResultUtil.fail(ErrorEnum.BAD_REQUEST, String.format("Invalid request parameter type:%s", ex.getMessage()));
    }



    /**
     * 处理 SpringMVC 请求地址不存在
     *
     * 注意，它需要设置如下两个配置项：
     * 1. spring.mvc.throw-exception-if-no-handler-found 为 true
     * 2. spring.mvc.static-path-pattern 为 /statics/**
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseResult<?> noHandlerFoundExceptionHandler(HttpServletRequest req, NoHandlerFoundException ex) {
        log.warn("[noHandlerFoundExceptionHandler]", ex);
        return ResponseResultUtil.fail(ErrorEnum.PATH_NOT_FUND, String.format("Request path not exist:%s", ex.getRequestURL()));
    }

    /**
     * 处理 SpringMVC 请求方法不正确
     *
     * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.warn("[httpRequestMethodNotSupportedExceptionHandler]", ex);
        return ResponseResultUtil.fail(METHOD_NOT_ALLOW, String.format("Invalid request method:%s", ex.getMessage()));
    }


    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult<?> defaultExceptionHandler(HttpServletRequest req, Throwable ex) {
        log.warn("[defaultExceptionHandler]", ex);
        // 插入异常日志
        this.createExceptionLog(req, ex);
        // 返回 ERROR
        return ResponseResultUtil.fail(SYS_ERROR);
    }

    private void createExceptionLog(HttpServletRequest req, Throwable e) {
        // 插入错误日志
    }


    /**
     * @Desc spring参数校验异常拦截器
     * @Param [e]
     * @return com.radar.commons.utils.result.ResponseResult<?>
     **/
    @ExceptionHandler(value = {ConstraintViolationException.class, BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public ResponseResult<?> handleValidatedException(Exception e) {
        ResponseResult<String> resp = null;
        log.warn(e.getClass().getName(), e);
        if (e instanceof ConstraintViolationException){
            ConstraintViolationException ex = (ConstraintViolationException) e;
            return ResponseResultUtil.fail(BAD_REQUEST,
                    ex.getConstraintViolations().stream()
                            .filter(Objects::nonNull)
                            .map( cv -> cv.getMessage() )
                            .collect(Collectors.joining("; ")));
        }
        else if (e instanceof MethodArgumentNotValidException) {
            // BeanValidation exception
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            return ResponseResultUtil.fail(BAD_REQUEST,
                    ex.getBindingResult().getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining("; ")));
        } else if (e instanceof ValidationException) {
            // BeanValidation GET simple param
            ValidationException ex = (ValidationException) e;

            return ResponseResultUtil.fail(BAD_REQUEST,
                    ex.getCause().getMessage());
        } else if (e instanceof BindException) {
            // BeanValidation GET object param
            BindException ex = (BindException) e;
            return ResponseResultUtil.fail(BAD_REQUEST,
                    ex.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining("; ")));
        }else if (e instanceof UnexpectedTypeException){
            UnexpectedTypeException ex = (UnexpectedTypeException) e;
            return ResponseResultUtil.fail(BAD_REQUEST, ex.getMessage());
        }

        return ResponseResultUtil.fail(BAD_REQUEST);
    }


}
