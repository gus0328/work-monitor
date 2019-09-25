package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.annotation.Log;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.enums.BusinessType;
import com.iccm.system.model.MonitorDevice;
import com.iccm.system.model.PostModel;
import com.iccm.system.service.IMonitorDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 监控设备Controller
 *
 * @author gxj
 * @date 2019-09-23
 */
//@PermissionsApi(value = "监控设备管理", authorities = "work:monitorDevice")
@RestController
@RequestMapping("/work/monitorDevice")
public class MonitorDeviceController extends BaseController {
    @Autowired
    private IMonitorDeviceService monitorDeviceService;

    /**
     * 查询监控设备列表
     */
//    @Log(title = "监控设备管理", businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "查询监控设备列表", authorities = "post:work:monitorDevice:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody MonitorDevice monitorDevice) {
        List<MonitorDevice> list = monitorDeviceService.selectMonitorDeviceList(monitorDevice);
        return JsonResult.ok().put("data", list);
    }

    /**
     * 分页查询监控设备列表
     */
//    @Log(title = "监控设备管理", businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "分页查询监控设备列表", authorities = "post:work:monitorDevice:pageList")
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody MonitorDevice monitorDevice) {
        startPage();
        List<MonitorDevice> list = monitorDeviceService.selectMonitorDeviceList(monitorDevice);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    /**
     * 导出监控设备列表
     */
//    @Log(title = "监控设备管理", businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "导出监控设备列表", authorities = "post:work:monitorDevice:export")
    @PostMapping("/export")
    public JsonResult export(@RequestBody MonitorDevice monitorDevice) {
        List<MonitorDevice> list = monitorDeviceService.selectMonitorDeviceList(monitorDevice);
        ExcelUtil<MonitorDevice> util = new ExcelUtil<MonitorDevice>(MonitorDevice.class);
        return util.exportExcel(list, "monitorDevice");
    }

    /**
     * 新增保存监控设备
     */
//    @Log(title = "监控设备管理", businessType = BusinessType.INSERT)
//    @RequiresPermissions(value = "新增保存监控设备", authorities = "post:work:monitorDevice:add")
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody MonitorDevice monitorDevice) {
        monitorDeviceService.insertMonitorDevice(monitorDevice);
        return JsonResult.ok();
    }

    /**
     * 修改保存监控设备
     */
//    @Log(title = "监控设备管理", businessType = BusinessType.UPDATE)
//    @RequiresPermissions(value = "修改保存监控设备", authorities = "post:work:monitorDevice:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody MonitorDevice monitorDevice) {
        monitorDeviceService.updateMonitorDevice(monitorDevice);
        return JsonResult.ok();
    }

    /**
     * 删除监控设备
     */
//    @Log(title = "监控设备管理", businessType = BusinessType.DELETE)
//    @RequiresPermissions(value = "删除监控设备", authorities = "post:work:monitorDevice:remove")
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel) {
        monitorDeviceService.deleteMonitorDeviceById(Long.parseLong(postModel.getId()));
        return JsonResult.ok();
    }


}
