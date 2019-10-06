package com.iccm.common.interceptor;

import com.iccm.common.CacheName;
import com.iccm.common.properties.SystemProperties;
import com.iccm.system.model.AppTokenInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Administrator on 2019/10/6.
 */
@Component
public class UploadInterceptor implements HandlerInterceptor {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SystemProperties systemProperties;

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2)throws Exception{
        String type = request.getParameter("resource");
        if("PC".equals(type)){
            return pcHandle(request,response,arg2);
        }else if("APP".equals(type)){
            return appHandle(request,response,arg2);
        }else if("OTHER".equals(type)){
            return otherHandle(request,response,arg2);
        }
        return false;
    }

    /**
     * pc 请求
     * @param request
     * @param response
     * @param arg2
     * @return
     */
    public boolean pcHandle(HttpServletRequest request, HttpServletResponse response, Object arg2){
        String token = request.getParameter("token");
        String loginName = cacheManager.getCache(CacheName.PCTOKENS).get(token,String.class);
        if(StringUtils.isBlank(loginName)){
            return false;
        }
        return true;
    }

    /**
     * app 请求
     * @param request
     * @param response
     * @param arg2
     * @return
     */
    public boolean appHandle(HttpServletRequest request, HttpServletResponse response, Object arg2){
        String token = request.getParameter("token");
        AppTokenInfo appTokenInfo = cacheManager.getCache(CacheName.APPTOKENS).get(token,AppTokenInfo.class);
        if(appTokenInfo==null){
            return false;
        }else{
            if(new Date().getTime()/1000-appTokenInfo.getTime()-systemProperties.getAppTokenTime()>0){
                return false;
            }
        }
        return true;
    }

    public boolean otherHandle(HttpServletRequest request, HttpServletResponse response, Object arg2){
        return false;
    }
}
