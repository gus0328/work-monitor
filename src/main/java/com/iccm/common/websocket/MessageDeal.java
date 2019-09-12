package com.iccm.common.websocket;

import com.alibaba.fastjson.JSONObject;
import com.iccm.common.CacheName;
import com.iccm.common.SpringContextHolder;
import com.iccm.system.model.SysUser;
import com.iccm.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by a on 2018/8/16.
 */
@ServerEndpoint(value="/iccmMessage/{token}")
@Component
public class MessageDeal {

    private static Logger logger = LoggerFactory.getLogger(MessageDeal.class);

    private SysUser sysUser;

    /**
     * 消息类型
     */
    public static final String TYPE = "type";

    /**
     * 指定用户
     */
    public static final String POINTUSER = "pointUser";

    /**
     * 系统通知
     */
    public static final String NOTICE = "notice";

    public static Map<String,Session> map = new HashMap<>();

    //连接时执行
    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session) throws Exception {
        CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
        String userCode = cacheManager.getCache(CacheName.PCTOKENS).get(token,String.class);
        if(StringUtils.isBlank(userCode)){
            throw new Exception("请先登录再连接webSocket");
        }
        ISysUserService sysUserService = SpringContextHolder.getBean("sysUserService");
        sysUser = sysUserService.selectUserByLoginName(userCode);
        map.put(sysUser.getLoginName(),session);
        System.out.print(map.size()+"--------------------------------------------------------");
        logger.info(sysUser.getUserName()+"已上线");
    }

    //关闭时执行
    @OnClose
    public void onClose(){
        logger.info(sysUser.getUserName()+"已下线");
        map.remove(sysUser.getLoginName());
    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println(message);
    }

    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error){
        logger.info(sysUser.getLoginName()+"连接时发生错误！"+error.getLocalizedMessage());
    }

    /**
     * 向所有在线用户推送消息
     */
    public static void sendMessageToAllOnLineUsers(JSONObject jsonObject)throws Exception{
        for (String userCode:map.keySet()) {
            map.get(userCode).getBasicRemote().sendText(jsonObject.toJSONString());
        }
    }

    /**
     * 向发送消息用户回复消息
     * @param session
     * @param message
     */
    public static void sendMessageToAssignUser(Session session, String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("推送消息失败");
        }
    }

    /**
     * 向指定的用户发送消息
     * @param jsonObject 发送消息的用户
     */
    public static void sendMessageToPointUser(JSONObject jsonObject)throws Exception{
        Session session = map.get(jsonObject.getString(POINTUSER));
        session.getBasicRemote().sendText(jsonObject.toJSONString());
        String notice = (String) jsonObject.get(MessageDeal.NOTICE);
        if(SystemNoticeType.KickOffTheLine.getType().equals(notice)||SystemNoticeType.LineOutTime.getType().equals(notice)){
            session.close();//登录超时，异地登录用户sesion关闭
        }
    }
}
