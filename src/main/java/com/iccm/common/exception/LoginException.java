package com.iccm.common.exception;

import lombok.Data;

/**
 * Created by Administrator on 2019/9/9.
 */
@Data
public class LoginException extends RuntimeException {

    private final int code;

    private final String message;

    public LoginException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
