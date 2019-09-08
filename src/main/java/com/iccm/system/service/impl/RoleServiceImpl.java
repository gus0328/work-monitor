package com.iccm.system.service.impl;

import java.util.List;
import com.iccm.common.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.RoleMapper;
import com.iccm.system.model.Role;
import com.iccm.system.service.IRoleService;
import com.iccm.common.Convert;

/**
 * 权限Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-08
 */
@Service
public class RoleServiceImpl implements IRoleService 
{
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询权限
     * 
     * @param roleId 权限ID
     * @return 权限
     */
    @Override
    public Role selectRoleById(Long roleId)
    {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 查询权限列表
     * 
     * @param role 权限
     * @return 权限
     */
    @Override
    public List<Role> selectRoleList(Role role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 新增权限
     * 
     * @param role 权限
     * @return 结果
     */
    @Override
    public int insertRole(Role role)
    {
        role.setCreateTime(DateUtils.getNowDate());
        return roleMapper.insertRole(role);
    }

    /**
     * 修改权限
     * 
     * @param role 权限
     * @return 结果
     */
    @Override
    public int updateRole(Role role)
    {
        role.setUpdateTime(DateUtils.getNowDate());
        return roleMapper.updateRole(role);
    }

    /**
     * 删除权限对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRoleByIds(String ids)
    {
        return roleMapper.deleteRoleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除权限信息
     * 
     * @param roleId 权限ID
     * @return 结果
     */
    public int deleteRoleById(Long roleId)
    {
        return roleMapper.deleteRoleById(roleId);
    }
}
