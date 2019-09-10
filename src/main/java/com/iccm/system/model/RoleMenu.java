package com.iccm.system.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

/**
 * 角色菜单对象 role_menu
 * 
 * @author gxj
 * @date 2019-09-09
 */
@Data
public class RoleMenu
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;

    public RoleMenu(Long roleId, Long menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public RoleMenu() {
    }
}
