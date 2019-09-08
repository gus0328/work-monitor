package com.iccm.common.exception;

/**
 * Created by Administrator on 2019/9/7.
 */
public class PermissionsException  extends RuntimeException {

    private final int code;

    private final String message;

    public PermissionsException(int code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
