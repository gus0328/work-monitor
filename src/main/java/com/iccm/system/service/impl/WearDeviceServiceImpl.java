package com.iccm.system.service.impl;

import java.util.List;
import com.iccm.common.DateUtils;
import com.iccm.common.SysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.WearDeviceMapper;
import com.iccm.system.model.WearDevice;
import com.iccm.system.service.IWearDeviceService;
import com.iccm.common.Convert;

/**
 * 穿戴设备Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Service
public class WearDeviceServiceImpl implements IWearDeviceService 
{
    @Autowired
    private WearDeviceMapper wearDeviceMapper;

    /**
     * 查询穿戴设备
     * 
     * @param id 穿戴设备ID
     * @return 穿戴设备
     */
    @Override
    public WearDevice selectWearDeviceById(Long id)
    {
        return wearDeviceMapper.selectWearDeviceById(id);
    }

    /**
     * 查询穿戴设备列表
     * 
     * @param wearDevice 穿戴设备
     * @return 穿戴设备
     */
    @Override
    public List<WearDevice> selectWearDeviceList(WearDevice wearDevice)
    {
        return wearDeviceMapper.selectWearDeviceList(wearDevice);
    }

    /**
     * 新增穿戴设备
     * 
     * @param wearDevice 穿戴设备
     * @return 结果
     */
    @Override
    public int insertWearDevice(WearDevice wearDevice)
    {
        wearDevice.setCreateBy(SysUtils.getSysUser().getUserName());
        wearDevice.setCreateTime(DateUtils.getNowDate());
        return wearDeviceMapper.insertWearDevice(wearDevice);
    }

    /**
     * 修改穿戴设备
     * 
     * @param wearDevice 穿戴设备
     * @return 结果
     */
    @Override
    public int updateWearDevice(WearDevice wearDevice)
    {
        wearDevice.setUpdateBy(SysUtils.getSysUser().getUserName());
        wearDevice.setUpdateTime(DateUtils.getNowDate());
        return wearDeviceMapper.updateWearDevice(wearDevice);
    }

    /**
     * 删除穿戴设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWearDeviceByIds(String ids)
    {
        return wearDeviceMapper.deleteWearDeviceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除穿戴设备信息
     * 
     * @param id 穿戴设备ID
     * @return 结果
     */
    public int deleteWearDeviceById(Long id)
    {
        return wearDeviceMapper.deleteWearDeviceById(id);
    }
}
