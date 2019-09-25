package com.iccm.system.service.impl;

import java.util.List;
import com.iccm.common.DateUtils;
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
}
