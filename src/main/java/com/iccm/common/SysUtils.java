package com.iccm.common;

import com.iccm.system.mapper.SysUserMapper;
import com.iccm.system.model.SysUser;
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
        SysUser  user=(SysUser) request.getSession().getAttribute("user");
        SysUserMapper sysUserMapper = SpringContextHolder.getBean("sysUserMapper");
        return sysUserMapper.selectUserById(user.getUserId());
    }
}
