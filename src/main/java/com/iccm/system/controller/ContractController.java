package com.iccm.system.controller;

import com.iccm.common.BaseController;
import com.iccm.common.JsonResult;
import com.iccm.common.SysUtils;
import com.iccm.common.annotation.RequiresPermissions;
import com.iccm.common.enums.BusinessType;
import com.iccm.common.page.TableDataInfo;
import com.iccm.common.utils.ExcelTool;
import com.iccm.common.utils.ImportSheetData;
import com.iccm.system.model.Contract;
import com.iccm.system.model.PostModel;
import com.iccm.system.service.IContractService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/2.
 */
@RestController
@RequestMapping("/contract")
public class ContractController extends BaseController{

    @Autowired
    private IContractService contractService;

    @PostMapping("/importData")
    public JsonResult importTemplate(HttpServletRequest request, boolean updateSupport) throws Exception {
        String[] fields1 = {"contractNo","contractName","provideGoods","contractGoodsArrivalDate","contractStopDate","contractStatus","totalAccount","totalPay","payWarnBfb","noPay","premium","filingDate","expiryDate","warnName","warnTime"};
        List<ImportSheetData> list = new ArrayList<>();
        ImportSheetData importSheetData1 = new ImportSheetData(fields1,"contracts","Sheet1",2,0,15, Contract.class);
        list.add(importSheetData1);
        Map<String,List> map = ExcelTool.uploadData(request,list);
        String message = "导入数据失败，请检查excel数据是否有错。";
        if(map!=null){
            List<Contract> list1 = map.get("contracts");
            message = contractService.importData(list1,updateSupport);
        }
        return JsonResult.ok(message);
    }

    /**
     * 修改保存合同超期提醒
     */
    @PostMapping("/edit")
    public JsonResult editSave(@RequestBody Contract contract)
    {
        try{
            contractService.updateContract(contract);
        }catch (Exception e){
            return JsonResult.error("保存失败:"+e.getMessage());
        }
        return JsonResult.ok();
    }

    /**
     * 查询岗位信息列表
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody Contract contract) {
        contract.setCreateBy(SysUtils.getSysUser().getUserId()+"");
        List<Contract> list = contractService.selectContractList(contract);
        return JsonResult.ok().put("data", list);
    }

    /**
     * 分页查询岗位信息列表
     */
    @PostMapping("/pageList")
    public JsonResult pageList(@RequestBody Contract contract) {
        contract.setCreateBy(SysUtils.getSysUser().getUserId()+"");
        startPage();
        List<Contract> list = contractService.selectContractList(contract);
        TableDataInfo tableDataInfo = getDataTable(list);
        return JsonResult.ok().put("data",tableDataInfo);
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public JsonResult remove(@RequestBody PostModel postModel) {
        contractService.deleteContractById(Integer.parseInt(postModel.getId()));
        return JsonResult.ok();
    }

    /**
     * 不再提醒
     * @param postModel
     * @return
     */
    @PostMapping("/noWarn")
    public JsonResult noWarn(@RequestBody PostModel postModel){
        Contract contract = new Contract();
        contract.setId(Integer.parseInt(postModel.getId()));
        contract.setStatus(1);
        contractService.updateContract(contract);
        return JsonResult.ok();
    }
}
