package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.annotation.Log;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.enums.BusinessType;
import com.iccm.system.model.PostModel;
import com.iccm.system.model.WearDevice;
import com.iccm.system.service.IWearDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 穿戴设备Controller
 *
 * @author gxj
 * @date 2019-09-23
 */
//@PermissionsApi(value = "穿戴设备管理",authorities = "work:wearDevice")
@RestController
@RequestMapping("/work/wearDevice")
public class WearDeviceController extends BaseController {
    @Autowired
    private IWearDeviceService wearDeviceService;

    /**
     * 查询穿戴设备列表
     */
//    @Log(title = "穿戴设备管理",businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "查询穿戴设备列表",authorities = "post:work:wearDevice:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody WearDevice wearDevice) {
        List<WearDevice> list = wearDeviceService.selectWearDeviceList(wearDevice);
        return JsonResult.ok().put("data",list);
    }

    /**
     * 分页查询穿戴设备列表
     */
//    @Log(title = "穿戴设备管理",businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "分页查询穿戴设备列表",authorities = "post:work:wearDevice:pageList")
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody WearDevice wearDevice) {
        startPage();
        List<WearDevice> list = wearDeviceService.selectWearDeviceList(wearDevice);
        return JsonResult.ok().put("data",getDataTable(list));
    }

    /**
     * 导出穿戴设备列表
     */
//    @Log(title = "穿戴设备管理",businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "导出穿戴设备列表",authorities = "post:work:wearDevice:export")
    @PostMapping("/export")
    public JsonResult export(@RequestBody WearDevice wearDevice) {
        List<WearDevice> list = wearDeviceService.selectWearDeviceList(wearDevice);
        ExcelUtil<WearDevice> util = new ExcelUtil<WearDevice>(WearDevice. class);
        return util.exportExcel(list, "wearDevice");
    }

    /**
     * 新增保存穿戴设备
     */
//    @Log(title = "穿戴设备管理",businessType = BusinessType.INSERT)
//    @RequiresPermissions(value = "新增保存穿戴设备",authorities = "post:work:wearDevice:add")
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody WearDevice wearDevice) {
            wearDeviceService.insertWearDevice(wearDevice);
        return JsonResult.ok();
    }

    /**
     * 修改保存穿戴设备
     */
//    @Log(title = "穿戴设备管理",businessType = BusinessType.UPDATE)
//    @RequiresPermissions(value = "修改保存穿戴设备",authorities = "post:work:wearDevice:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody WearDevice wearDevice) {
            wearDeviceService.updateWearDevice(wearDevice);
        return JsonResult.ok();
    }

        /**
         * 删除穿戴设备
         */
//        @Log(title = "穿戴设备管理",businessType = BusinessType.DELETE)
//        @RequiresPermissions(value = "删除穿戴设备",authorities = "post:work:wearDevice:remove")
        @PostMapping("/remove")
        public JsonResult remove(@RequestBody PostModel postModel) {
                wearDeviceService.deleteWearDeviceById(Long.parseLong(postModel.getId()));
            return JsonResult.ok();
        }


}
