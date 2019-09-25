package com.iccm.system.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;
import java.util.Date;

/**
 * 消息管理对象 message
 * 
 * @author gxj
 * @date 2019-09-17
 */
@Data
public class Message
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 主题 */
    @Excel(name = "主题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String message;

    /** 发送人 */
    @Excel(name = "发送人")
    private String sendUser;

    /** 发送时间 */
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date sendTime;

    /** 消息类型 */
    @Excel(name = "消息类型")
    private String messageType;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    public Message(String title, String message, String sendUser, Date sendTime, String messageType, Integer status) {
        this.title = title;
        this.message = message;
        this.sendUser = sendUser;
        this.sendTime = sendTime;
        this.messageType = messageType;
        this.status = status;
    }

    public Message(String id, String title, String message, String sendUser, Date sendTime, String messageType, Integer status) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.sendUser = sendUser;
        this.sendTime = sendTime;
        this.messageType = messageType;
        this.status = status;
    }

    public Message() {
    }
}
