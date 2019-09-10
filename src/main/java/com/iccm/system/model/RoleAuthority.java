package com.iccm.system.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

/**
 * 角色权限对象 role_authority
 * 
 * @author gxj
 * @date 2019-09-09
 */
@Data
public class RoleAuthority
{
    private static final long serialVersionUID = 1L;

    /** 角色id */
    private Long roleId;

    /** 权限标识 */
    private String authority;

    public RoleAuthority(Long roleId, String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }

    public RoleAuthority() {
    }
}
