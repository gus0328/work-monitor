package com.iccm.common.aspect;

import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.exception.PermissionsException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/9/7.
 */
@Aspect
@Component
@Order(1)
public class PermissionsAspect {

    private static final String point = "execution(* com.iccm.system.controller..*(..))";

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
//        if(permissions.equals("post:system:authorities:sync")){
//            return true;
//        }else{
//            return false;
//        }
        return true;
    }
}
