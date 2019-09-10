package com.iccm.system.service;

import com.alibaba.fastjson.JSONArray;
import com.iccm.system.model.FormSelect;
import com.iccm.system.model.Role;
import com.iccm.system.model.SelectModel;

import java.util.List;

/**
 * 权限Service接口
 * 
 * @author gxj
 * @date 2019-09-08
 */
public interface IRoleService 
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
     * 批量删除权限
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleByIds(String ids);

    /**
     * 删除权限信息
     * 
     * @param roleId 权限ID
     * @return 结果
     */
    public int deleteRoleById(Long roleId);

    /**
     * 角色下拉
     * @return
     */
    public List<FormSelect> roleSelect();

    /**
     * 根据roleId查询菜单
     * @param roleId
     * @return
     */
    public JSONArray queryMenusByRoleId(Long roleId);

    /**
     * 根据roleId查询权限
     * @param roleId
     * @return
     */
    public JSONArray queryAuthoritiesByRoleId(Long roleId);
}
