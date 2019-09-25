package com.iccm.system.service;

import com.alibaba.fastjson.JSONObject;
import com.iccm.common.JsonResult;
import com.iccm.system.model.*;

import java.util.List;

/**
 * 现场作业Service接口
 * 
 * @author gxj
 * @date 2019-09-23
 */
public interface ISiteWorkService 
{
    /**
     * 查询现场作业
     * 
     * @param id 现场作业ID
     * @return 现场作业
     */
    public SiteWork selectSiteWorkById(String id);

    /**
     * 查询现场作业列表
     * 
     * @param siteWork 现场作业
     * @return 现场作业集合
     */
    public List<SiteWork> selectSiteWorkList(SiteWork siteWork);

    /**
     * 新增现场作业
     * 
     * @param siteWorkVo 现场作业
     * @return 结果
     */
    public int insertSiteWork(SiteWorkVo siteWorkVo);

    /**
     * 修改现场作业
     * 
     * @param siteWorkVo 现场作业
     * @return 结果
     */
    public int updateSiteWork(SiteWorkVo siteWorkVo);

    /**
     * 批量删除现场作业
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSiteWorkByIds(String ids);

    /**
     * 删除现场作业信息
     * 
     * @param id 现场作业ID
     * @return 结果
     */
    public JsonResult deleteSiteWorkById(String id);

    /**
     * 根据id查询作业详情
     * @param id
     * @return
     */
    public SiteWorkVo getWorkDetailsByWorkId(String id);

    /**
     * 穿戴设备下拉
     * @return
     */
    public List<SiteWorkerVo.WearDevice> getWearDeviceSelectList(WearDevice wearDevice);

    /**
     * 气体监测设备下拉
     * @return
     */
    public List<SiteGas> getGasDeviceSelectList(GasDevice gasDevice);

    /**
     * 监控设备下拉
     * @return
     */
    public List<SiteMonitor> getMonitorDeviceSelectList(MonitorDevice monitorDevice);
}
