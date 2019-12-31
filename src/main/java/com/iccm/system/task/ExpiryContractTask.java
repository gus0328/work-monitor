package com.iccm.system.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iccm.common.Constants;
import com.iccm.common.config.ApplicationListener;
import com.iccm.common.email.MailService;
import com.iccm.common.push.TemplateType;
import com.iccm.common.utils.DateUtil;
import com.iccm.common.utils.MessageUtil;
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
    private ISysUserService sysUserService;

    @Autowired
    private MessageUtil messageUtil;

    @Scheduled(cron = "0/30 * * * * *")
    public void expiryContract(){
        List<Contract> list = contractMapper.selectExpiryContract();
        ApplicationListener.executorService.submit(()->{
            list.forEach(contract -> {
                Date latestDate = contract.getLatestWarnTime();
                if(latestDate==null){
                    boolean flag = true;
                    try{
                        messageUtil.pushtoSingle(contract.getWarnName(),Constants.CONTRACT_WARN,contract.toString(),contract.getNotice(), JSON.toJSONString(contract), TemplateType.notice,Constants.SYS_NOTICE, SystemNoticeType.ContractExpiry.getType());
                    }catch (Exception e){
                        flag = false;
                    }
                   if(flag)
                    updateLatestTime(contract);
                }else{
                    int time = Integer.parseInt(contract.getWarnTime());
                    Date comDate = DateUtil.getAppointDate(latestDate,time);
                    int n =DateUtil.compareToDate(comDate,new Date());
                    if(n!=1){
                        boolean flag = true;
                        try{
                            messageUtil.pushtoSingle(contract.getWarnName(),Constants.CONTRACT_WARN,contract.toString(),contract.getNotice(), JSON.toJSONString(contract), TemplateType.notice,Constants.SYS_NOTICE, SystemNoticeType.ContractExpiry.getType());
                        }catch (Exception e){
                            flag = false;
                        }
                        if(flag)
                        updateLatestTime(contract);
                    }
                }
            });
        });
    }

    /**
     * 更新通知时间
     * @param contract
     */
    public void updateLatestTime(Contract contract){
        Contract updateContract = new Contract();
        updateContract.setId(contract.getId());
        updateContract.setLatestWarnTime(new Date());
        contractMapper.updateContract(updateContract);
    }
}
