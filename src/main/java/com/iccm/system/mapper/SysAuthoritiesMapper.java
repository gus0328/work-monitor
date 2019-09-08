package com.iccm.system.mapper;

import com.iccm.system.model.SysAuthorities;
import java.util.List;

/**
 * 权限标识Mapper接口
 * 
 * @author gxj
 * @date 2019-09-08
 */
public interface SysAuthoritiesMapper 
{
    /**
     * 查询权限标识
     * 
     * @param authority 权限标识ID
     * @return 权限标识
     */
    public SysAuthorities selectSysAuthoritiesById(String authority);

    /**
     * 查询权限标识列表
     * 
     * @param sysAuthorities 权限标识
     * @return 权限标识集合
     */
    public List<SysAuthorities> selectSysAuthoritiesList(SysAuthorities sysAuthorities);

    /**
     * 新增权限标识
     * 
     * @param sysAuthorities 权限标识
     * @return 结果
     */
    public int insertSysAuthorities(SysAuthorities sysAuthorities);

    /**
     * 修改权限标识
     * 
     * @param sysAuthorities 权限标识
     * @return 结果
     */
    public int updateSysAuthorities(SysAuthorities sysAuthorities);

    /**
     * 删除权限标识
     * 
     * @param authority 权限标识ID
     * @return 结果
     */
    public int deleteSysAuthoritiesById(String authority);

    /**
     * 批量删除权限标识
     * 
     * @param authoritys 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysAuthoritiesByIds(String[] authoritys);

    /**
     * 删除全部权限标识
     * @return
     */
    public int deleteAll();
}
