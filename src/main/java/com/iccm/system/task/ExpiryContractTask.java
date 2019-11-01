package com.iccm.system.task;

import com.alibaba.fastjson.JSONObject;
import com.iccm.common.Constants;
import com.iccm.common.email.MailService;
import com.iccm.common.utils.UUIDUtil;
import com.iccm.common.websocket.MessageDeal;
import com.iccm.common.websocket.MessageType;
import com.iccm.common.websocket.SystemNoticeType;
import com.iccm.system.mapper.ContractMapper;
import com.iccm.system.mapper.MessageMapper;
import com.iccm.system.mapper.UserMessageMapper;
import com.iccm.system.model.Contract;
import com.iccm.system.model.Message;
import com.iccm.system.model.SysUser;
import com.iccm.system.model.UserMessage;
import com.iccm.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/9/17.
 */
@Component
public class ExpiryContractTask {

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private UserMessageMapper userMessageMapper;

    @Scheduled(cron = "0 15 10 ? * *")
    public void expiryContract(){
        List<Contract> list = contractMapper.selectExpiryContract();
        Map<String,String> map = new HashMap();
        list.forEach(contract -> {
            String str = map.get(contract.getWarnName());
            if(StringUtils.isBlank(str)){
                map.put(contract.getWarnName(),"已下合同已超期:</br>合同编号:【"+contract.getContractNo()+"】");
            }else{
                map.put(contract.getWarnName(),str+"</br>合同编号:【"+contract.getContractNo()+"】");
            }
            contract.setStatus(1);
            contractMapper.updateContract(contract);
        });
        map.forEach((key,value) ->{
            SysUser sysUser = sysUserService.selectUserByLoginName(key);
            String messageId = UUIDUtil.randomUUID32();
            Message message = new Message(messageId,Constants.CONTRACT_WARN,value,Constants.SYS_NOTICE,new Date(),SystemNoticeType.ContractExpiry.getType(),0);
            messageMapper.insertMessage(message);
            UserMessage userMessage = new UserMessage(messageId,sysUser.getUserId());
            userMessageMapper.insertUserMessage(userMessage);
            int count = messageMapper.queryUnreadCount(sysUser.getUserId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(MessageDeal.POINTUSER,key);
            jsonObject.put(MessageDeal.TYPE, MessageType.SystemNotice.getType());
            jsonObject.put(MessageDeal.NOTICE, SystemNoticeType.ContractExpiry.getType());
            jsonObject.put(MessageType.Message.getType(),count);
            try {
                MessageDeal.sendMessageToPointUser(jsonObject);//通知用户登录超时
            } catch (Exception e) {
                //do nothing
            }
            String content = value.replace("</br>","\n");
            mailService.sendMail(Constants.CONTRACT_WARN,content,"437016007@qq.com");
        });
    }
}
