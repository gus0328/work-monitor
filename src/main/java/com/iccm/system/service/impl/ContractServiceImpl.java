package com.iccm.system.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.gexin.fastjson.JSON;
import com.iccm.common.Constants;
import com.iccm.common.DateUtils;
import com.iccm.common.SysUtils;
import com.iccm.common.config.ApplicationListener;
import com.iccm.common.exception.BusinessException;
import com.iccm.common.properties.SystemProperties;
import com.iccm.common.push.GetuiPush;
import com.iccm.common.push.TemplateType;
import com.iccm.common.utils.Md5Utils;
import com.iccm.common.utils.MessageUtil;
import com.iccm.common.utils.StringUtils;
import com.iccm.common.utils.UUIDUtil;
import com.iccm.common.websocket.SystemNoticeType;
import com.iccm.system.mapper.MessageMapper;
import com.iccm.system.model.Message;
import com.iccm.system.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.ContractMapper;
import com.iccm.system.model.Contract;
import com.iccm.system.service.IContractService;
import com.iccm.common.Convert;

/**
 * 合同超期提醒Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-17
 */
@Service
public class ContractServiceImpl implements IContractService 
{
    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private GetuiPush getuiPush;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private SystemProperties systemProperties;

    @Autowired
    private MessageUtil messageUtil;

    /**
     * 查询合同超期提醒
     * 
     * @param id 合同超期提醒ID
     * @return 合同超期提醒
     */
    @Override
    public Contract selectContractById(Integer id)
    {
        return contractMapper.selectContractById(id);
    }

    /**
     * 查询合同超期提醒列表
     * 
     * @param contract 合同超期提醒
     * @return 合同超期提醒
     */
    @Override
    public List<Contract> selectContractList(Contract contract)
    {
        return contractMapper.selectContractList(contract);
    }

    /**
     * 新增合同超期提醒
     * 
     * @param contract 合同超期提醒
     * @return 结果
     */
    @Override
    public int insertContract(Contract contract)
    {
        contract.setCreateTime(DateUtils.getNowDate());
        return contractMapper.insertContract(contract);
    }

    /**
     * 修改合同超期提醒
     * 
     * @param contract 合同超期提醒
     * @return 结果
     */
    @Override
    public int updateContract(Contract contract)
    {
        if(StringUtils.isNotBlank(contract.getPayWarnBfb())&&StringUtils.isNotBlank(contract.getTotalPay())&&StringUtils.isNotBlank(contract.getNoPay())){
            float bfb = Float.parseFloat(contract.getPayWarnBfb())/100;
            float totalPay = Float.parseFloat(contract.getTotalPay());
            float noPay = Float.parseFloat(contract.getNoPay());
            float realBfb = totalPay/(totalPay+noPay);
            String message = "合同："+contract.getContractNo()+" 合同付款额已经超过"+contract.getPayWarnBfb()+"%";
            if(realBfb>=bfb){
                ApplicationListener.executorService.submit(()->{
                    messageUtil.pushtoSingle(contract.getWarnName(),"合同付款提醒",contract.toString(),message, JSON.toJSONString(contract),TemplateType.notice,Constants.SYS_NOTICE,SystemNoticeType.ContractExpiry.getType());
                });
            }
        }
        contract.setUpdateBy(SysUtils.getSysUser().getUserName());
        contract.setUpdateTime(DateUtils.getNowDate());
        return contractMapper.updateContract(contract);
    }

    /**
     * 删除合同超期提醒对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteContractByIds(String ids)
    {
        return contractMapper.deleteContractByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合同超期提醒信息
     * 
     * @param id 合同超期提醒ID
     * @return 结果
     */
    public int deleteContractById(Integer id)
    {
        return contractMapper.deleteContractById(id);
    }

    /**
     * 导入合同数据
     *
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param contracts 导入合同
     * @return 结果
     */
    @Override
    public String importData(List<Contract> contracts, Boolean isUpdateSupport)
    {
        if (StringUtils.isNull(contracts) || contracts.size() == 0)
        {
            throw new BusinessException("导入合同数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Contract contract : contracts)
        {
            try
            {
                String expiryDate1 = contract.getExpiryDate();
                String contractStopDate = contract.getContractStopDate();
                contract.setExpiryDate(getFormatDate(expiryDate1));
                contract.setContractStopDate(getFormatDate(contractStopDate));
                Contract u = contractMapper.selectByContractNo(contract.getContractNo());
                if(org.apache.commons.lang3.StringUtils.isBlank(contract.getWarnName())){
                    contract.setWarnName(SysUtils.getSysUser().getLoginName());
                }
                if(org.apache.commons.lang3.StringUtils.isBlank(contract.getWarnTime())){
                    contract.setWarnTime(systemProperties.getWarnTime());
                }
                if (StringUtils.isNull(u))
                {
                    contract.setCreateTime(new Date());
                    contract.setCreateBy(SysUtils.getSysUser().getUserId()+"");
                    contract.setStatus(0);
                    this.insertContract(contract);
                    successNum++;
                    //successMsg.append("<br/>" + successNum + "、合同编号: " + contract.getContractNo() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    contract.setUpdateBy(SysUtils.getSysUser().getUserId()+"");
                    contract.setUpdateTime(new Date());
                    this.updateContract(contract);
                    successNum++;
                    //successMsg.append("<br/>" + successNum + "、合同编号: " + contract.getContractNo() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、合同编号: " + contract.getContractNo() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、合同编号: " + contract.getContractNo() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 日期格式转换
     * @param dateStr
     * @return
     */
    public String getFormatDate(String dateStr){
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            double d = Double.parseDouble(dateStr);
            Date date = org.apache.poi.ss.usermodel.DateUtil
                    .getJavaDate(Double.valueOf(d+""));
            dateStr = format.format(date);
        }catch (Exception e){
            if(dateStr.indexOf("年")!=-1){
                dateStr = dateStr.replace("年","-");
                dateStr = dateStr.replace("月","-");
                dateStr = dateStr.replace("日","");
            }
            if(dateStr.indexOf("/")!=-1){
                dateStr = dateStr.replaceAll("/","-");
            }
        }
        return dateStr;
    }
}
