package com.iccm.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.SysUtils;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.system.model.*;
import com.iccm.system.service.IRoleAuthorityService;
import com.iccm.system.service.IRoleMenuService;
import com.iccm.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色Controller
 *
 * @author gxj
 * @date 2019-09-08
 */
@PermissionsApi(value = "角色管理" , authorities = "system:role")
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleMenuService roleMenuService;

    @Autowired
    private IRoleAuthorityService roleAuthorityService;

    /**
     * 查询角色列表
     */
    @RequiresPermissions(value = "查询角色列表" , authorities = "post:system:role:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody Role role) {
        List<Role> list = roleService.selectRoleList(role);
        return JsonResult.ok().put("data" , list);
    }

    /**
     * 分页查询角色列表
     */
    @RequiresPermissions(value = "分页查询角色列表" , authorities = "post:system:role:pageList")
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody Role role) {
        startPage();
        List<Role> list = roleService.selectRoleList(role);
        return JsonResult.ok().put("data" , getDataTable(list));
    }

    /**
     * 导出角色列表
     */
    @RequiresPermissions(value = "导出角色列表" , authorities = "post:system:role:export")
    @PostMapping("/export")
    public JsonResult export(@RequestBody Role role) {
        List<Role> list = roleService.selectRoleList(role);
        ExcelUtil<Role> util = new ExcelUtil<Role>(Role.class);
        return util.exportExcel(list, "role");
    }

    /**
     * 新增保存角色
     */
    @RequiresPermissions(value = "新增保存角色" , authorities = "post:system:role:add")
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody Role role) {
        role.setCreateBy(SysUtils.getSysUser().getUserName());
        roleService.insertRole(role);
        return JsonResult.ok();
    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions(value = "修改保存角色" , authorities = "post:system:role:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody Role role) {
        role.setUpdateBy(SysUtils.getSysUser().getUserName());
        roleService.updateRole(role);
        return JsonResult.ok();
    }

    /**
     * 删除角色
     */
    @RequiresPermissions(value = "删除角色" , authorities = "post:system:role:remove")
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel) {
        roleService.deleteRoleById(Long.parseLong(postModel.getId()));
        return JsonResult.ok();
    }

    /**
     * 查询角色下拉
     */
    @RequiresPermissions(value = "查询角色下拉" , authorities = "get:system:role:roleSelect")
    @GetMapping("/roleSelect")
    public JsonResult roleSelect(){
        List list = roleService.roleSelect();
        return JsonResult.ok().put("data",list);
    }

    /**
     * 根据roleId查询菜单
     */
    @RequiresPermissions(value = "根据roleId查询菜单" , authorities = "post:system:role:menus")
    @PostMapping("/menus")
    public JsonResult queryMenusByRoleId(@RequestBody PostModel postModel){
        JSONArray jsonArray = roleService.queryMenusByRoleId(Long.parseLong(postModel.getId()));
        return JsonResult.ok().put("data",jsonArray);
    }

    /**
     * 根据roleId查询权限
     */
    @RequiresPermissions(value = "根据roleId查询权限" , authorities = "post:system:role:authorities")
    @PostMapping("/authorities")
    public JsonResult queryAuthoritiesByRoleId(@RequestBody PostModel postModel){
        JSONArray jsonArray = roleService.queryAuthoritiesByRoleId(Long.parseLong(postModel.getId()));
        return JsonResult.ok().put("data",jsonArray);
    }

    /**
     * 菜单权限更改
     * @param roleMenuTree
     * @return
     */
    @RequiresPermissions(value = "菜单权限更改" , authorities = "post:system:role:menu:change")
    @PostMapping("/menu/change")
    public JsonResult menuChange(@RequestBody RoleMenuTree roleMenuTree){
        if(roleMenuTree.isFlag()){
            roleMenuService.insertRoleMenu(new RoleMenu(roleMenuTree.getRoleId(),roleMenuTree.getMenuId()));
        }else{
            roleMenuService.deleteByMenuIdAndRoleId(roleMenuTree);
        }
        return JsonResult.ok();
    }

    /**
     * 接口权限更改
     * @param roleAuthorityTree
     * @return
     */
    @RequiresPermissions(value = "接口权限更改" , authorities = "post:system:role:authority:change")
    @PostMapping("/authority/change")
    public JsonResult authChange(@RequestBody RoleAuthorityTree roleAuthorityTree){
        if(roleAuthorityTree.isFlag()){
            roleAuthorityService.insertRoleAuthority(new RoleAuthority(roleAuthorityTree.getRoleId(),roleAuthorityTree.getAuthority()));
        }else{
            roleAuthorityService.deleteRoleAuthByRoleIdAndAuth(new RoleAuthority(roleAuthorityTree.getRoleId(),roleAuthorityTree.getAuthority()));
        }
        return JsonResult.ok();
    }

}
