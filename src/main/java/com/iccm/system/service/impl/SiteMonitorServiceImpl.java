package com.iccm.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.SiteMonitorMapper;
import com.iccm.system.model.SiteMonitor;
import com.iccm.system.service.ISiteMonitorService;
import com.iccm.common.Convert;

/**
 * 作业监控设备Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Service
public class SiteMonitorServiceImpl implements ISiteMonitorService 
{
    @Autowired
    private SiteMonitorMapper siteMonitorMapper;

    /**
     * 查询作业监控设备
     * 
     * @param id 作业监控设备ID
     * @return 作业监控设备
     */
    @Override
    public SiteMonitor selectSiteMonitorById(Long id)
    {
        return siteMonitorMapper.selectSiteMonitorById(id);
    }

    /**
     * 查询作业监控设备列表
     * 
     * @param siteMonitor 作业监控设备
     * @return 作业监控设备
     */
    @Override
    public List<SiteMonitor> selectSiteMonitorList(SiteMonitor siteMonitor)
    {
        return siteMonitorMapper.selectSiteMonitorList(siteMonitor);
    }

    /**
     * 新增作业监控设备
     * 
     * @param siteMonitor 作业监控设备
     * @return 结果
     */
    @Override
    public int insertSiteMonitor(SiteMonitor siteMonitor)
    {
        return siteMonitorMapper.insertSiteMonitor(siteMonitor);
    }

    /**
     * 修改作业监控设备
     * 
     * @param siteMonitor 作业监控设备
     * @return 结果
     */
    @Override
    public int updateSiteMonitor(SiteMonitor siteMonitor)
    {
        return siteMonitorMapper.updateSiteMonitor(siteMonitor);
    }

    /**
     * 删除作业监控设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSiteMonitorByIds(String ids)
    {
        return siteMonitorMapper.deleteSiteMonitorByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除作业监控设备信息
     * 
     * @param id 作业监控设备ID
     * @return 结果
     */
    public int deleteSiteMonitorById(Long id)
    {
        return siteMonitorMapper.deleteSiteMonitorById(id);
    }
}
