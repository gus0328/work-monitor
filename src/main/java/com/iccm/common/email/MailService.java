package com.iccm.common.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/9/17.
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender; //框架自带的

    @Async  //意思是异步调用这个方法
    public void sendMail(String title, String content, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("15229031630@163.com"); // 发送人的邮箱
        message.setSubject(title); //标题
        message.setTo(email); //发给谁  对方邮箱
        message.setText(content); //内容
        mailSender.send(message); //发送
    }

}
