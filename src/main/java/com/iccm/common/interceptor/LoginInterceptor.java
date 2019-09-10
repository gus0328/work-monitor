package com.iccm.common.interceptor;

import com.iccm.common.CacheName;
import com.iccm.common.exception.LoginException;
import com.iccm.system.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * crate by gxj
 * 验证用户登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private CacheManager cacheManager;

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
		HttpSession session = request.getSession();
		SysUser user =  (SysUser) session.getAttribute("user");
		if(user == null){
			throw new LoginException(401,"用户未登录");
		}else{
			String sessionId = (String)cacheManager.getCache(CacheName.SESSIONS).get(user.getUserId()).get();
			if(!session.getId().equals(sessionId)){
				session.removeAttribute("user");
				throw new LoginException(401,"用户被踢下线");
			}
		}
		return true;
	}
}
