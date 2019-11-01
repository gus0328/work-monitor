package com.iccm.system.mapper;

import com.iccm.system.model.SiteGas;
import java.util.List;

/**
 * 作业气体监测Mapper接口
 * 
 * @author gxj
 * @date 2019-09-23
 */
public interface SiteGasMapper 
{
    /**
     * 查询作业气体监测
     * 
     * @param id 作业气体监测ID
     * @return 作业气体监测
     */
    public SiteGas selectSiteGasById(Long id);

    /**
     * 查询作业气体监测列表
     * 
     * @param siteGas 作业气体监测
     * @return 作业气体监测集合
     */
    public List<SiteGas> selectSiteGasList(SiteGas siteGas);

    /**
     * 新增作业气体监测
     * 
     * @param siteGas 作业气体监测
     * @return 结果
     */
    public int insertSiteGas(SiteGas siteGas);

    /**
     * 修改作业气体监测
     * 
     * @param siteGas 作业气体监测
     * @return 结果
     */
    public int updateSiteGas(SiteGas siteGas);

    /**
     * 删除作业气体监测
     * 
     * @param id 作业气体监测ID
     * @return 结果
     */
    public int deleteSiteGasById(Long id);

    /**
     * 批量删除作业气体监测
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSiteGasByIds(String[] ids);

    /**
     * 根据workId删除
     * @param workId
     * @return
     */
    public int deleteSiteGasByWorkId(String workId);

    /**
     * 根据workId查询
     * @param workId
     * @return
     */
    public List<SiteGas> getGasesByWorkId(String workId);

    /**
     * 验证设备是否在正在作业任务中
     * @param itemCode
     * @return
     */
    public int verifyDeviceIfRunning(String itemCode);
}
