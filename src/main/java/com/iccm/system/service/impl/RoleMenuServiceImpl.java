package com.iccm.system.service.impl;

import java.util.List;

import com.iccm.system.model.RoleMenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.RoleMenuMapper;
import com.iccm.system.model.RoleMenu;
import com.iccm.system.service.IRoleMenuService;
import com.iccm.common.Convert;

/**
 * 角色菜单Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-09
 */
@Service
public class RoleMenuServiceImpl implements IRoleMenuService 
{
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * 查询角色菜单
     * 
     * @param roleId 角色菜单ID
     * @return 角色菜单
     */
    @Override
    public RoleMenu selectRoleMenuById(Long roleId)
    {
        return roleMenuMapper.selectRoleMenuById(roleId);
    }

    /**
     * 查询角色菜单列表
     * 
     * @param roleMenu 角色菜单
     * @return 角色菜单
     */
    @Override
    public List<RoleMenu> selectRoleMenuList(RoleMenu roleMenu)
    {
        return roleMenuMapper.selectRoleMenuList(roleMenu);
    }

    /**
     * 新增角色菜单
     * 
     * @param roleMenu 角色菜单
     * @return 结果
     */
    @Override
    public int insertRoleMenu(RoleMenu roleMenu)
    {
        return roleMenuMapper.insertRoleMenu(roleMenu);
    }

    /**
     * 修改角色菜单
     * 
     * @param roleMenu 角色菜单
     * @return 结果
     */
    @Override
    public int updateRoleMenu(RoleMenu roleMenu)
    {
        return roleMenuMapper.updateRoleMenu(roleMenu);
    }

    /**
     * 删除角色菜单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRoleMenuByIds(String ids)
    {
        return roleMenuMapper.deleteRoleMenuByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除角色菜单信息
     * 
     * @param roleId 角色菜单ID
     * @return 结果
     */
    public int deleteRoleMenuById(Long roleId)
    {
        return roleMenuMapper.deleteRoleMenuById(roleId);
    }

    /**
     * 删除菜单权限
     * @param roleMenu
     * @return
     */
    @Override
    public int deleteByMenuIdAndRoleId(RoleMenuTree roleMenu) {
        return roleMenuMapper.deleteByMenuIdAndRoleId(roleMenu);
    }
}
