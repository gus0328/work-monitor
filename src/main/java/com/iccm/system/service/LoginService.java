package com.iccm.system.service;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.iccm.common.CacheName;
import com.iccm.common.JsonResult;
import com.iccm.common.ServletUtils;
import com.iccm.common.TokenUtils;
import com.iccm.common.config.ApplicationListener;
import com.iccm.common.enums.OperatorType;
import com.iccm.common.properties.SystemProperties;
import com.iccm.common.utils.AddressUtils;
import com.iccm.common.utils.IpUtils;
import com.iccm.common.utils.Md5Utils;
import com.iccm.common.websocket.MessageDeal;
import com.iccm.common.websocket.MessageType;
import com.iccm.common.websocket.SystemNoticeType;
import com.iccm.system.mapper.SysLogininforMapper;
import com.iccm.system.model.AppTokenInfo;
import com.iccm.system.model.LoginParams;
import com.iccm.system.model.SysLogininfor;
import com.iccm.system.model.SysUser;
import eu.bitwalker.useragentutils.UserAgent;
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

    @Autowired
    private SysLogininforMapper sysLogininforMapper;

    @Transactional
    public JsonResult login(LoginParams loginParams, HttpSession session, HttpServletRequest request) {
        String type = request.getHeader("request_source");
        JsonResult jsonResult = null;
        if(type.equals("PC")&&!session.getAttribute(Constants.KAPTCHA_SESSION_KEY).equals(loginParams.getValidateCode())){
            jsonResult = JsonResult.ok(-1,"验证码错误");
        }
        SysUser user = sysUserService.selectUserByLoginName(loginParams.getUsername());
        if (jsonResult==null&&user == null) {
            jsonResult = JsonResult.ok(-1,"账号不存在");
        } else if (jsonResult==null&&!user.getPassword().equals(Md5Utils.hash(loginParams.getPassword()))) {
            jsonResult = JsonResult.ok(-1,"密码错误");
        } else if (jsonResult==null&&"1".equals(user.getStatus())) {
            jsonResult = JsonResult.ok(-1,"账号被锁定");
        }
        SysLogininfor sysLogininfor = new SysLogininfor();
        sysLogininfor.setLoginName(loginParams.getUsername());
        sysLogininfor.setLoginTime(new Date());
        if(jsonResult!=null){
            sysLogininfor.setStatus("1");
            sysLogininfor.setMsg((String)jsonResult.get("msg"));
        }else{
            sysLogininfor.setStatus("0");
            sysLogininfor.setMsg("登录成功");
        }
        if("PC".equals(type)){
            //保存日志
            sysLogininfor.setLoginSource(OperatorType.MANAGE.getName());
            saveLoginLog(sysLogininfor,request);
            if(jsonResult!=null){
                return jsonResult;
            }
            user.setLoginIp(sysLogininfor.getIpaddr());
            user.setLoginDate(sysLogininfor.getLoginTime());
            return pcLogin(loginParams,session,user);
        }else if("APP".equals(type)){
            sysLogininfor.setLoginSource(OperatorType.MOBILE.getName());
            saveLoginLog(sysLogininfor,request);
            if(jsonResult!=null){
                return jsonResult;
            }
            user.setLoginIp(sysLogininfor.getIpaddr());
            user.setLoginDate(sysLogininfor.getLoginTime());
            return appLogin(user,sysLogininfor.getLoginTime());
        }else if("OTHER".equals(type)){
            sysLogininfor.setLoginSource(OperatorType.OTHER.getName());
            saveLoginLog(sysLogininfor,request);
            if(jsonResult!=null){
                return jsonResult;
            }
            return otherLogin();
        }
        return JsonResult.error("未知来源登录");
    }

    /**
     * pc登录
     * @param loginParams
     * @param session
     * @param user
     * @return
     */
    public JsonResult pcLogin(LoginParams loginParams, HttpSession session,SysUser user){
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

    /**
     * app登录
     * @param user
     * @param loginDate
     * @return
     */
    public JsonResult appLogin(SysUser user,Date loginDate){
        AppTokenInfo appTokenInfo = new AppTokenInfo();
        appTokenInfo.setTime(loginDate.getTime());
        appTokenInfo.setSysUser(user);
        String token = tokenUtils.createAppToken();
        cacheManager.getCache(CacheName.APPTOKENS).put(token,appTokenInfo);
        return JsonResult.ok("登录成功").put("access_token",token);
    }

    /**
     * 保存日志
     * @param sysLogininfor
     * @param request
     */
    public void saveLoginLog(SysLogininfor sysLogininfor,HttpServletRequest request){
        try{
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            String ip = IpUtils.getIpAddr(request);
            sysLogininfor.setIpaddr(ip);
            //异步添加登录日志
            ApplicationListener.executorService.submit(() ->{
                sysLogininfor.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
                sysLogininfor.setBrowser(userAgent.getBrowser().getName());
                sysLogininfor.setOs(userAgent.getOperatingSystem().getName());
                sysLogininforMapper.insertLogininfor(sysLogininfor);
            });
        }catch (Exception e){
            //不做处理
        }
    }

    /**
     * 其他登录
     * @return
     */
    public JsonResult otherLogin(){
        return JsonResult.error();
    }
}
