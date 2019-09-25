package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.annotation.Log;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.enums.BusinessType;
import com.iccm.system.model.*;
import com.iccm.system.service.ISiteWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 现场作业Controller
 *
 * @author gxj
 * @date 2019-09-23
 */
//@PermissionsApi(value = "现场作业管理", authorities = "work:siteWork")
@RestController
@RequestMapping("/work/siteWork")
public class SiteWorkController extends BaseController {
    @Autowired
    private ISiteWorkService siteWorkService;


    /**
     * 查询现场作业列表
     */
//    @Log(title = "现场作业管理", businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "查询现场作业列表", authorities = "post:work:siteWork:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody SiteWork siteWork) {
        List<SiteWork> list = siteWorkService.selectSiteWorkList(siteWork);
        return JsonResult.ok().put("data", list);
    }

//    public JsonResult getWorkDetails(@RequestBody PostModel postModel){
//
//    }

    /**
     * 分页查询现场作业列表
     */
//    @Log(title = "现场作业管理", businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "分页查询现场作业列表", authorities = "post:work:siteWork:pageList")
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody SiteWork siteWork) {
        startPage();
        List<SiteWork> list = siteWorkService.selectSiteWorkList(siteWork);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    /**
     * 导出现场作业列表
     */
//    @Log(title = "现场作业管理", businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "导出现场作业列表", authorities = "post:work:siteWork:export")
    @PostMapping("/export")
    public JsonResult export(@RequestBody SiteWork siteWork) {
        List<SiteWork> list = siteWorkService.selectSiteWorkList(siteWork);
        ExcelUtil<SiteWork> util = new ExcelUtil<SiteWork>(SiteWork.class);
        return util.exportExcel(list, "siteWork");
    }

    /**
     * 新增保存现场作业
     */
//    @Log(title = "现场作业管理", businessType = BusinessType.INSERT)
//    @RequiresPermissions(value = "新增保存现场作业", authorities = "post:work:siteWork:add")
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody SiteWorkVo siteWorkVo) {
        siteWorkService.insertSiteWork(siteWorkVo);
        return JsonResult.ok();
    }

    /**
     * 修改保存现场作业
     */
//    @Log(title = "现场作业管理", businessType = BusinessType.UPDATE)
//    @RequiresPermissions(value = "修改保存现场作业", authorities = "post:work:siteWork:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody SiteWorkVo siteWorkVo) {
        siteWorkService.updateSiteWork(siteWorkVo);
        return JsonResult.ok();
    }

    /**
     * 删除现场作业
     */
//    @Log(title = "现场作业管理", businessType = BusinessType.DELETE)
//    @RequiresPermissions(value = "删除现场作业", authorities = "post:work:siteWork:remove")
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel) {
        return siteWorkService.deleteSiteWorkById(postModel.getId());
    }

    /**
     * 查询作业详情
     * @param postModel
     * @return
     */
    @PostMapping("/getWorkDetails")
    public JsonResult getWorkDetails(@RequestBody PostModel postModel){
        return JsonResult.ok().put("details",siteWorkService.getWorkDetailsByWorkId(postModel.getId()));
    }

    /**
     * 穿戴设备下拉
     * @return
     */
    @PostMapping("/wearSelect")
    public JsonResult getWearDeviceSelectList(@RequestBody WearDevice wearDevice) {
        return JsonResult.ok().put("data",siteWorkService.getWearDeviceSelectList(wearDevice));
    }

    /**
     * 气体监测设备下拉
     * @return
     */
    @PostMapping("/gasSelect")
    public JsonResult getGasDeviceSelectList(@RequestBody GasDevice gasDevice) {
        return JsonResult.ok().put("data",siteWorkService.getGasDeviceSelectList(gasDevice));
    }

    /**
     * 监控设备下拉
     * @return
     */
    @PostMapping("/monitorSelect")
    public JsonResult getMonitorDeviceSelectList(@RequestBody MonitorDevice monitorDevice) {
        return JsonResult.ok().put("data",siteWorkService.getMonitorDeviceSelectList(monitorDevice));
    }
}
