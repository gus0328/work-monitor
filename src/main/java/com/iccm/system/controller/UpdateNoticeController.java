package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.SysUtils;
import com.iccm.system.mapper.UpdateNoticeMapper;
import com.iccm.system.model.PostModel;
import com.iccm.system.model.UpdateNotice;
import com.iccm.system.service.IUpdateNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * app更新Controller
 *
 * @author gxj
 * @date 2019-10-10
 */
@RestController
@RequestMapping("/system/notice")
public class UpdateNoticeController extends BaseController {
    @Autowired
    private IUpdateNoticeService updateNoticeService;

    @Autowired
    private UpdateNoticeMapper updateNoticeMapper;

    /**
     * 查询app更新列表
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody UpdateNotice updateNotice) {
        List<UpdateNotice> list = updateNoticeService.selectUpdateNoticeList(updateNotice);
        return JsonResult.ok().put("data", list);
    }

    /**
     * 分页查询app更新列表
     */
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody UpdateNotice updateNotice) {
        startPage();
        List<UpdateNotice> list = updateNoticeService.selectUpdateNoticeList(updateNotice);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    /**
     * 导出app更新列表
     */
    @PostMapping("/export")
    public JsonResult export(@RequestBody UpdateNotice updateNotice) {
        List<UpdateNotice> list = updateNoticeService.selectUpdateNoticeList(updateNotice);
        ExcelUtil<UpdateNotice> util = new ExcelUtil<UpdateNotice>(UpdateNotice.class);
        return util.exportExcel(list, "notice");
    }

    /**
     * 新增保存app更新
     */
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody UpdateNotice updateNotice) {
        updateNotice.setCreateBy(SysUtils.getSysUser().getUserName());
        updateNoticeService.insertUpdateNotice(updateNotice);
        return JsonResult.ok();
    }

    /**
     * 修改保存app更新
     */
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody UpdateNotice updateNotice) {
        updateNotice.setUpdateBy(SysUtils.getSysUser().getUserName());
        updateNoticeService.updateUpdateNotice(updateNotice);
        return JsonResult.ok();
    }

    /**
     * 删除app更新
     */
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel) {
        updateNoticeService.deleteUpdateNoticeById(Long.parseLong(postModel.getId()));
        return JsonResult.ok();
    }

    /**
     * 根据版本号查询
     * @param versionCode
     * @return
     */
    @GetMapping("/selectByVersionCode")
    public JsonResult selectByVersionCode(String versionCode){
        return JsonResult.ok().put("data",updateNoticeMapper.selectByVersionCode(versionCode));
    }
}
