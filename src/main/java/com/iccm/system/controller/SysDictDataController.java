package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.ExcelUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.SysUtils;
import com.iccm.common.annotation.Log;
import com.iccm.common.annotation.PermissionsApi;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.enums.BusinessType;
import com.iccm.common.page.TableDataInfo;
import com.iccm.system.mapper.SysDictDataMapper;
import com.iccm.system.model.PostModel;
import com.iccm.system.model.SysDictData;
import com.iccm.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

;

/**
 * 数据字典信息
 * 
 * @author gxj
 */
@PermissionsApi(value = "数据字典信息",authorities = "system:dict:data")
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController
{

    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询字典内容
     * @param dictData
     * @return
     */
    @RequiresPermissions(value = "查询字典内容",authorities = "post:system:dict:data:list")
    @PostMapping("/list")
    public JsonResult list(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        TableDataInfo tableDataInfo = getDataTable(list);
        return JsonResult.ok().put("data",tableDataInfo);
    }

    /**
     * 根据字典类型查询
     * @param postModel
     * @return
     */
    @RequiresPermissions(value = "根据字典类型查询",authorities = "post:system:dict:data:list1")
    @PostMapping("/list1")
    public JsonResult list2(@RequestBody PostModel postModel){
        SysDictData sysDictData = new SysDictData();
        sysDictData.setDictType(postModel.getId());
        List<SysDictData> list = dictDataService.selectDictDataList(sysDictData);
        return JsonResult.ok().put("data",list);
    }

    /**
     * 导出字典信息
     * @param dictData
     * @return
     */
    @RequiresPermissions(value = "导出字典信息",authorities = "post:system:dict:data:export")
    @PostMapping("/export")
    public JsonResult export(SysDictData dictData)
    {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        return util.exportExcel(list, "字典数据");
    }

    /**
     * 新增保存字典类型
     */
    @RequiresPermissions(value = "新增保存字典类型",authorities = "post:system:dict:data:add")
    @PostMapping("/add")
    public JsonResult addSave(@Validated @RequestBody SysDictData dict)
    {
        dict.setCreateBy(SysUtils.getSysUser().getUserName());
        dictDataService.insertDictData(dict);
        return JsonResult.ok();
    }

    /**
     * 修改保存字典类型
     */
    @RequiresPermissions(value = "修改保存字典类型",authorities = "post:system:dict:data:edit")
    @PostMapping("/edit")
    public JsonResult editSave(@Validated @RequestBody SysDictData dict)
    {
        dict.setUpdateBy(SysUtils.getSysUser().getUserName());
        dictDataService.updateDictData(dict);
        return JsonResult.ok();
    }

    /**
     * 根据id删除字典信息
     * @param postModel
     * @return
     */
    @RequiresPermissions(value = "根据id删除字典信息",authorities = "post:system:dict:data:removeById")
    @PostMapping("/removeById")
    public JsonResult removeById(@RequestBody PostModel postModel){
        dictDataService.deleteDictDataById(Long.parseLong(postModel.getId()));
        return JsonResult.ok();
    }

    /**
     * 根据字典类型查询
     * @param type
     * @return
     */
    @GetMapping("/selectDataByType")
    public JsonResult selectDataByType(String type){
        return JsonResult.ok().put("data",sysDictDataMapper.selectDataByType(type));
    }
}
