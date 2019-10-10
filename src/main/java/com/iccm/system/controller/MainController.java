package com.iccm.system.controller;

import com.iccm.common.Convert;
import com.iccm.common.FileUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.SysUtils;
import com.iccm.common.properties.SystemProperties;
import com.iccm.common.utils.UUIDUtil;
import com.iccm.system.mapper.MenuMapper;
import com.iccm.system.mapper.MessageMapper;
import com.iccm.system.mapper.RoleMenuMapper;
import com.iccm.system.mapper.SysUserMapper;
import com.iccm.system.model.PostModel;
import com.iccm.system.model.SysUser;
import com.iccm.system.service.IMessageService;
import com.iccm.system.service.ISysUserService;
import com.wangfan.endecrypt.utils.EndecryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private SystemProperties systemProperties;

    @GetMapping("/user/info")
    public JsonResult userInfo(HttpSession session) {
        SysUser sysUser = (SysUser) session.getAttribute("user");
        if(sysUser==null){
            return JsonResult.error("登录超时");
        }
        List<Long> menuIds = roleMenuMapper.getMenuIdsByUserId(sysUser.getUserId());
        List<String> permission = new ArrayList<>();
        menuIds.forEach(menuId ->{
            permission.add(menuMapper.queryMenuNameByMenuId(menuId));
        });
        int count = messageService.queryUnreadCount();
        return JsonResult.ok().put("permission", permission).put("avatar",sysUser.getAvatar()).put("userName",sysUser.getUserName()).put("unreadCount",count).put("data","login");
    }

    @GetMapping("/verifyOldPass")
    public JsonResult verifyPassWord(String oldPass){
        SysUser sysUser = SysUtils.getSysUser();
        boolean flag = sysUser.getPassword().equals(EndecryptUtils.encrytMd5(oldPass));
        return JsonResult.ok().put("data",flag);
    }

    @PostMapping("/ownpacePassSave")
    public JsonResult ownpacePassSave(@RequestBody PostModel postModel){
        SysUser sysUser = SysUtils.getSysUser();
        sysUser.setPassword(EndecryptUtils.encrytMd5(postModel.getId()));
        sysUserMapper.updateUser(sysUser);
        return JsonResult.ok();
    }

    @GetMapping("/ownspaceInfo")
    public JsonResult ownspaceInfo(HttpServletRequest request){
        SysUser sysUser = SysUtils.getSysUser();
        sysUser.setPassword("");
        sysUserService.setPostInfo(sysUser);
        sysUserService.setRoleInfo(sysUser);
        return JsonResult.ok().put("data",sysUser).put("token",request.getHeader("token"));
    }

    @PostMapping("/ownspaceInfoSave")
    public JsonResult ownspaceInfoSave(@RequestParam("userName") String userName,@RequestParam("telephone") String telephone){
        SysUser sysUser = SysUtils.getSysUser();
        sysUser.setUserName(userName);
        sysUser.setPhonenumber(telephone);
        sysUserMapper.updateUser(sysUser);
        return JsonResult.ok();
    }

}
