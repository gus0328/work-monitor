package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.SysUtils;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.system.model.Message;
import com.iccm.system.model.PostModel;
import com.iccm.system.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消息管理Controller
 *
 * @author gxj
 * @date 2019-09-17
 */
@PermissionsApi(value = "消息管理管理", authorities = "system:message")
@RestController
@RequestMapping("/system/message")
public class MessageController extends BaseController {
    @Autowired
    private IMessageService messageService;

    /**
     * 查询消息管理列表
     */
//    @RequiresPermissions(value = "查询消息管理列表", authorities = "post:system:message:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody Message message) {
        List<Message> list = messageService.selectMessageList(message);
        return JsonResult.ok().put("data", list);
    }

    /**
     * 分页查询消息管理列表
     */
//    @RequiresPermissions(value = "分页查询消息管理列表", authorities = "post:system:message:pageList")
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody Message message) {
        startPage();
        List<Message> list = messageService.selectMessageList(message);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    /**
     * 导出消息管理列表
     */
//    @RequiresPermissions(value = "导出消息管理列表", authorities = "post:system:message:export")
    @PostMapping("/export")
    public JsonResult export(@RequestBody Message message) {
        List<Message> list = messageService.selectMessageList(message);
        ExcelUtil<Message> util = new ExcelUtil<Message>(Message.class);
        return util.exportExcel(list, "message");
    }

    /**
     * 新增保存消息管理
     */
//    @RequiresPermissions(value = "新增保存消息管理", authorities = "post:system:message:add")
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody Message message) {
        messageService.insertMessage(message);
        return JsonResult.ok();
    }

    /**
     * 修改保存消息管理
     */
//    @RequiresPermissions(value = "修改保存消息管理", authorities = "post:system:message:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody Message message) {
        messageService.updateMessage(message);
        return JsonResult.ok();
    }

    /**
     * 删除消息管理
     */
//    @RequiresPermissions(value = "删除消息管理", authorities = "post:system:message:remove")
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel) {
        messageService.deleteMessageById(postModel.getId());
        return JsonResult.ok();
    }

    @GetMapping("/queryNormalList")
    public JsonResult queryNormalList(){
        Message message = new Message();
        message.setStatus(4);
        message.setUserId(SysUtils.getSysUser().getUserId());
        startPage();
        List<Message> list = messageService.selectMessageList1(message);
        int unread = messageService.queryUnreadCount();
        int read = messageService.queryReadCount();
        return JsonResult.ok().put("data",getDataTable(list)).put("unread",unread).put("read",read);
    }

    @GetMapping("/queryDelList")
    public JsonResult queryDelList(){
        Message message = new Message();
        message.setStatus(2);
        message.setUserId(SysUtils.getSysUser().getUserId());
        startPage();
        List<Message> list = messageService.selectMessageList1(message);
        return JsonResult.ok().put("data",getDataTable(list));
    }

    @GetMapping("/queryContentById")
    public JsonResult queryMessageContent(String id){
        Message message = messageService.selectMessageById(id);
        return JsonResult.ok().put("data",message);
    }

    @PostMapping("changeToRead")
    public JsonResult changeToRead(@RequestBody PostModel postModel){
        Message message = new Message();
        message.setId(postModel.getId());
        message.setStatus(1);
        messageService.updateMessage(message);
        int unread = messageService.queryUnreadCount();
        return JsonResult.ok().put("unread",unread);
    }

    /**
     * 查询未读消息数量
     * @return
     */
    @GetMapping("/getUnreadMessageCount")
    public JsonResult queryUnreadCount(){
        return JsonResult.ok().put("count",messageService.queryUnreadCount());
    }

}
