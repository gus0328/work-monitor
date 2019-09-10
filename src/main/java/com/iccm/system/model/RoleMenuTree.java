package com.iccm.system.model;

import lombok.Data;

/**
 * Created by Administrator on 2019/9/9.
 */
@Data
public class RoleMenuTree {

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 菜单标题
     */
    private String menuTitle;

    /**
     * 此单路径
     */
    private String menuUrl;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 状态（true该角色有此菜单,false该角色没有此菜单）
     */
    private boolean flag;

}
