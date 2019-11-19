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
    @ResponseStatus(HttpStatus.OK)
    public JsonResult loginExceptionHandle(LoginException ex) {
        return JsonResult.error(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(PermissionsException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult permissionsExceptionHandle(PermissionsException ex){
        return JsonResult.error(403,ex.getMessage());
    }

    @ExceptionHandler(ParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult parameterExceptionHandle(ParameterException ex){
        return JsonResult.error(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult businessExceptionHandle(BusinessException ex){
        return JsonResult.error(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult exceptionHandle(Exception ex){
        ex.printStackTrace();
        return JsonResult.error(500,"系统内部发生异常");
    }
}
