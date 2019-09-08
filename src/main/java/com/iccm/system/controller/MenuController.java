package com.iccm.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.iccm.common.BaseController;
import com.iccm.common.JsonResult;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.system.model.Menu;
import com.iccm.system.model.PostModel;
import com.iccm.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单Controller
 * 
 * @author gxj
 * @date 2019-09-07
 */
@PermissionsApi(value = "菜单管理",authorities = "system:menu")
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController
{

    @Autowired
    private IMenuService menuService;

    /**
     * 查询菜单列表
     */
    @RequiresPermissions(value = "查询菜单列表",authorities = "post:system:menu:list")
    @PostMapping("/list")
    public JSONArray list(@RequestBody Menu menu)
    {
        return menuService.selectMenuList(menu);
    }

//    /**
//     * 导出菜单列表
//     */
//    @PostMapping("/export")
//    @ResponseBody
//    public JsonResult export(@RequestBody Menu menu)
//    {
//        List<Menu> list = menuService.selectMenuList(menu);
//        ExcelUtil<Menu> util = new ExcelUtil<Menu>(Menu.class);
//        return util.exportExcel(list, "menu");
//    }

    /**
     * 新增保存菜单
     */
    @RequiresPermissions(value = "新增保存菜单",authorities = "post:system:menu:add")
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody Menu menu)
    {
        menuService.insertMenu(menu);
        return JsonResult.ok();
    }

    /**
     * 修改保存菜单
     */
    @RequiresPermissions(value = "修改保存菜单",authorities = "post:system:menu:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody Menu menu)
    {
        menuService.updateMenu(menu);
        return JsonResult.ok();
    }

    /**
     * 删除菜单
     */
    @RequiresPermissions(value = "删除菜单",authorities = "post:system:menu:remove")
    @PostMapping( "/remove")
    public JsonResult remove(@RequestBody PostModel postModel)
    {
        menuService.deleteMenuById(Long.parseLong(postModel.getId()));
        return JsonResult.ok();
    }

    @RequiresPermissions(value = "获取菜单树",authorities = "get:system:menu:treeSelect")
    @GetMapping("/treeSelect")
    public JSONArray treeSelect(){
        return menuService.queryMenuSelect();
    }
}
