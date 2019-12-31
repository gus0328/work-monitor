package com.iccm.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.iccm.common.Constants;
import com.iccm.common.push.GetuiPush;
import com.iccm.common.push.TemplateType;
import com.iccm.common.websocket.MessageDeal;
import com.iccm.common.websocket.MessageType;
import com.iccm.common.websocket.SystemNoticeType;
import com.iccm.system.mapper.MessageMapper;
import com.iccm.system.mapper.SysUserMapper;
import com.iccm.system.mapper.UserMessageMapper;
import com.iccm.system.model.Message;
import com.iccm.system.model.SysUser;
import com.iccm.system.model.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2019/12/2.
 */
@Component
public class MessageUtil {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageDeal messageDeal;

    @Autowired
    private GetuiPush getuiPush;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserMessageMapper userMessageMapper;

    @Transactional
    public void pushtoSingle(String loginName,String title,String content,String noticeContent,String dataOrUrl,TemplateType templateType,String sendUser,String noticeType){
        SysUser sysUser = sysUserMapper.selectUserByLoginName(loginName);
        String messageId = UUIDUtil.randomUUID32();
        Message message = new Message(messageId, title,content,sendUser,new Date(), noticeType,0);
        messageMapper.insertMessage(message);
        UserMessage userMessage = new UserMessage(messageId,sysUser.getUserId());
        userMessageMapper.insertUserMessage(userMessage);
        try{
            int count = messageMapper.queryUnreadCount(sysUser.getUserId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(MessageDeal.POINTUSER,loginName);
            jsonObject.put(MessageDeal.TYPE, MessageType.SystemNotice.getType());
            jsonObject.put(MessageDeal.NOTICE, noticeType);
            jsonObject.put(MessageType.Message.getType(),count);
            MessageDeal.sendMessageToPointUser(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        getuiPush.pushtoSingle(sysUser.getDeviceId(),title,noticeContent,dataOrUrl,templateType);
    }
}
