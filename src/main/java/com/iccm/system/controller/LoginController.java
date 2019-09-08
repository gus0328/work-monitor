package com.iccm.system.controller;

import com.iccm.common.JsonResult;
import com.iccm.system.model.LoginParams;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/9/8.
 */
@RestController
@RequestMapping("/main")
public class LoginController {

    @GetMapping("/user/info")
    public JsonResult userInfo(HttpServletRequest request) {
        String[] array = {"system","depart","dict","menu","anthorities"};
        return JsonResult.ok().put("permission",array);
    }

    @PostMapping("/user/login")
    public JsonResult login(@RequestBody LoginParams loginParams, HttpSession session) {
        return JsonResult.ok("登录成功").put("access_token", "qweeeeeeeeeeeeeeeeeeeeeeeeee");
    }
}
