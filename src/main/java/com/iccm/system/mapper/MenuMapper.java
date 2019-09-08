package com.iccm.system.mapper;


import com.iccm.system.model.Menu;
import com.iccm.system.model.SelectModel;

import java.util.List;

/**
 * 菜单Mapper接口
 * 
 * @author gxj
 * @date 2019-09-07
 */
public interface MenuMapper 
{
    /**
     * 查询菜单
     * 
     * @param menuId 菜单ID
     * @return 菜单
     */
    public Menu selectMenuById(Long menuId);

    /**
     * 查询菜单列表
     * 
     * @param menu 菜单
     * @return 菜单集合
     */
    public List<Menu> selectMenuList(Menu menu);

    /**
     * 新增菜单
     * 
     * @param menu 菜单
     * @return 结果
     */
    public int insertMenu(Menu menu);

    /**
     * 修改菜单
     * 
     * @param menu 菜单
     * @return 结果
     */
    public int updateMenu(Menu menu);

    /**
     * 删除菜单
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId);

    /**
     * 批量删除菜单
     * 
     * @param menuIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMenuByIds(String[] menuIds);

    /**
     * 查询部门下拉
     * @return
     */
    public List<SelectModel> queryMenuSelect();
}
