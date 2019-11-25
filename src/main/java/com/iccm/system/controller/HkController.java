package com.iccm.system.controller;

import com.iccm.common.CacheName;
import com.iccm.common.JsonResult;
import com.iccm.system.hkcontrolls.lib.HCNetSDK;
import com.iccm.system.hkcontrolls.lib.HKNetSDK;
import com.sun.jna.NativeLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/11/19.
 */
@RestController
@RequestMapping("/hkControl")
public class HkController {

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/login")
    public JsonResult login(String username, String password, int port, String ipAddress, HttpServletRequest request){
        try{
            NativeLong nativeLong = HKNetSDK.login(ipAddress,port,username,password,new HCNetSDK.NET_DVR_DEVICEINFO_V30());
            String token = request.getHeader("token");
            String key = token+ipAddress;
            cacheManager.getCache(CacheName.HKCONTROL).put(key,nativeLong);
        }catch (Exception e){
            return JsonResult.error(ipAddress+"登录海康视屏云台控制失败");
        }
        return JsonResult.ok();
    }

    @GetMapping("/logout")
    public JsonResult logout(String ipAddress,HttpServletRequest request){
        try{
            String token = request.getHeader("token");
            String key = token+ipAddress;
            NativeLong nativeLong = cacheManager.getCache(CacheName.HKCONTROL).get(key,NativeLong.class);
            HKNetSDK.logout(nativeLong);
        }catch (Exception e){
            return JsonResult.ok(ipAddress+"注销云台控制失败");
        }
        return JsonResult.ok();
    }

    @GetMapping("/control")
    public JsonResult control(String ipAddress,int command,int status,HttpServletRequest request){
        try {
            String token = request.getHeader("token");
            String key = token+ipAddress;
            NativeLong nativeLong = cacheManager.getCache(CacheName.HKCONTROL).get(key,NativeLong.class);
            NativeLong channel = new NativeLong(1);
            HKNetSDK.ptzControl(nativeLong,channel,command,4,status);
        }catch (Exception e){
            return JsonResult.error(ipAddress+"云台控制失败");
        }
        return JsonResult.ok();
    }
}
