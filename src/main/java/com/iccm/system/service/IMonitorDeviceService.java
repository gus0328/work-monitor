package com.iccm.system.service;

import com.iccm.system.model.MonitorDevice;
import java.util.List;

/**
 * 监控设备Service接口
 * 
 * @author gxj
 * @date 2019-09-23
 */
public interface IMonitorDeviceService 
{
    /**
     * 查询监控设备
     * 
     * @param id 监控设备ID
     * @return 监控设备
     */
    public MonitorDevice selectMonitorDeviceById(Long id);

    /**
     * 查询监控设备列表
     * 
     * @param monitorDevice 监控设备
     * @return 监控设备集合
     */
    public List<MonitorDevice> selectMonitorDeviceList(MonitorDevice monitorDevice);

    /**
     * 新增监控设备
     * 
     * @param monitorDevice 监控设备
     * @return 结果
     */
    public int insertMonitorDevice(MonitorDevice monitorDevice);

    /**
     * 修改监控设备
     * 
     * @param monitorDevice 监控设备
     * @return 结果
     */
    public int updateMonitorDevice(MonitorDevice monitorDevice);

    /**
     * 批量删除监控设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMonitorDeviceByIds(String ids);

    /**
     * 删除监控设备信息
     * 
     * @param id 监控设备ID
     * @return 结果
     */
    public int deleteMonitorDeviceById(Long id);
}
