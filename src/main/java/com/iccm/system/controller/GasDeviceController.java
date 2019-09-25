package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.annotation.Log;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.enums.BusinessType;
import com.iccm.system.model.GasDevice;
import com.iccm.system.model.PostModel;
import com.iccm.system.service.IGasDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 气体监测设备Controller
 *
 * @author gxj
 * @date 2019-09-23
 */
//@PermissionsApi(value = "气体监测设备管理", authorities = "work:gasDevice")
@RestController
@RequestMapping("/work/gasDevice")
public class GasDeviceController extends BaseController {
    @Autowired
    private IGasDeviceService gasDeviceService;

    /**
     * 查询气体监测设备列表
     */
//    @Log(title = "气体监测设备管理", businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "查询气体监测设备列表", authorities = "post:work:gasDevice:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody GasDevice gasDevice) {
        List<GasDevice> list = gasDeviceService.selectGasDeviceList(gasDevice);
        return JsonResult.ok().put("data", list);
    }

    /**
     * 分页查询气体监测设备列表
     */
//    @Log(title = "气体监测设备管理", businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "分页查询气体监测设备列表", authorities = "post:work:gasDevice:pageList")
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody GasDevice gasDevice) {
        startPage();
        List<GasDevice> list = gasDeviceService.selectGasDeviceList(gasDevice);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    /**
     * 导出气体监测设备列表
     */
//    @Log(title = "气体监测设备管理", businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "导出气体监测设备列表", authorities = "post:work:gasDevice:export")
    @PostMapping("/export")
    public JsonResult export(@RequestBody GasDevice gasDevice) {
        List<GasDevice> list = gasDeviceService.selectGasDeviceList(gasDevice);
        ExcelUtil<GasDevice> util = new ExcelUtil<GasDevice>(GasDevice.class);
        return util.exportExcel(list, "gasDevice");
    }

    /**
     * 新增保存气体监测设备
     */
//    @Log(title = "气体监测设备管理", businessType = BusinessType.INSERT)
//    @RequiresPermissions(value = "新增保存气体监测设备", authorities = "post:work:gasDevice:add")
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody GasDevice gasDevice) {
        gasDeviceService.insertGasDevice(gasDevice);
        return JsonResult.ok();
    }

    /**
     * 修改保存气体监测设备
     */
//    @Log(title = "气体监测设备管理", businessType = BusinessType.UPDATE)
//    @RequiresPermissions(value = "修改保存气体监测设备", authorities = "post:work:gasDevice:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody GasDevice gasDevice) {
        gasDeviceService.updateGasDevice(gasDevice);
        return JsonResult.ok();
    }

    /**
     * 删除气体监测设备
     */
//    @Log(title = "气体监测设备管理", businessType = BusinessType.DELETE)
//    @RequiresPermissions(value = "删除气体监测设备", authorities = "post:work:gasDevice:remove")
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel) {
        gasDeviceService.deleteGasDeviceById(Long.parseLong(postModel.getId()));
        return JsonResult.ok();
    }


}
