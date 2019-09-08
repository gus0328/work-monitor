package com.iccm.system.model;

import lombok.Data;

/**
 * Created by Administrator on 2019/8/27.
 */
@Data
public class LoginParams {

    private String username;

    private String password;

    private String validateCode;

}
