package com.iccm.common.enums;

/**
 * 操作人类别
 * 
 * @author gxj
 */
public enum OperatorType
{
    /**
     * 其它
     */
    OTHER("其他"),

    /**
     * 后台用户
     */
    MANAGE("PC"),

    /**
     * 手机端用户
     */
    MOBILE("APP");

    private final String name;

    OperatorType(String name) {
        this.name = name;
    }

    OperatorType() {
        this.name = "";
    }

    public String getName() {
        return name;
    }
}
