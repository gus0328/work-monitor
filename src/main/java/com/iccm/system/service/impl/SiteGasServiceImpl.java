package com.iccm.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.SiteGasMapper;
import com.iccm.system.model.SiteGas;
import com.iccm.system.service.ISiteGasService;
import com.iccm.common.Convert;

/**
 * 作业气体监测Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Service
public class SiteGasServiceImpl implements ISiteGasService 
{
    @Autowired
    private SiteGasMapper siteGasMapper;

    /**
     * 查询作业气体监测
     * 
     * @param id 作业气体监测ID
     * @return 作业气体监测
     */
    @Override
    public SiteGas selectSiteGasById(Long id)
    {
        return siteGasMapper.selectSiteGasById(id);
    }

    /**
     * 查询作业气体监测列表
     * 
     * @param siteGas 作业气体监测
     * @return 作业气体监测
     */
    @Override
    public List<SiteGas> selectSiteGasList(SiteGas siteGas)
    {
        return siteGasMapper.selectSiteGasList(siteGas);
    }

    /**
     * 新增作业气体监测
     * 
     * @param siteGas 作业气体监测
     * @return 结果
     */
    @Override
    public int insertSiteGas(SiteGas siteGas)
    {
        return siteGasMapper.insertSiteGas(siteGas);
    }

    /**
     * 修改作业气体监测
     * 
     * @param siteGas 作业气体监测
     * @return 结果
     */
    @Override
    public int updateSiteGas(SiteGas siteGas)
    {
        return siteGasMapper.updateSiteGas(siteGas);
    }

    /**
     * 删除作业气体监测对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSiteGasByIds(String ids)
    {
        return siteGasMapper.deleteSiteGasByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除作业气体监测信息
     * 
     * @param id 作业气体监测ID
     * @return 结果
     */
    public int deleteSiteGasById(Long id)
    {
        return siteGasMapper.deleteSiteGasById(id);
    }
}
