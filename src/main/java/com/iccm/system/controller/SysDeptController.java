package com.iccm.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.iccm.common.BaseController;
import com.iccm.common.JsonResult;
import com.iccm.common.UserConstants;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.system.model.SysDept;
import com.iccm.system.model.Ztree;
import com.iccm.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门信息
 * 
 * @author gxj
 */
@PermissionsApi(value = "部门管理",authorities = "system:dept")
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController
{

    @Autowired
    private ISysDeptService deptService;

    /**
     * 查询部门列表
     * @param dept
     * @return
     */
    @RequiresPermissions(value = "查询部门列表",authorities = "post:system:dept:list")
    @PostMapping("/list")
    public JSONArray list(@RequestBody SysDept dept)
    {
        return  deptService.selectDeptList(dept);
    }

    /**
     * 新增保存部门
     */
    @RequiresPermissions(value = "新增保存部门",authorities = "post:system:dept:add")
    @PostMapping("/add")
    public JsonResult addSave(@Validated @RequestBody SysDept dept)
    {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return JsonResult.error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
//        dept.setCreateBy(ShiroUtils.getLoginName());
        deptService.insertDept(dept);
        return JsonResult.ok();
    }

    /**
     * 修改保存部门
     */
    @RequiresPermissions(value = "修改保存部门",authorities = "post:system:dept:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@Validated @RequestBody SysDept dept)
    {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return JsonResult.error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        else if (dept.getParentId().equals(dept.getDeptId()))
        {
            return JsonResult.error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
//        dept.setUpdateBy(ShiroUtils.getLoginName());
        deptService.updateDept(dept);
        return JsonResult.ok();
    }

    /**
     * 删除
     */
    @RequiresPermissions(value = "删除部门",authorities = "post:system:dept:remove")
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody SysDept sysDept)
    {
        if (deptService.selectDeptCount(sysDept.getDeptId()) > 0)
        {
            return JsonResult.error("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(sysDept.getDeptId()))
        {
            return JsonResult.error("部门存在用户,不允许删除");
        }
        deptService.deleteDeptById(sysDept.getDeptId());
        return JsonResult.ok();
    }

    /**
     * 校验部门名称
     */
    @RequiresPermissions(value = "校验部门名称",authorities = "post:system:dept:checkDeptNameUnique")
    @PostMapping("/checkDeptNameUnique")
    public String checkDeptNameUnique(@RequestBody SysDept dept)
    {
        return deptService.checkDeptNameUnique(dept);
    }

    /**
     * 加载部门列表树
     */
    @RequiresPermissions(value = "加载部门列表树",authorities = "get:system:dept:treeData")
    @GetMapping("/treeData")
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = deptService.selectDeptTree(new SysDept());
        return ztrees;
    }

    /**
     * 查询部门下拉
     * @return
     */
    @RequiresPermissions(value = "查询部门下拉",authorities = "get:system:dept:treeSelect")
    @GetMapping("/treeSelect")
    public JSONArray treeSelect(){
        return deptService.queryDeptSelect();
    }

}
