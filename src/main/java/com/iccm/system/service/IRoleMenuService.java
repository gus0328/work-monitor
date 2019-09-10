package com.iccm.system.service;

import com.iccm.system.model.RoleMenu;
import com.iccm.system.model.RoleMenuTree;

import java.util.List;

/**
 * 角色菜单Service接口
 * 
 * @author gxj
 * @date 2019-09-09
 */
public interface IRoleMenuService 
{
    /**
     * 查询角色菜单
     * 
     * @param roleId 角色菜单ID
     * @return 角色菜单
     */
    public RoleMenu selectRoleMenuById(Long roleId);

    /**
     * 查询角色菜单列表
     * 
     * @param roleMenu 角色菜单
     * @return 角色菜单集合
     */
    public List<RoleMenu> selectRoleMenuList(RoleMenu roleMenu);

    /**
     * 新增角色菜单
     * 
     * @param roleMenu 角色菜单
     * @return 结果
     */
    public int insertRoleMenu(RoleMenu roleMenu);

    /**
     * 修改角色菜单
     * 
     * @param roleMenu 角色菜单
     * @return 结果
     */
    public int updateRoleMenu(RoleMenu roleMenu);

    /**
     * 批量删除角色菜单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleMenuByIds(String ids);

    /**
     * 删除角色菜单信息
     * 
     * @param roleId 角色菜单ID
     * @return 结果
     */
    public int deleteRoleMenuById(Long roleId);

    /**
     * 删除菜单权限
     * @param roleMenu
     * @return
     */
    public int deleteByMenuIdAndRoleId(RoleMenuTree roleMenu);
}
