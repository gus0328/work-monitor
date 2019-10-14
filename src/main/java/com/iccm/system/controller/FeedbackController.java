package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.SysUtils;
import com.iccm.system.mapper.FeedbackMapper;
import com.iccm.system.model.Feedback;
import com.iccm.system.model.PostModel;
import com.iccm.system.model.SysUser;
import com.iccm.system.service.IFeedbackService;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 反馈意见Controller
 *
 * @author gxj
 * @date 2019-10-10
 */
@RestController
@RequestMapping("/system/feedback")
public class FeedbackController extends BaseController {
    @Autowired
    private IFeedbackService feedbackService;

    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 查询反馈意见列表
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody Feedback feedback) {
        List<Feedback> list = feedbackService.selectFeedbackList(feedback);
        return JsonResult.ok().put("data", list);
    }

    /**
     * 分页查询反馈意见列表
     */
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody Feedback feedback) {
        startPage();
        List<Feedback> list = feedbackService.selectFeedbackList(feedback);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    /**
     * app分页查询
     * @param title
     * @return
     */
    @GetMapping("/appPageList")
    public JsonResult appPageList(String title){
        Feedback feedback = new Feedback();
        feedback.setTitle(title);
        feedback.setCreateById(SysUtils.getSysUser().getUserId());
        startPage();
        List<Feedback> list = feedbackMapper.appSelectFeedbackList(feedback);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    /**
     * 查询
     * @return
     */
    @GetMapping("/pageList")
    public JsonResult pageList(String title){
        startPage();
        Feedback feedback = new Feedback();
        feedback.setTitle(title);
        feedback.setCreateById(SysUtils.getSysUser().getUserId());
        List<Feedback> list = feedbackService.selectFeedbackList(feedback);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    /**
     * 导出反馈意见列表
     */
    @PostMapping("/export")
    public JsonResult export(@RequestBody Feedback feedback) {
        List<Feedback> list = feedbackService.selectFeedbackList(feedback);
        ExcelUtil<Feedback> util = new ExcelUtil<Feedback>(Feedback.class);
        return util.exportExcel(list, "feedback");
    }

    /**
     * 新增保存反馈意见
     */
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody Feedback feedback) {
        SysUser sysUser = SysUtils.getSysUser();
        feedback.setCreateBy(sysUser.getUserName());
        feedback.setCreateTime(new Date());
        feedback.setStatus(0L);
        feedback.setCreateById(sysUser.getUserId());
        feedback.setDetpName(sysUser.getDept().getDeptName());
        feedbackService.insertFeedback(feedback);
        return JsonResult.ok();
    }

    /**
     * 修改保存反馈意见
     */
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody Feedback feedback) {
        SysUser sysUser = SysUtils.getSysUser();
        feedback.setUpdateBy(sysUser.getUserName());
        feedback.setUpdateTime(new Date());
        feedbackService.updateFeedback(feedback);
        return JsonResult.ok();
    }

    @PostMapping("/changeStatus")
    public JsonResult changeStatus(@RequestBody Feedback feedback){
        feedbackService.updateFeedback(feedback);
        return JsonResult.ok();
    }

    /**
     * 删除反馈意见
     */
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel) {
        feedbackService.deleteFeedbackById(Long.parseLong(postModel.getId()));
        return JsonResult.ok();
    }

    /**
     * 查询详情
     * @param id
     * @return
     */
    @GetMapping("/getFeedbackDetails")
    public JsonResult getFeedbackDetails(Long id){
        return JsonResult.ok().put("details",feedbackService.selectFeedbackById(id));
    }

    @GetMapping("/appGetCount")
    public JsonResult appGetCount(){
        SysUser sysUser = SysUtils.getSysUser();
        return JsonResult.ok().put("count",feedbackMapper.selectAppUnreadCount(sysUser.getUserId()));
    }

}
