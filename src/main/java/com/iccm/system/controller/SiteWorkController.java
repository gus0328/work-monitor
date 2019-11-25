package com.iccm.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.iccm.common.*;
import com.iccm.common.annotation.Log;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.config.ApplicationListener;
import com.iccm.common.enums.BusinessType;
import com.iccm.common.utils.DateUtil;
import com.iccm.system.mapper.SiteWorkMapper;
import com.iccm.system.model.*;
import com.iccm.system.opcServer.OpcTask;
import com.iccm.system.service.ISiteWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @Autowired
    private SiteWorkMapper siteWorkMapper;

    @Autowired
    private OpcTask opcTask;


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

    /**
     * 查询个人负责现场列表
     * @param siteWork
     * @return
     */
    @PostMapping("/ownlist")
    public JsonResult ownList(@RequestBody SiteWork siteWork) {
        SysUser sysUser = SysUtils.getSysUser();
        siteWork.setLeadUserId(sysUser.getUserId());
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
     * 分页查询现场作业列表
     */
//    @Log(title = "现场作业管理", businessType = BusinessType.QUERY)
//    @RequiresPermissions(value = "分页查询现场作业列表", authorities = "post:work:siteWork:pageList")
    @PostMapping("/ownPageList")
    public JsonResult ownPageList(@RequestBody SiteWork siteWork) {
        siteWork.setLeadUserId(SysUtils.getSysUser().getUserId());
        startPage();
        List<SiteWork> list = siteWorkService.selectSiteWorkList(siteWork);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    @GetMapping("/ownPageList")
    public JsonResult pageList(){
        SiteWork siteWork = new SiteWork();
        siteWork.setLeadUserId(SysUtils.getSysUser().getUserId());
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
     * 新增保存现场作业
     */
//    @Log(title = "现场作业管理", businessType = BusinessType.INSERT)
//    @RequiresPermissions(value = "新增保存现场作业", authorities = "post:work:siteWork:add")
    @PostMapping("/ownAdd")
    public JsonResult ownAddSave(@RequestBody SiteWorkVo siteWorkVo) {
        SysUser sysUser = SysUtils.getSysUser();
        SiteWork siteWork = siteWorkVo.getSiteWork();
        siteWork.setLeadUserId(sysUser.getUserId());
        siteWork.setLeadUserName(sysUser.getUserName());
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

    /**
     * 更改状态
     * @param workStatus
     * @param postModel
     * @return
     */
    @PostMapping("/changeWorkStatus")
    public JsonResult changeWorkStatus(@RequestParam("workStatus") int workStatus, @RequestBody PostModel postModel){
        SiteWork siteWork = new SiteWork();
        siteWork.setId(postModel.getId());
        siteWork.setWorkStatus(workStatus);
        siteWorkMapper.updateSiteWork(siteWork);
        return JsonResult.ok().put("status",workStatus);
    }

    /**
     * 定时获取作业数据
     * @param workData
     * @return
     */
    @PostMapping("/getWorkData")
    public JsonResult getWorkData(@RequestBody WorkData workData) throws ExecutionException, InterruptedException {
        WorkData workData1 = ApplicationListener.executorService.submit(()->{
            WorkData.RemoteData remoteData = workData.getRemoteData();
            remoteData.setDevice1(opcTask.get(remoteData.getDevice1(),String.class));
            remoteData.setDevice2(opcTask.get(remoteData.getDevice2(),String.class));
            remoteData.setDevice3(opcTask.get(remoteData.getDevice3(),String.class));
            remoteData.setDevice4(opcTask.get(remoteData.getDevice4(),String.class));
            remoteData.setDevice5(opcTask.get(remoteData.getDevice5(),String.class));
            List<WorkData.Person> persons = workData.getPersons();
            persons.forEach(person -> {
                String bloodPress = Double.valueOf(opcTask.get(person.getHeightPress(),String.class)).intValue()+"/"+  Double.valueOf(opcTask.get(person.getLowPress(),String.class)).intValue()+"mmHg";
                person.setBloodPress(bloodPress);
                person.setHeartRate(Double.valueOf(opcTask.get(person.getHeartRate(),String.class)).intValue()+"次/分钟");
                double sk = Double.parseDouble(opcTask.get(person.getSkinT(),String.class));
                DecimalFormat df = new DecimalFormat(".0");
                person.setSkinT(df.format(sk)+"℃");
            });
            return workData;
        }).get();
        return JsonResult.ok().put("data",workData1);
    }
}
