package com.iccm.system.controller;


import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.enums.BusinessType;
import com.iccm.common.page.TableDataInfo;
import com.iccm.system.model.SysLogininfor;
import com.iccm.system.service.ISysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统访问记录
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController
{
    private String prefix = "monitor/logininfor";

    @Autowired
    private ISysLogininforService logininforService;

    @RequiresPermissions("monitor:logininfor:view")
    @GetMapping()
    public String logininfor()
    {
        return prefix + "/logininfor";
    }

    @RequiresPermissions("monitor:logininfor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysLogininfor logininfor)
    {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
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
