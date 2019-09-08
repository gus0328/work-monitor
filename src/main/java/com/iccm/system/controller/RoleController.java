package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.system.model.PostModel;
import com.iccm.system.model.Role;
import com.iccm.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限Controller
 *
 * @author gxj
 * @date 2019-09-08
 */
@PermissionsApi(value = "权限管理",authorities = "system:role")
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {
    @Autowired
    private IRoleService roleService;

    /**
     * 查询权限列表
     */
    @RequiresPermissions(value = "查询权限列表",authorities = "post:system:role:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody Role role) {
        List<Role> list = roleService.selectRoleList(role);
        return JsonResult.ok().put("data",list);
    }

    /**
     * 分页查询权限列表
     */
    @RequiresPermissions(value = "分页查询权限列表",authorities = "post:system:role:pageList")
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody Role role) {
        startPage();
        List<Role> list = roleService.selectRoleList(role);
        return JsonResult.ok().put("data",getDataTable(list));
    }

    /**
     * 导出权限列表
     */
    @RequiresPermissions(value = "导出权限列表",authorities = "post:system:role:export")
    @PostMapping("/export")
    public JsonResult export(@RequestBody Role role) {
        List<Role> list = roleService.selectRoleList(role);
        ExcelUtil<Role> util = new ExcelUtil<Role>(Role. class);
        return util.exportExcel(list, "role");
    }

    /**
     * 新增保存权限
     */
    @RequiresPermissions(value = "新增保存权限",authorities = "post:system:role:add")
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody Role role) {
            roleService.insertRole(role);
        return JsonResult.ok();
    }

    /**
     * 修改保存权限
     */
    @RequiresPermissions(value = "修改保存权限",authorities = "post:system:role:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody Role role) {
            roleService.updateRole(role);
        return JsonResult.ok();
    }

        /**
         * 删除权限
         */
        @RequiresPermissions(value = "删除权限",authorities = "post:system:role:remove")
        @PostMapping("/remove")
        public JsonResult remove(@RequestBody PostModel postModel) {
                roleService.deleteRoleById(Long.parseLong(postModel.getId()));
            return JsonResult.ok();
        }


}
