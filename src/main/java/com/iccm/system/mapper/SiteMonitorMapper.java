package com.iccm.system.mapper;

import com.iccm.system.model.SiteMonitor;
import java.util.List;

/**
 * 作业监控设备Mapper接口
 * 
 * @author gxj
 * @date 2019-09-23
 */
public interface SiteMonitorMapper 
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
     * 删除作业监控设备
     * 
     * @param id 作业监控设备ID
     * @return 结果
     */
    public int deleteSiteMonitorById(Long id);

    /**
     * 批量删除作业监控设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSiteMonitorByIds(String[] ids);

    /**
     * 根据workId删除
     * @param workId
     * @return
     */
    public int deleteSiteMonitorByWorkId(String workId);

    /**
     * 根据workId查询
     * @param workId
     * @return
     */
    public List<SiteMonitor> getMonitorsByWorkId(String workId);

    /**
     * 验证设备是否在正在作业任务中
     * @param ipAdress
     * @return
     */
    public int verifyDeviceIfRunning(String ipAdress);
}
