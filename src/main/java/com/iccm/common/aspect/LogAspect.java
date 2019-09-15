package com.iccm.common.aspect;


import com.alibaba.fastjson.JSON;
import com.iccm.common.CacheName;
import com.iccm.common.ServletUtils;
import com.iccm.common.SysUtils;
import com.iccm.common.annotation.Log;
import com.iccm.common.config.ApplicationListener;
import com.iccm.common.enums.BusinessStatus;
import com.iccm.common.enums.OperatorType;
import com.iccm.common.utils.AddressUtils;
import com.iccm.common.utils.IpUtils;
import com.iccm.common.utils.StringUtils;
import com.iccm.system.mapper.SysOperLogMapper;
import com.iccm.system.model.AppTokenInfo;
import com.iccm.system.model.SysOperLog;
import com.iccm.system.model.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 操作日志记录处理
 * 
 * @author gxj
 */
@Aspect
@Component
@Order(1)
public class LogAspect
{
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CacheManager cacheManager;

    // 配置织入点
    @Pointcut("@annotation(com.iccm.common.annotation.Log)")
    public void logPointCut()
    {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint)
    {
        handleLog(joinPoint, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e)
    {
        try{
            AppTokenInfo appTokenInfo = new AppTokenInfo();
            OperatorType operatorType = (OperatorType) request.getAttribute("source");
            switch (operatorType){
                case MANAGE:
                    appTokenInfo.setSysUser((SysUser) request.getSession().getAttribute("user"));
                    break;
                case MOBILE:
                    AppTokenInfo appTokenInfo1 = cacheManager.getCache(CacheName.APPTOKENS).get(request.getHeader("token"), AppTokenInfo.class);
                    appTokenInfo.setSysUser(appTokenInfo1.getSysUser());
                    break;
            }
            String operUrl = request.getRequestURI();
            String method = request.getMethod();
            Map map = request.getParameterMap();
            String ip = IpUtils.getIpAddr(request);
            String location = AddressUtils.getRealAddressByIP(ip);
            ApplicationListener.executorService.submit(() -> {
                try
                {
                    // 获得注解
                    Log controllerLog = getAnnotationLog(joinPoint);
                    if (controllerLog == null)
                    {
                        return;
                    }

                    // *========数据库日志=========*//
                    SysOperLog operLog = new SysOperLog();
                    operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
                    // 请求的地址
                    operLog.setOperIp(ip);
                    operLog.setOperLocation(location);
                    operLog.setOperUrl(operUrl);
                    SysUser currentUser = appTokenInfo.getSysUser();
                    if (currentUser != null)
                    {
                        operLog.setOperName(currentUser.getLoginName());
                        if (StringUtils.isNotNull(currentUser.getDept())
                                && StringUtils.isNotEmpty(currentUser.getDept().getDeptName()))
                        {
                            operLog.setDeptName(currentUser.getDept().getDeptName());
                        }
                    }

                    if (e != null)
                    {
                        operLog.setStatus(BusinessStatus.FAIL.ordinal());
                        operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
                    }
                    // 设置方法名称
                    String className = joinPoint.getTarget().getClass().getName();
                    String methodName = joinPoint.getSignature().getName();
                    operLog.setMethod(className + "." + methodName + "()");
                    // 设置请求方式
                    operLog.setRequestMethod(method);
                    // 处理设置注解上的参数
                    getControllerMethodDescription(controllerLog, operLog,operatorType,map);
                    // 保存数据库
                    sysOperLogMapper.insertOperlog(operLog);
                }
                catch (Exception exp)
                {
                    // 记录本地异常日志
                    log.error("==前置通知异常==");
                    log.error("异常信息:{}", exp.getMessage());
                    exp.printStackTrace();
                }
            });
        }catch (Exception exception){
// 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exception.getMessage());
            exception.printStackTrace();
        }

    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log 日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(Log log, SysOperLog operLog,OperatorType operatorType,Map map) throws Exception
    {
        // 设置action动作
        operLog.setBusinessType(log.businessType().getName());
        // 设置标题
        operLog.setTitle(log.title());
        // 设置操作人类别
        operLog.setOperatorType(operatorType.getName());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData())
        {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(operLog,map);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(SysOperLog operLog,Map map) throws Exception
    {
        String params = JSON.toJSONString(map);
        operLog.setOperParam(StringUtils.substring(params, 0, 2000));
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
