package com.iccm.common.websocket;

/**
 * Created by 82415 on 2018/9/20.
 */
public enum SystemNoticeType {

    /*登录超时*/LineOutTime("lineOutTime"),/*被踢下线*/KickOffTheLine("kickOffTheLine"),ContractExpiry("contractExpiry");
    private final String type;

    private SystemNoticeType(String type)
    {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
