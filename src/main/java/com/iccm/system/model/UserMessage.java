package com.iccm.system.model;

import com.iccm.common.annotation.Excel;
import lombok.Data;

/**
 * Created by Administrator on 2019/9/17.
 */
@Data
public class UserMessage {

    /** 消息id */
    private String messageId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    public UserMessage(String messageId, Long userId) {
        this.messageId = messageId;
        this.userId = userId;
    }

    public UserMessage() {
    }
}
