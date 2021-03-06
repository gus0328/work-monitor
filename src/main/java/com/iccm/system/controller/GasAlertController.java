package com.iccm.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.iccm.common.BaseController;
import com.iccm.common.JsonResult;
import com.iccm.system.model.GasAlert;
import com.iccm.system.model.PostModel;
import com.iccm.system.service.IGasAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2019/11/24.
 */
@RestController
@RequestMapping("/gasAlert")
public class GasAlertController extends BaseController {

    @Autowired
    private IGasAlertService gasAlertService;

    /**
     * 查询气体报警设置列表
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody GasAlert gasAlert)
    {
        List<GasAlert> list = gasAlertService.selectGasAlertList(gasAlert);
        return JsonResult.ok().put("data",list);
    }

    /**
     * 获取
     * @return
     */
    @GetMapping("/getAlertValue")
    public JsonResult getAlertValue(){
        List<GasAlert> list = gasAlertService.selectGasAlertList(new GasAlert());
        JSONObject jsonObject = new JSONObject();
        list.forEach(gasAlert -> {
            jsonObject.put("device"+gasAlert.getGasType(),gasAlert.toString());
        });
        return JsonResult.ok().put("data",jsonObject);
    }

    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody GasAlert gasAlert){
        startPage();
        List<GasAlert> list = gasAlertService.selectGasAlertList(gasAlert);
        return JsonResult.ok().put("data", getDataTable(list));
    }

    /**
     * 新增保存气体报警设置
     */
    @PostMapping("/add")
    public JsonResult addSave(@RequestBody GasAlert gasAlert)
    {
        gasAlertService.insertGasAlert(gasAlert);
        return JsonResult.ok();
    }

    /**
     * 修改保存气体报警设置
     */
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody GasAlert gasAlert)
    {
        gasAlertService.updateGasAlert(gasAlert);
        return JsonResult.ok();
    }

    /**
     * 删除气体报警设置
     */
    @PostMapping( "/remove")
    public JsonResult remove(@RequestBody PostModel postModel)
    {
        gasAlertService.deleteGasAlertById(Long.parseLong(postModel.getId()));
        return JsonResult.ok();
    }
}
