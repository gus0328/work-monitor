package com.iccm.system.model;

import com.iccm.common.annotation.Excel;
import lombok.Data;

/**
 * 权限标识对象 sys_authorities
 * 
 * @author gxj
 * @date 2019-09-08
 */
@Data
public class SysAuthorities extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 授权标识 */
    private String authority;

    /** 授权名称 */
    @Excel(name = "授权名称")
    private String authorityName;

    /** 模块名称 */
    @Excel(name = "模块名称")
    private String parentName;

    /** 排序号 */
    @Excel(name = "排序号")
    private Long orderNum;

    /** 模块url */
    @Excel(name = "模块url")
    private String parentUrl;

    public SysAuthorities(String authority, String authorityName, String parentName, Long orderNum, String parentUrl) {
        this.authority = authority;
        this.authorityName = authorityName;
        this.parentName = parentName;
        this.orderNum = orderNum;
        this.parentUrl = parentUrl;
    }

    public SysAuthorities() {
    }
}
