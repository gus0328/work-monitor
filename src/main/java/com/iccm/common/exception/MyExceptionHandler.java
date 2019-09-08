package com.iccm.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 全局异常处理器
 * Created by wangfan on 2018-02-22 上午 11:29.
 */
@ControllerAdvice
public class MyExceptionHandler {
    private Logger logger = LoggerFactory.getLogger("MyExceptionHandler");


}
