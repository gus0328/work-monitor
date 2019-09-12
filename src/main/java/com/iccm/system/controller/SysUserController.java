package com.iccm.system.controller;


import com.iccm.common.*;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.page.TableDataInfo;
import com.iccm.common.properties.SystemProperties;
import com.iccm.common.utils.StringUtil;
import com.iccm.system.model.PostModel;
import com.iccm.system.model.ResetPwd;
import com.iccm.system.model.SysUser;
import com.iccm.system.service.IRoleService;
import com.iccm.system.service.ISysUserService;
import com.wangfan.endecrypt.utils.EndecryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户信息
 * 
 * @author GXJ
 */
@PermissionsApi(value = "用户管理",authorities = "system:user")
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    private String prefix = "system/user";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private SystemProperties systemProperties;

    /**
     * 分页查询用户
     * @param user
     * @return
     */
    @RequiresPermissions(value = "分页查询用户", authorities = "post:system:user:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        list.forEach(sysUser -> {
            userService.setPostInfo(sysUser);
            userService.setRoleInfo(sysUser);
            sysUser.setRpassword(sysUser.getPassword());
        });
        TableDataInfo tableDataInfo = getDataTable(list);
        return JsonResult.ok().put("data",tableDataInfo);
    }

    /**
     * 导出用户数据
     * @param user
     * @return
     */
    @RequiresPermissions(value = "导出用户数据", authorities = "post:system:user:export")
    @PostMapping("/export")
    public JsonResult export(@RequestBody SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    /**
     * 导入用户数据
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = "导入用户数据", authorities = "post:system:user:importData")
    @PostMapping("/importData")
    public JsonResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = SysUtils.getSysUser().getUserName();
        String message = userService.importUser(userList, updateSupport, operName);
        return JsonResult.ok(message);
    }

    /**
     * 导入数据模板
     * @return
     */
    @RequiresPermissions(value = "导入数据模板", authorities = "post:system:user:importTemplate")
    @GetMapping("/importTemplate")
    public JsonResult importTemplate()
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions(value = "新增保存用户", authorities = "post:system:user:add")
    @PostMapping("/add")
    public JsonResult addSave(@Validated @RequestBody SysUser user)
    {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName())))
        {
            return JsonResult.error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return JsonResult.error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return JsonResult.error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setPassword(EndecryptUtils.encrytMd5(user.getPassword()));
        user.setCreateBy(SysUtils.getSysUser().getUserName());
        userService.insertUser(user);
        return JsonResult.ok();
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions(value = "修改保存用户", authorities = "post:system:user:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@Validated @RequestBody SysUser user)
    {
        if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return JsonResult.error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return JsonResult.error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        if(StringUtils.isNotBlank(user.getPassword())&&user.getPassword().length()<30){
            user.setPassword(EndecryptUtils.encrytMd5(user.getPassword()));
        }
        user.setUpdateBy(SysUtils.getSysUser().getUserName());
        userService.updateUser(user);
        return JsonResult.ok();
    }

    @PostMapping("/resetPwd")
    public JsonResult resetPwdSave(HttpSession session,@Validated @RequestBody ResetPwd resetPwd)
    {
        SysUser sysUser = SysUtils.getSysUser();
        if(sysUser.getPassword().equals(EndecryptUtils.encrytMd5(resetPwd.getOldPassword()))){
            sysUser.setPassword(EndecryptUtils.encrytMd5(resetPwd.getNewPassword()));
            sysUser.setUpdateBy(SysUtils.getSysUser().getUserName());
            userService.resetUserPwd(sysUser);
            session.setAttribute("user",sysUser);
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    @PostMapping("/adminResetPwd")
    public JsonResult resetPwd(@RequestBody PostModel postModel){
        SysUser sysUser = new SysUser();
        sysUser.setUserId(Long.parseLong(postModel.getId()));
        sysUser.setPassword(EndecryptUtils.encrytMd5(systemProperties.getInitPass()));
        sysUser.setUpdateBy(SysUtils.getSysUser().getUserName());
        userService.updateUserInfo(sysUser);
        return JsonResult.ok("用户密码已重置");
    }

    /**
     * 删除用户
     * @param postModel
     * @return
     */
    @RequiresPermissions(value = "删除用户", authorities = "post:system:user:remove")
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel)
    {
        try
        {
            userService.deleteUserByIds(postModel.getId());
            return JsonResult.ok();
        }
        catch (Exception e)
        {
            return JsonResult.error();
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    public String checkLoginNameUnique(@RequestBody SysUser user)
    {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    public String checkPhoneUnique(@RequestBody SysUser user)
    {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    public JsonResult checkEmailUnique(@RequestBody SysUser user)
    {
        return JsonResult.ok().put("data",userService.checkEmailUnique(user));
    }

    /**
     * 用户状态修改
     */
    @RequiresPermissions(value = "用户状态修改", authorities = "post:system:user:changeStatus")
    @PostMapping("/changeStatus")
    public JsonResult changeStatus(@RequestBody SysUser user)
    {
        user.setUpdateBy(SysUtils.getSysUser().getUserName());
        userService.changeStatus(user);
        return JsonResult.ok();
    }
}