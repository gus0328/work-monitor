package com.iccm.system.service.impl;

import java.util.List;
import com.iccm.common.DateUtils;
import com.iccm.common.SysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.GasDeviceMapper;
import com.iccm.system.model.GasDevice;
import com.iccm.system.service.IGasDeviceService;
import com.iccm.common.Convert;

/**
 * 气体监测设备Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Service
public class GasDeviceServiceImpl implements IGasDeviceService 
{
    @Autowired
    private GasDeviceMapper gasDeviceMapper;

    /**
     * 查询气体监测设备
     * 
     * @param id 气体监测设备ID
     * @return 气体监测设备
     */
    @Override
    public GasDevice selectGasDeviceById(Long id)
    {
        return gasDeviceMapper.selectGasDeviceById(id);
    }

    /**
     * 查询气体监测设备列表
     * 
     * @param gasDevice 气体监测设备
     * @return 气体监测设备
     */
    @Override
    public List<GasDevice> selectGasDeviceList(GasDevice gasDevice)
    {
        return gasDeviceMapper.selectGasDeviceList(gasDevice);
    }

    /**
     * 新增气体监测设备
     * 
     * @param gasDevice 气体监测设备
     * @return 结果
     */
    @Override
    public int insertGasDevice(GasDevice gasDevice)
    {
        gasDevice.setCreateBy(SysUtils.getSysUser().getUserName());
        gasDevice.setCreateTime(DateUtils.getNowDate());
        return gasDeviceMapper.insertGasDevice(gasDevice);
    }

    /**
     * 修改气体监测设备
     * 
     * @param gasDevice 气体监测设备
     * @return 结果
     */
    @Override
    public int updateGasDevice(GasDevice gasDevice)
    {
        gasDevice.setUpdateBy(SysUtils.getSysUser().getUserName());
        gasDevice.setUpdateTime(DateUtils.getNowDate());
        return gasDeviceMapper.updateGasDevice(gasDevice);
    }

    /**
     * 删除气体监测设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGasDeviceByIds(String ids)
    {
        return gasDeviceMapper.deleteGasDeviceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除气体监测设备信息
     * 
     * @param id 气体监测设备ID
     * @return 结果
     */
    public int deleteGasDeviceById(Long id)
    {
        return gasDeviceMapper.deleteGasDeviceById(id);
    }
}
