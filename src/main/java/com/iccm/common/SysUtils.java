package com.iccm.common;

import com.iccm.common.enums.OperatorType;
import com.iccm.system.mapper.SysUserMapper;
import com.iccm.system.model.AppTokenInfo;
import com.iccm.system.model.SysUser;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/9/10.
 */
public class SysUtils {

    /**
     * 获得一个用户
     * <功能详细描述>获得当前session中存放的用户，
     *
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static SysUser getSysUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String type = request.getHeader("request_source");
        SysUserMapper sysUserMapper = SpringContextHolder.getBean("sysUserMapper");
        Long userId = -1l;
        if("PC".equals(type)){
            SysUser  user=(SysUser) request.getSession().getAttribute("user");
            userId = user.getUserId();
        }else if("APP".equals(type)){
            CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
            AppTokenInfo appTokenInfo = cacheManager.getCache(CacheName.APPTOKENS).get(request.getHeader("token"),AppTokenInfo.class);
            userId = appTokenInfo.getSysUser().getUserId();
        }else if("OTHER".equals(type)){
            userId = -1L;
        }
        return sysUserMapper.selectUserById(userId);
    }
}
