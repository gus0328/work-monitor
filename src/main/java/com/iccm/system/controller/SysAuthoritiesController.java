package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.Constants;
import com.iccm.common.JsonResult;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.system.model.PostModel;
import com.iccm.system.model.SysAuthorities;
import com.iccm.system.service.ISysAuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 权限标识Controller
 *
 * @author gxj
 * @date 2019-09-08
 */
@PermissionsApi(value = "权限标识管理",authorities = "system:authorities")
@RestController
@RequestMapping("/system/authorities")
public class SysAuthoritiesController extends BaseController {
    @Autowired
    private ISysAuthoritiesService sysAuthoritiesService;

    @Autowired
    private WebApplicationContext applicationContext;

    /**
     * 查询权限标识列表
     */
    @RequiresPermissions(value = "查询权限标识列表",authorities = "post:system:authorities:list")
    @PostMapping("/list")
    public JsonResult list(@RequestBody SysAuthorities sysAuthorities) {
       return JsonResult.ok().put("data",sysAuthoritiesService.selectSysAuthoritiesList(sysAuthorities));
    }

//    /**
//     * 分页查询权限标识列表
//     */
//    @RequiresPermissions(value = "分页查询权限标识列表",authorities = "post:system:authorities:pageList")
//    @PostMapping("/pageList")
//    public TableDataInfo pageList(@RequestBody SysAuthorities sysAuthorities) {
//        startPage();
//        List<SysAuthorities> list = sysAuthoritiesService.selectSysAuthoritiesList(sysAuthorities);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出权限标识列表
//     */
//    @RequiresPermissions(value = "导出权限标识列表",authorities = "post:system:authorities:export")
//    @PostMapping("/export")
//    public JsonResult export(@RequestBody SysAuthorities sysAuthorities) {
//        List<SysAuthorities> list = sysAuthoritiesService.selectSysAuthoritiesList(sysAuthorities);
//        ExcelUtil<SysAuthorities> util = new ExcelUtil<SysAuthorities>(SysAuthorities.class);
//        return util.exportExcel(list, "authorities");
//    }

    /**
     * 新增保存权限标识
     */
    @RequiresPermissions(value = "新增保存权限标识",authorities = "post:system:authorities:add")
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody SysAuthorities sysAuthorities) {
        sysAuthoritiesService.insertSysAuthorities(sysAuthorities);
        return JsonResult.ok();
    }

    /**
     * 修改保存权限标识
     */
    @RequiresPermissions(value = "修改保存权限标识",authorities = "post:system:authorities:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody SysAuthorities sysAuthorities) {
        sysAuthoritiesService.updateSysAuthorities(sysAuthorities);
        return JsonResult.ok();
    }

    /**
     * 删除权限标识
     */
    @RequiresPermissions(value = "删除权限标识",authorities = "post:system:authorities:remove")
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel) {
        sysAuthoritiesService.deleteSysAuthoritiesById(postModel.getId());
        return JsonResult.ok();
    }

    /**
     * 同步权限标识
     */
    @RequiresPermissions(value = "同步权限标识",authorities = "post:system:authorities:sync")
    @PostMapping("/sync")
    public JsonResult getAllURL() {
        Map<String,Object> beans = applicationContext.getBeansWithAnnotation(PermissionsApi.class);
        sysAuthoritiesService.deleteAll();
        long index = 0;
        for(Object bean : beans.values()){
            PermissionsApi permissionsApi = bean.getClass().getAnnotation(PermissionsApi.class);
            SysAuthorities sysAuthorities = new SysAuthorities(permissionsApi.authorities(),permissionsApi.value(), Constants.SYS_AUTH_API,index,Constants.SYS_AUTH_API);
            sysAuthoritiesService.insertSysAuthorities(sysAuthorities);
            Method[] methods = bean.getClass().getDeclaredMethods();
            long methodIndex = 0;
            for (Method declaredMethod : methods) {
                RequiresPermissions requiresPermissions = AnnotationUtils.findAnnotation(declaredMethod, RequiresPermissions.class);
                if (requiresPermissions != null) {
                    SysAuthorities sysAuthorities1 = new SysAuthorities(requiresPermissions.authorities(), requiresPermissions.value(), sysAuthorities.getAuthorityName(), methodIndex, sysAuthorities.getAuthority());
                    sysAuthoritiesService.insertSysAuthorities(sysAuthorities1);
                    methodIndex++;
                }
            }
            index++;
        }
        return JsonResult.ok();
    }

}
