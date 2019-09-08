package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.UserConstants;
import com.iccm.common.annotation.Log;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.enums.BusinessType;
import com.iccm.common.page.TableDataInfo;
import com.iccm.system.model.PostModel;
import com.iccm.system.model.SysDictType;
import com.iccm.system.model.Ztree;
import com.iccm.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典信息
 * 
 * @author gxj
 */
@PermissionsApi(value = "字典类型管理",authorities = "system:dict")
@RestController
@RequestMapping("/system/dict")
public class SysDictTypeController extends BaseController
{

    @Autowired
    private ISysDictTypeService dictTypeService;

    /**
     * 查询字典类型
     * @param dictType
     * @return
     */
    @RequiresPermissions(value = "查询字典类型",authorities = "post:system:dict:list")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysDictType dictType)
    {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    /**
     * 字典类型导出
     * @param dictType
     * @return
     */
    @RequiresPermissions(value = "字典类型导出",authorities = "post:system:dict:export")
    @PostMapping("/export")
    public JsonResult export(@RequestBody SysDictType dictType)
    {
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        ExcelUtil<SysDictType> util = new ExcelUtil<SysDictType>(SysDictType.class);
        return util.exportExcel(list, "字典类型");
    }

    /**
     * 新增保存字典类型
     */
    @RequiresPermissions(value = "新增保存字典类型",authorities = "post:system:dict:add")
    @PostMapping("/add")
    public JsonResult addSave(@Validated @RequestBody SysDictType dict)
    {
        if (UserConstants.DICT_TYPE_NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return JsonResult.error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
//        dict.setCreateBy(ShiroUtils.getLoginName());
        dictTypeService.insertDictType(dict);
        return JsonResult.ok();
    }

    /**
     * 修改保存字典类型
     */
    @RequiresPermissions(value = "修改保存字典类型",authorities = "post:system:dict:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@Validated @RequestBody SysDictType dict)
    {
        if (UserConstants.DICT_TYPE_NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return JsonResult.error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
//        dict.setUpdateBy(ShiroUtils.getLoginName());
        dictTypeService.updateDictType(dict);
        return JsonResult.ok();
    }

    /**
     * 删除字典类型
     * @param postModel
     * @return
     */
    @RequiresPermissions(value = "删除字典类型",authorities = "post:system:dict:removeById")
    @PostMapping("/removeById")
    public JsonResult removeById(@RequestBody PostModel postModel){
        dictTypeService.deleteDictTypeById(Long.parseLong(postModel.getId()));
        return JsonResult.ok();
    }

    /**
     * 校验字典类型
     */
    @RequiresPermissions(value = "校验字典类型",authorities = "post:system:dict:checkDictTypeUnique")
    @PostMapping("/checkDictTypeUnique")
    public String checkDictTypeUnique(SysDictType dictType)
    {
        return dictTypeService.checkDictTypeUnique(dictType);
    }

    /**
     * 加载字典列表树
     */
    @RequiresPermissions(value = "加载字典列表树",authorities = "get:system:dict:treeData")
    @GetMapping("/treeData")
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dictTypeService.selectDictTree(new SysDictType());
        return ztrees;
    }
}
