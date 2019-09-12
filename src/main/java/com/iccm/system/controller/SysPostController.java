package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.SysUtils;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.system.model.PostModel;
import com.iccm.system.model.SysPost;
import com.iccm.system.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.util.SystemUtil;

import java.util.List;

/**
 * 岗位信息Controller
 *
 * @author gxj
 * @date 2019-09-11
 */
@PermissionsApi(value = "岗位信息管理", authorities = "system:post")
@RestController
@RequestMapping("/system/post")
public class SysPostController extends BaseController {
    @Autowired
    private ISysPostService sysPostService;

    /**
     * 查询岗位信息列表
     */
    @RequiresPermissions(value = "查询岗位信息列表", authorities = "post:system:post:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody SysPost sysPost) {
        List<SysPost> list = sysPostService.selectSysPostList(sysPost);
        return JsonResult.ok().put("data", list);
    }

    /**
     * 分页查询岗位信息列表
     */
    @RequiresPermissions(value = "分页查询岗位信息列表", authorities = "post:system:post:pageList")
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody SysPost sysPost) {
        startPage();
        List<SysPost> list = sysPostService.selectSysPostList(sysPost);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    /**
     * 导出岗位信息列表
     */
    @RequiresPermissions(value = "导出岗位信息列表", authorities = "post:system:post:export")
    @PostMapping("/export")
    public JsonResult export(@RequestBody SysPost sysPost) {
        List<SysPost> list = sysPostService.selectSysPostList(sysPost);
        ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
        return util.exportExcel(list, "post");
    }

    /**
     * 新增保存岗位信息
     */
    @RequiresPermissions(value = "新增保存岗位信息", authorities = "post:system:post:add")
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody SysPost sysPost) {
        sysPost.setStatus("0");
        sysPost.setCreateBy(SysUtils.getSysUser().getUserName());
        sysPostService.insertSysPost(sysPost);
        return JsonResult.ok();
    }

    /**
     * 修改保存岗位信息
     */
    @RequiresPermissions(value = "修改保存岗位信息", authorities = "post:system:post:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody SysPost sysPost) {
        sysPost.setStatus("0");
        sysPost.setCreateBy(SysUtils.getSysUser().getUserName());
        sysPostService.updateSysPost(sysPost);
        return JsonResult.ok();
    }

    /**
     * 删除岗位信息
     */
    @RequiresPermissions(value = "删除岗位信息", authorities = "post:system:post:remove")
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel) {
        sysPostService.deleteSysPostById(Long.parseLong(postModel.getId()));
        return JsonResult.ok();
    }


}
