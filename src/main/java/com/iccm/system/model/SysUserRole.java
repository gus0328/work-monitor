package com.iccm.system.model;

import lombok.Data;

/**
 * 用户和角色关联 sys_user_role
 * 
 * @author gxj
 */
@Data
public class SysUserRole
{
    /** 用户ID */
    private Long userId;
    
    /** 角色ID */
    private Long roleId;
}
