package com.iccm.system.controller;

import com.iccm.common.CacheName;
import com.iccm.common.FileUtil;
import com.iccm.common.JsonResult;
import com.iccm.common.SysUtils;
import com.iccm.common.properties.SystemProperties;
import com.iccm.common.utils.ExcelTool;
import com.iccm.common.utils.ImportSheetData;
import com.iccm.common.utils.StringUtil;
import com.iccm.common.utils.UUIDUtil;
import com.iccm.system.mapper.ContractMapper;
import com.iccm.system.mapper.SysUserMapper;
import com.iccm.system.model.Contract;
import com.iccm.system.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
    private SystemProperties systemProperties;

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SysUserMapper sysUserMapper;

    @RequestMapping("/test")
    public JsonResult upload(HttpServletRequest request) throws Exception {
        String[] fields1 = {"contractNo","contractDate","expiryDate","warnName"};
        List<ImportSheetData> list = new ArrayList<>();
        ImportSheetData importSheetData1 = new ImportSheetData(fields1,"contracts","Sheet1",1,0,4, Contract.class);
        list.add(importSheetData1);
        Map<String,List> map = ExcelTool.uploadData(request,list);
        String token = request.getParameter("token");
        String loginName = cacheManager.getCache(CacheName.PCTOKENS).get(token,String.class);
        if(map!=null){
            List<Contract> list1 = map.get("contracts");
            for(Contract contract:list1){
                if(StringUtils.isBlank(contract.getWarnName())){
                    contract.setWarnName(loginName);
                    contract.setStatus(0);
                }
                contract.setCreateBy(loginName);
                contract.setCreateTime(new Date());
                contractMapper.insertContract(contract);
            }
        }
        return JsonResult.ok();
    }

    @GetMapping("/query")
    public JsonResult query(Contract contract,HttpServletRequest request){
        List<Contract> list = contractMapper.selectContractList(contract);
        return JsonResult.ok().put("data",list).put("token",request.getHeader("token"));
    }

    //处理文件上传
    @RequestMapping("/uploadAvator")
    public JsonResult uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();  //获取上传文件原名
        int index = fileName.lastIndexOf(".");
        String newFileName = UUIDUtil.randomUUID32()+fileName.substring(index,fileName.length());
        try {
            FileUtil.uploadFile(file.getBytes(), systemProperties.getAvatorPath()+"/", newFileName);
        } catch (Exception e) {
            return JsonResult.error("文件上传失败");
        }
        String token = request.getParameter("token");
        String loginName = cacheManager.getCache(CacheName.PCTOKENS).get(token,String.class);
        SysUser sysUser = sysUserMapper.selectUserByLoginName(loginName);
        if(StringUtils.isNotBlank(sysUser.getAvatar())){
            try{
                File delFile = new File(systemProperties.getAvatorPath()+sysUser.getAvatar().substring(7,sysUser.getAvatar().length()));
                delFile.delete();
            }catch (Exception e){
                //no need deal
            }
        }
        String avatar = "/avator/"+newFileName;
        sysUser.setAvatar(avatar);
        sysUserMapper.updateUser(sysUser);
        return JsonResult.ok().put("data",avatar);
    }
}
