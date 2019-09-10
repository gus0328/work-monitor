package com.iccm.system.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2019/9/10.
 */
@Data
public class ResetPwd {

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;
}
