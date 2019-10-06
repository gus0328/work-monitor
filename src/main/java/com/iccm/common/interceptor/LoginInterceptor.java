package com.iccm.common.interceptor;

import com.iccm.common.CacheName;
import com.iccm.common.enums.OperatorType;
import com.iccm.common.exception.LoginException;
import com.iccm.common.properties.SystemProperties;
import com.iccm.system.model.AppTokenInfo;
import com.iccm.system.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * crate by gxj
 * 验证用户登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

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
		String type = request.getHeader("request_source");
		if("PC".equals(type)){
			request.setAttribute("source", OperatorType.MANAGE);
			return pcHandle(request,response,arg2);
		}else if("APP".equals(type)){
			request.setAttribute("source", OperatorType.MOBILE);
			return appHandle(request,response,arg2);
		}else if("OTHER".equals(type)){
			request.setAttribute("source", OperatorType.OTHER);
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
		HttpSession session = request.getSession();
		SysUser user =  (SysUser) session.getAttribute("user");
		if(user == null){
			throw new LoginException(401,"用户未登录");
		}else{
			String sessionId = "";
			try{
				sessionId = (String)cacheManager.getCache(CacheName.SESSIONS).get(user.getUserId()).get();
			}catch (NullPointerException e){
				session.invalidate();
				throw new LoginException(466,"系统已重启，您需要重新登录");
			}
			if(!session.getId().equals(sessionId)){
				session.removeAttribute("user");
				throw new LoginException(401,"用户被踢下线");
			}
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
		AppTokenInfo appTokenInfo = cacheManager.getCache(CacheName.APPTOKENS).get(request.getHeader("token"),AppTokenInfo.class);
		if(appTokenInfo==null){
			throw new LoginException(466,"需要重新登录");
		}else{
			if(new Date().getTime()/1000-appTokenInfo.getTime()-systemProperties.getAppTokenTime()>0){
				throw new LoginException(466,"登录超时");
			}
		}
		return true;
	}

	public boolean otherHandle(HttpServletRequest request, HttpServletResponse response, Object arg2){
		return false;
	}
}
