package com.iccm.system.controller;

import com.iccm.common.JsonResult;
import com.iccm.system.model.LoginParams;
import com.iccm.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/9/8.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginParams loginParams, HttpSession session,HttpServletRequest request) {
        return loginService.login(loginParams,session,request);
    }

    @GetMapping("/logout")
    public void loginOut(HttpSession session){
        session.invalidate();
    }
}
