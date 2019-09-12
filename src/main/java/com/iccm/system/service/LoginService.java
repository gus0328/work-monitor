package com.iccm.system.service;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.iccm.common.CacheName;
import com.iccm.common.JsonResult;
import com.iccm.common.TokenUtils;
import com.iccm.common.properties.SystemProperties;
import com.iccm.common.utils.IpUtils;
import com.iccm.common.websocket.MessageDeal;
import com.iccm.common.websocket.MessageType;
import com.iccm.common.websocket.SystemNoticeType;
import com.iccm.system.model.LoginParams;
import com.iccm.system.model.SysUser;
import com.wangfan.endecrypt.utils.EndecryptUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private SystemProperties systemProperties;

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
        session.setMaxInactiveInterval(systemProperties.getSessionTime());
        sysUserService.updateUserInfo(user);
        session.setAttribute("user",user);
        String oldSession = cacheManager.getCache(CacheName.SESSIONS).get(user.getUserId(),String.class);
        if(StringUtils.isNotBlank(oldSession)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(MessageDeal.POINTUSER,user.getLoginName());
            jsonObject.put(MessageDeal.TYPE, MessageType.SystemNotice.getType());
            jsonObject.put(MessageDeal.NOTICE, SystemNoticeType.KickOffTheLine.getType());//重复登录踢下线
            try{
                MessageDeal.sendMessageToPointUser(jsonObject);
            }catch (Exception e){
                //发送消息失败
            }
        }
        cacheManager.getCache(CacheName.SESSIONS).put(user.getUserId(),session.getId());
        String token = tokenUtils.createPcToken(loginParams.getUsername(),session.getId());
        cacheManager.getCache(CacheName.PCTOKENS).put(token,loginParams.getUsername());
        return JsonResult.ok("登录成功").put("access_token",token);
    }

}
