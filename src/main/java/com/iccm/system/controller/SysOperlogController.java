package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.annotation.Log;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.enums.BusinessType;
import com.iccm.common.page.TableDataInfo;
import com.iccm.system.model.SysOperLog;
import com.iccm.system.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录
 * 
 * @author gxj
 */
@PermissionsApi(value = "操作日志",authorities = "monitor:operlog")
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends BaseController
{

    @Autowired
    private ISysOperLogService operLogService;

    @Log(title = "操作日志",businessType = BusinessType.QUERY)
    @RequiresPermissions(value = "查询操作日志",authorities = "post:monitor:operlog:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody SysOperLog operLog)
    {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        TableDataInfo tableDataInfo = getDataTable(list);
        return JsonResult.ok().put("data",tableDataInfo);
    }

//    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
//    @RequiresPermissions("monitor:operlog:export")
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(SysOperLog operLog)
//    {
//        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
//        ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class);
//        return util.exportExcel(list, "操作日志");
//    }
//
//    @RequiresPermissions("monitor:operlog:remove")
//    @PostMapping("/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids)
//    {
//        return toAjax(operLogService.deleteOperLogByIds(ids));
//    }
//
//    @RequiresPermissions("monitor:operlog:detail")
//    @GetMapping("/detail/{operId}")
//    public String detail(@PathVariable("operId") Long operId, ModelMap mmap)
//    {
//        mmap.put("operLog", operLogService.selectOperLogById(operId));
//        return prefix + "/detail";
//    }
//
//    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
//    @RequiresPermissions("monitor:operlog:remove")
//    @PostMapping("/clean")
//    @ResponseBody
//    public AjaxResult clean()
//    {
//        operLogService.cleanOperLog();
//        return success();
//    }
}
