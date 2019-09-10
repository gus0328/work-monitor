package com.iccm.system.service;

import com.google.code.kaptcha.Constants;
import com.iccm.common.CacheName;
import com.iccm.common.JsonResult;
import com.iccm.common.utils.IpUtils;
import com.iccm.system.model.LoginParams;
import com.iccm.system.model.SysUser;
import com.wangfan.endecrypt.utils.EndecryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Administrator on 2019/9/10.
 */
@Service
public class LoginService {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private CacheManager cacheManager;

    @Transactional
    public JsonResult login(LoginParams loginParams, HttpSession session, HttpServletRequest request) {
        if(!session.getAttribute(Constants.KAPTCHA_SESSION_KEY).equals(loginParams.getValidateCode())){
            return JsonResult.error("验证码错误");
        }
        SysUser user = sysUserService.selectUserByLoginName(loginParams.getUsername());
        if (user == null) {
            return JsonResult.error("账号不存在");
        } else if (!user.getPassword().equals(EndecryptUtils.encrytMd5(loginParams.getPassword()))) {
            return JsonResult.error("密码错误");
        } else if ("1".equals(user.getStatus())) {
            return JsonResult.error("账号被锁定");
        }
        user.setLoginIp(IpUtils.getIpAddr(request));
        user.setLoginDate(new Date());
        user.setSalt(session.getId());
        sysUserService.updateUserInfo(user);
        session.setAttribute("user",user);
        cacheManager.getCache(CacheName.SESSIONS).put(user.getUserId(),session.getId());
        return JsonResult.ok("登录成功").put("access_token","PC");
    }

}
