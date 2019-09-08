package com.iccm.system.model;

import com.iccm.common.annotation.Excel;
import lombok.Data;

/**
 * 权限对象 role
 * 
 * @author gxj
 * @date 2019-09-08
 */
@Data
public class Role extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private Long roleId;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String roleName;

    /** 角色权限字符串 */
    @Excel(name = "角色权限字符串")
    private String roleKey;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer roleSort;

}
