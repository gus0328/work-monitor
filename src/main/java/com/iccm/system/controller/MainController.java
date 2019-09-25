package com.iccm.system.controller;

import com.iccm.common.JsonResult;
import com.iccm.system.mapper.MenuMapper;
import com.iccm.system.mapper.MessageMapper;
import com.iccm.system.mapper.RoleMenuMapper;
import com.iccm.system.model.SysUser;
import com.iccm.system.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/9/9.
 */
@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private IMessageService messageService;

    @GetMapping("/user/info")
    public JsonResult userInfo(HttpSession session) {
        SysUser sysUser = (SysUser) session.getAttribute("user");
        List<Long> menuIds = roleMenuMapper.getMenuIdsByUserId(sysUser.getUserId());
        List<String> permission = new ArrayList<>();
        menuIds.forEach(menuId ->{
            permission.add(menuMapper.queryMenuNameByMenuId(menuId));
        });
        int count = messageService.queryUnreadCount();
        return JsonResult.ok().put("permission", permission).put("avatar",sysUser.getAvatar()).put("userName",sysUser.getUserName()).put("unreadCount",count);
    }
}
