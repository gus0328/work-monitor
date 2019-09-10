package com.iccm.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.RoleAuthorityMapper;
import com.iccm.system.model.RoleAuthority;
import com.iccm.system.service.IRoleAuthorityService;
import com.iccm.common.Convert;

/**
 * 角色权限Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-09
 */
@Service
public class RoleAuthorityServiceImpl implements IRoleAuthorityService 
{
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    /**
     * 查询角色权限
     * 
     * @param roleId 角色权限ID
     * @return 角色权限
     */
    @Override
    public RoleAuthority selectRoleAuthorityById(Long roleId)
    {
        return roleAuthorityMapper.selectRoleAuthorityById(roleId);
    }

    /**
     * 查询角色权限列表
     * 
     * @param roleAuthority 角色权限
     * @return 角色权限
     */
    @Override
    public List<RoleAuthority> selectRoleAuthorityList(RoleAuthority roleAuthority)
    {
        return roleAuthorityMapper.selectRoleAuthorityList(roleAuthority);
    }

    /**
     * 新增角色权限
     * 
     * @param roleAuthority 角色权限
     * @return 结果
     */
    @Override
    public int insertRoleAuthority(RoleAuthority roleAuthority)
    {
        return roleAuthorityMapper.insertRoleAuthority(roleAuthority);
    }

    /**
     * 修改角色权限
     * 
     * @param roleAuthority 角色权限
     * @return 结果
     */
    @Override
    public int updateRoleAuthority(RoleAuthority roleAuthority)
    {
        return roleAuthorityMapper.updateRoleAuthority(roleAuthority);
    }

    /**
     * 删除角色权限对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRoleAuthorityByIds(String ids)
    {
        return roleAuthorityMapper.deleteRoleAuthorityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除角色权限信息
     * 
     * @param roleId 角色权限ID
     * @return 结果
     */
    public int deleteRoleAuthorityById(Long roleId)
    {
        return roleAuthorityMapper.deleteRoleAuthorityById(roleId);
    }

    /**
     * 删除权限
     * @return
     */
    @Override
    public int deleteRoleAuthByRoleIdAndAuth(RoleAuthority roleAuthority) {
        return roleAuthorityMapper.deleteRoleAuthByRoleIdAndAuth(roleAuthority);
    }
}
