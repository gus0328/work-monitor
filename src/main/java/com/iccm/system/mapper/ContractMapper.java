package com.iccm.system.mapper;

import com.iccm.system.model.Contract;
import java.util.List;

/**
 * 合同超期提醒Mapper接口
 * 
 * @author gxj
 * @date 2019-09-17
 */
public interface ContractMapper 
{
    /**
     * 查询合同超期提醒
     * 
     * @param id 合同超期提醒ID
     * @return 合同超期提醒
     */
    public Contract selectContractById(Integer id);

    /**
     * 查询合同超期提醒列表
     * 
     * @param contract 合同超期提醒
     * @return 合同超期提醒集合
     */
    public List<Contract> selectContractList(Contract contract);

    /**
     * 新增合同超期提醒
     * 
     * @param contract 合同超期提醒
     * @return 结果
     */
    public int insertContract(Contract contract);

    /**
     * 修改合同超期提醒
     * 
     * @param contract 合同超期提醒
     * @return 结果
     */
    public int updateContract(Contract contract);

    /**
     * 删除合同超期提醒
     * 
     * @param id 合同超期提醒ID
     * @return 结果
     */
    public int deleteContractById(Integer id);

    /**
     * 批量删除合同超期提醒
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteContractByIds(String[] ids);

    /**
     * 查询超期合同
     * @return
     */
    public List<Contract> selectExpiryContract();

    /**
     * 根据合同编号查询
     * @param contractNo
     * @return
     */
    public Contract selectByContractNo(String contractNo);
}
