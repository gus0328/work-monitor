package com.iccm.common.aspect;

import com.alibaba.fastjson.JSONObject;

import com.iccm.system.mapper.SessionMapper;
import com.iccm.system.mapper.SysUserMapper;
import com.iccm.system.model.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by gxj on 2018/9/21.
 * 自定义监听器
 */
@Aspect
@Component
public class SpringSessionAspect {

    @Pointcut("execution(public * org.springframework.session.jdbc.JdbcOperationsSessionRepository.createSession(..))")
    public void sessionCreate(){}

    @Pointcut("execution(public * org.springframework.session.jdbc.JdbcOperationsSessionRepository.deleteById(..))")
    public void sessionDelete(){}

    @Pointcut("execution(public * org.springframework.session.jdbc.JdbcOperationsSessionRepository.cleanUpExpiredSessions(..))")
    public void sessionOverdue(){}

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SessionMapper sessionMapper;

//    @Autowired
//    private LoginLogService loginLogService;

    /**
     * session 创建
     * @param joinPoint
     */
    @Before("sessionCreate()")
    public void sessionLisner(JoinPoint joinPoint){

    }

//    /**
//     * session 删除
//     * @param joinPoint
//     */
//    @Before("sessionDelete()")
//    public void sessionLisner2(JoinPoint joinPoint){
//        try{
//            String sessionId = (String)joinPoint.getArgs()[0];
//            letUserOutLine(sessionId);
//        }catch (Exception e){
//        }
//    }
//
//    /**
//     * session 过期
//     * @param joinPoint
//     */
//    @Before("sessionOverdue()")
//    public void sessionLisner3(JoinPoint joinPoint){
//        long time = Long.valueOf(System.currentTimeMillis());
//        List<String> sessionIds = sessionMapper.querySessionIdsByOutlinetime(time);
//        for(String sessionId:sessionIds){
//            try{
//                letUserOutLine(sessionId);
//            }catch (Exception e){
//
//            }
//        }
//        //JdbcOperationsSessionRepository//可以和用户表做表连接查询，消息当前系统时间的全部下线。
//    }
//
//    /**
//     * 使用户下线
//     * @param sessionId
//     * @throws Exception
//     */
//    public void letUserOutLine(String sessionId) throws Exception{
////        loginLogService.updateQuitTime(sessionId);
//        SysUser offLineUser = sysUserMapper.queryUserBySessionId(sessionId);
//        sessionMapper.
//        if(offLineUser!=null){
//            ApplicationListener.Application.removeAttribute(offLineUser.getUserCode());
//            sysUserMapper.toOutLine(offLineUser.getId());//用户下线
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put(MessageDeal.POINTUSER,offLineUser.getId());
//            jsonObject.put(MessageDeal.TYPE, MessageType.SystemNotice.getType());
//            jsonObject.put(MessageDeal.NOTICE, SystemNoticeType.LineOutTime.getType());
//            MessageDeal.sendMessageToPointUser(jsonObject);//通知用户登录超时
//        }
//    }
}
