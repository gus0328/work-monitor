package com.iccm.system.controller;

import com.iccm.common.CacheName;
import com.iccm.common.JsonResult;
import com.iccm.system.model.LoginParams;
import com.iccm.system.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
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

    @Autowired
    private CacheManager cacheManager;

    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginParams loginParams,HttpServletRequest request) {
        return loginService.login(loginParams,request);
    }

    @GetMapping("/logout")
    public void loginOut(HttpSession session){
        session.invalidate();
    }

    @GetMapping("/verifyPcToken")
    public JsonResult verifyPcToken(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            return JsonResult.ok().put("data",false);
        }else{
            return JsonResult.ok().put("data",true);
        }
    }
}
