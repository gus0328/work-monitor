package com.iccm.system.mapper;

import com.iccm.system.model.*;

import java.util.List;

/**
 * 权限Mapper接口
 * 
 * @author gxj
 * @date 2019-09-08
 */
public interface RoleMapper 
{
    /**
     * 查询权限
     * 
     * @param roleId 权限ID
     * @return 权限
     */
    public Role selectRoleById(Long roleId);

    /**
     * 查询权限列表
     * 
     * @param role 权限
     * @return 权限集合
     */
    public List<Role> selectRoleList(Role role);

    /**
     * 新增权限
     * 
     * @param role 权限
     * @return 结果
     */
    public int insertRole(Role role);

    /**
     * 修改权限
     * 
     * @param role 权限
     * @return 结果
     */
    public int updateRole(Role role);

    /**
     * 删除权限
     * 
     * @param roleId 权限ID
     * @return 结果
     */
    public int deleteRoleById(Long roleId);

    /**
     * 批量删除权限
     * 
     * @param roleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleByIds(String[] roleIds);

    /**
     * 查询角色下拉
     * @return
     */
    public List<FormSelect> roleSelect();

    /**
     * 根据id查询角色菜单
     * @param roleId
     * @return
     */
    public List<RoleMenuTree> queryMenuSelectById(Long roleId);

    /**
     * 根据id查询角色权限
     * @param roleId
     * @return
     */
    public List<RoleAuthorityTree> queryAuthoritiesById(Long roleId);

    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    public List<Role> selectRolesByUserId(Long userId);
}
