package com.iccm.system.service;

import com.alibaba.fastjson.JSONArray;
import com.iccm.system.model.Menu;

/**
 * 菜单Service接口
 * 
 * @author gxj
 * @date 2019-09-07
 */
public interface IMenuService 
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
    public JSONArray selectMenuList(Menu menu);

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
     * 批量删除菜单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMenuByIds(String ids);

    /**
     * 删除菜单信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId);

    /**
     * 下拉列表
     * @return
     */
    public JSONArray queryMenuSelect();
}
