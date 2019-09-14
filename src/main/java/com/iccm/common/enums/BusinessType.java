package com.iccm.common.enums;

/**
 * 业务操作类型
 * 
 * @author gxj
 */
public enum BusinessType
{
    /**
     * 其它
     */
    OTHER("其它"),

    /**
     * 新增
     */
    INSERT("新增"),

    /**
     * 修改
     */
    UPDATE("修改"),

    /**
     * 删除
     */
    DELETE("删除"),

    /**
     * 授权
     */
    GRANT("授权"),

    /**
     * 导出
     */
    EXPORT("导出"),

    /**
     * 导入
     */
    IMPORT("导入"),

    /**
     * 强退
     */
    FORCE("强退"),

    /**
     * 生成代码
     */
    GENCODE("生成代码"),
    
    /**
     * 清空
     */
    CLEAN("清空"),

    /**
     * 查询
     */
    QUERY("查询");

    private final String name;

    BusinessType(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    BusinessType() {
        this.name = "";
    }
}
