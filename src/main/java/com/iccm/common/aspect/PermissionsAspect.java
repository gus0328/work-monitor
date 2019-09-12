package com.iccm.common.aspect;

import com.iccm.common.SysUtils;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.exception.PermissionsException;
import com.iccm.system.mapper.RoleAuthorityMapper;
import com.iccm.system.mapper.SysUserRoleMapper;
import com.iccm.system.model.RoleAuthority;
import com.iccm.system.model.SysUser;
import com.iccm.system.service.IRoleAuthorityService;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 2019/9/7.
 */
@Aspect
@Component
@Order(1)
public class PermissionsAspect {

    private static final String point = "execution(* com.iccm.system.controller..*(..))";

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    //切点
    @Pointcut(value = point)
    public void point() {

    }

    @Before("point()")
    public void before(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        if (requiresPermissions != null) {
            if(!verifyUserPermissions(requiresPermissions.authorities())){
                throw new PermissionsException(401,"你没有权限操作");
            }
        }
    }

    /**
     * 验证用户是否拥有此权限
     * @param permissions
     * @return
     */
    public boolean verifyUserPermissions(String permissions){
        List<Long> roles = sysUserRoleMapper.getRolesIdByUserId(SysUtils.getSysUser().getUserId());
        for(Long role:roles){
            int count = roleAuthorityMapper.verifyIsHasAuth(new RoleAuthority(role,permissions));
            if(count>0){
                return true;
            }
        }
        return false;
    }
}
