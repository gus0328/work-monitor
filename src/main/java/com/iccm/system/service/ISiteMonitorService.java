package com.iccm.system.service;

import com.iccm.system.model.SiteMonitor;
import java.util.List;

/**
 * 作业监控设备Service接口
 * 
 * @author gxj
 * @date 2019-09-23
 */
public interface ISiteMonitorService 
{
    /**
     * 查询作业监控设备
     * 
     * @param id 作业监控设备ID
     * @return 作业监控设备
     */
    public SiteMonitor selectSiteMonitorById(Long id);

    /**
     * 查询作业监控设备列表
     * 
     * @param siteMonitor 作业监控设备
     * @return 作业监控设备集合
     */
    public List<SiteMonitor> selectSiteMonitorList(SiteMonitor siteMonitor);

    /**
     * 新增作业监控设备
     * 
     * @param siteMonitor 作业监控设备
     * @return 结果
     */
    public int insertSiteMonitor(SiteMonitor siteMonitor);

    /**
     * 修改作业监控设备
     * 
     * @param siteMonitor 作业监控设备
     * @return 结果
     */
    public int updateSiteMonitor(SiteMonitor siteMonitor);

    /**
     * 批量删除作业监控设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSiteMonitorByIds(String ids);

    /**
     * 删除作业监控设备信息
     * 
     * @param id 作业监控设备ID
     * @return 结果
     */
    public int deleteSiteMonitorById(Long id);
}
