package com.iccm.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.iccm.common.Convert;
import com.iccm.common.DateUtils;
import com.iccm.common.utils.JSONUtil;
import com.iccm.system.mapper.MenuMapper;
import com.iccm.system.model.Menu;
import com.iccm.system.model.SelectModel;
import com.iccm.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-07
 */
@Service
public class MenuServiceImpl implements IMenuService
{
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询菜单
     * 
     * @param menuId 菜单ID
     * @return 菜单
     */
    @Override
    public Menu selectMenuById(Long menuId)
    {
        return menuMapper.selectMenuById(menuId);
    }

    /**
     * 查询菜单列表
     * 
     * @param menu 菜单
     * @return 菜单
     */
    @Override
    public JSONArray selectMenuList(Menu menu)
    {
        List list = menuMapper.selectMenuList(menu);
        JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss"));
        return JSONUtil.listToTree(jsonArray,"menuId","parentId","children");
    }

    /**
     * 新增菜单
     * 
     * @param menu 菜单
     * @return 结果
     */
    @Override
    public int insertMenu(Menu menu)
    {
        menu.setCreateTime(DateUtils.getNowDate());
        return menuMapper.insertMenu(menu);
    }

    /**
     * 修改菜单
     * 
     * @param menu 菜单
     * @return 结果
     */
    @Override
    public int updateMenu(Menu menu)
    {
        menu.setUpdateTime(DateUtils.getNowDate());
        return menuMapper.updateMenu(menu);
    }

    /**
     * 删除菜单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMenuByIds(String ids)
    {
        return menuMapper.deleteMenuByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除菜单信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId)
    {
        return menuMapper.deleteMenuById(menuId);
    }

    /**
     * 查询菜单下拉
     * @return
     */
    @Override
    public JSONArray queryMenuSelect() {
        List<SelectModel> list = menuMapper.queryMenuSelect();
        JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONString(list));
        return JSONUtil.listToTree(jsonArray,"value","depart","children");
    }
}
