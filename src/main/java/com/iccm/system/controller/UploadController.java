package com.iccm.system.controller;

import com.iccm.common.JsonResult;
import com.iccm.common.SysUtils;
import com.iccm.common.utils.ExcelTool;
import com.iccm.common.utils.ImportSheetData;
import com.iccm.common.utils.StringUtil;
import com.iccm.system.mapper.ContractMapper;
import com.iccm.system.model.Contract;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/9/17.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private ContractMapper contractMapper;

    @RequestMapping("/test")
    public JsonResult upload(HttpServletRequest request) throws Exception {
        String[] fields1 = {"contractNo","contractDate","expiryDate","warnName"};
        List<ImportSheetData> list = new ArrayList<>();
        ImportSheetData importSheetData1 = new ImportSheetData(fields1,"contracts","Sheet1",1,0,3, Contract.class);
        list.add(importSheetData1);
        Map<String,List> map = ExcelTool.uploadData(request,list);
        if(map!=null){
            List<Contract> list1 = map.get("contracts");
            for(Contract contract:list1){
                if(StringUtils.isBlank(contract.getWarnName())){
                    contract.setWarnName(SysUtils.getSysUser().getLoginName());
                    contract.setStatus(0);
                }
                contract.setCreateBy(SysUtils.getSysUser().getLoginName());
                contract.setCreateTime(new Date());
                contractMapper.insertContract(contract);
            }
        }
        return JsonResult.ok();
    }

    @GetMapping("/query")
    public JsonResult query(Contract contract){
        List<Contract> list = contractMapper.selectContractList(contract);
        return JsonResult.ok().put("data",list);
    }
}
