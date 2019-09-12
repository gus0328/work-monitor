package com.iccm.common.websocket;

/**
 * Created by a on 2018/8/16.
 */
public enum  MessageType {
    /*消息*/Message("message"),/*系统通知*/SystemNotice("systemNotice"),/*代办*/Agency("agency");
    private final String type;

    private MessageType(String type)
    {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
