package com.iccm.system.service.impl;

import java.util.List;
import com.iccm.common.DateUtils;
import com.iccm.common.SysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.MonitorDeviceMapper;
import com.iccm.system.model.MonitorDevice;
import com.iccm.system.service.IMonitorDeviceService;
import com.iccm.common.Convert;

/**
 * 监控设备Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Service
public class MonitorDeviceServiceImpl implements IMonitorDeviceService 
{
    @Autowired
    private MonitorDeviceMapper monitorDeviceMapper;

    /**
     * 查询监控设备
     * 
     * @param id 监控设备ID
     * @return 监控设备
     */
    @Override
    public MonitorDevice selectMonitorDeviceById(Long id)
    {
        return monitorDeviceMapper.selectMonitorDeviceById(id);
    }

    /**
     * 查询监控设备列表
     * 
     * @param monitorDevice 监控设备
     * @return 监控设备
     */
    @Override
    public List<MonitorDevice> selectMonitorDeviceList(MonitorDevice monitorDevice)
    {
        return monitorDeviceMapper.selectMonitorDeviceList(monitorDevice);
    }

    /**
     * 新增监控设备
     * 
     * @param monitorDevice 监控设备
     * @return 结果
     */
    @Override
    public int insertMonitorDevice(MonitorDevice monitorDevice)
    {
        monitorDevice.setCreateBy(SysUtils.getSysUser().getUserName());
        monitorDevice.setCreateTime(DateUtils.getNowDate());
        return monitorDeviceMapper.insertMonitorDevice(monitorDevice);
    }

    /**
     * 修改监控设备
     * 
     * @param monitorDevice 监控设备
     * @return 结果
     */
    @Override
    public int updateMonitorDevice(MonitorDevice monitorDevice)
    {
        monitorDevice.setUpdateBy(SysUtils.getSysUser().getUserName());
        monitorDevice.setUpdateTime(DateUtils.getNowDate());
        return monitorDeviceMapper.updateMonitorDevice(monitorDevice);
    }

    /**
     * 删除监控设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMonitorDeviceByIds(String ids)
    {
        return monitorDeviceMapper.deleteMonitorDeviceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除监控设备信息
     * 
     * @param id 监控设备ID
     * @return 结果
     */
    public int deleteMonitorDeviceById(Long id)
    {
        return monitorDeviceMapper.deleteMonitorDeviceById(id);
    }
}
