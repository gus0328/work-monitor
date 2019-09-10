package com.iccm.common.exception;

import com.iccm.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * Created by wangfan on 2018-02-22 上午 11:29.
 */
@RestControllerAdvice
public class MyExceptionHandler {
    private Logger logger = LoggerFactory.getLogger("MyExceptionHandler");

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public LoginException loginExceptionHandle(LoginException ex) {
        return ex;
    }

    @ExceptionHandler(PermissionsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public PermissionsException permissionsExceptionHandle(PermissionsException ex){
        return ex;
    }

    @ExceptionHandler(ParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ParameterException parameterExceptionHandle(ParameterException ex){
        return ex;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BusinessException businessExceptionHandle(BusinessException ex){
        return ex;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult exceptionHandle(Exception ex){
        return JsonResult.error().put("message","系统内部发生异常");
    }
}
