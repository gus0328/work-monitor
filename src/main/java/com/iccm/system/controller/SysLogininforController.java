package com.iccm.system.controller;


import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.annotation.Log;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.enums.BusinessType;
import com.iccm.common.page.TableDataInfo;
import com.iccm.system.model.SysLogininfor;
import com.iccm.system.service.ISysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统访问记录
 *
 * @author gxj
 */
@PermissionsApi(value = "登录日志",authorities = "monitor:logininfor")
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController {

    @Autowired
    private ISysLogininforService logininforService;

    @Log(title = "登录日志",businessType = BusinessType.QUERY)
    @RequiresPermissions(value = "查询登录日志",authorities = "post:monitor:logininfor:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody SysLogininfor logininfor) {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        TableDataInfo tableDataInfo = getDataTable(list);
        return JsonResult.ok().put("data",tableDataInfo);
    }

//    @Log(title = "登陆日志", businessType = BusinessType.EXPORT)
//    @RequiresPermissions("monitor:logininfor:export")
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(SysLogininfor logininfor)
//    {
//        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
//        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
//        return util.exportExcel(list, "登陆日志");
//    }
//
//    @RequiresPermissions("monitor:logininfor:remove")
//    @Log(title = "登陆日志", businessType = BusinessType.DELETE)
//    @PostMapping("/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids)
//    {
//        return toAjax(logininforService.deleteLogininforByIds(ids));
//    }
//
//    @RequiresPermissions("monitor:logininfor:remove")
//    @Log(title = "登陆日志", businessType = BusinessType.CLEAN)
//    @PostMapping("/clean")
//    @ResponseBody
//    public AjaxResult clean()
//    {
//        logininforService.cleanLogininfor();
//        return success();
//    }
}
