package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.SysUtils;
import com.iccm.system.mapper.MobileDeviceMapper;
import com.iccm.system.mapper.SysUserMapper;
import com.iccm.system.model.MobileDevice;
import com.iccm.system.model.PostModel;
import com.iccm.system.model.SysUser;
import com.iccm.system.service.IMobileDeviceService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 移动设备管理Controller
 *
 * @author gxj
 * @date 2019-10-14
 */
@RestController
@RequestMapping("/system/device")
public class MobileDeviceController extends BaseController {
    @Autowired
    private IMobileDeviceService mobileDeviceService;

    @Autowired
    private MobileDeviceMapper mobileDeviceMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询移动设备管理列表
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody MobileDevice mobileDevice) {
        List<MobileDevice> list = mobileDeviceService.selectMobileDeviceList(mobileDevice);
        return JsonResult.ok().put("data", list);
    }

    /**
     * 分页查询移动设备管理列表
     */
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody MobileDevice mobileDevice) {
        startPage();
        List<MobileDevice> list = mobileDeviceService.selectMobileDeviceList(mobileDevice);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    /**
     * 导出移动设备管理列表
     */
    @PostMapping("/export")
    public JsonResult export(@RequestBody MobileDevice mobileDevice) {
        List<MobileDevice> list = mobileDeviceService.selectMobileDeviceList(mobileDevice);
        ExcelUtil<MobileDevice> util = new ExcelUtil<MobileDevice>(MobileDevice.class);
        return util.exportExcel(list, "device");
    }

    /**
     * 新增保存移动设备管理
     */
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody MobileDevice mobileDevice) {
        List<Long> ids = mobileDeviceMapper.selectIdByDeviceId(mobileDevice.getDeviceId());
        SysUser sysUser = SysUtils.getSysUser();
        if(CollectionUtils.isNotEmpty(ids)){
            mobileDevice.setId(Long.parseLong(ids.get(0)+""));
            mobileDeviceService.updateMobileDevice(mobileDevice);
        }else{
            mobileDevice.setUserId(sysUser.getUserId());
            mobileDevice.setLoginName(sysUser.getLoginName());
            mobileDevice.setUserName(sysUser.getUserName());
            mobileDevice.setCreateTime(new Date());
            mobileDeviceService.insertMobileDevice(mobileDevice);
        }
        SysUser updateUser = new SysUser();
        updateUser.setUserId(sysUser.getUserId());
        updateUser.setDeviceId(mobileDevice.getDeviceId());
        sysUserMapper.updateUser(updateUser);
        return JsonResult.ok();
    }

    /**
     * 修改保存移动设备管理
     */
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody MobileDevice mobileDevice) {
        mobileDeviceService.updateMobileDevice(mobileDevice);
        return JsonResult.ok();
    }

    /**
     * 删除移动设备管理
     */
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel) {
        mobileDeviceService.deleteMobileDeviceById(Long.parseLong(postModel.getId()));
        return JsonResult.ok();
    }


}
