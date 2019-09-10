package com.iccm.system.service;

import com.iccm.system.model.RoleAuthority;
import java.util.List;

/**
 * 角色权限Service接口
 * 
 * @author gxj
 * @date 2019-09-09
 */
public interface IRoleAuthorityService 
{
    /**
     * 查询角色权限
     * 
     * @param roleId 角色权限ID
     * @return 角色权限
     */
    public RoleAuthority selectRoleAuthorityById(Long roleId);

    /**
     * 查询角色权限列表
     * 
     * @param roleAuthority 角色权限
     * @return 角色权限集合
     */
    public List<RoleAuthority> selectRoleAuthorityList(RoleAuthority roleAuthority);

    /**
     * 新增角色权限
     * 
     * @param roleAuthority 角色权限
     * @return 结果
     */
    public int insertRoleAuthority(RoleAuthority roleAuthority);

    /**
     * 修改角色权限
     * 
     * @param roleAuthority 角色权限
     * @return 结果
     */
    public int updateRoleAuthority(RoleAuthority roleAuthority);

    /**
     * 批量删除角色权限
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleAuthorityByIds(String ids);

    /**
     * 删除角色权限信息
     * 
     * @param roleId 角色权限ID
     * @return 结果
     */
    public int deleteRoleAuthorityById(Long roleId);

    /**
     * 删除权限
     * @return
     */
    public int deleteRoleAuthByRoleIdAndAuth(RoleAuthority roleAuthority);
}
