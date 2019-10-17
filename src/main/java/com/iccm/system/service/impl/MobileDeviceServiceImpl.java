package com.iccm.system.service.impl;

import java.util.List;
import com.iccm.common.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.MobileDeviceMapper;
import com.iccm.system.model.MobileDevice;
import com.iccm.system.service.IMobileDeviceService;
import com.iccm.common.Convert;

/**
 * 移动设备管理Service业务层处理
 * 
 * @author gxj
 * @date 2019-10-14
 */
@Service
public class MobileDeviceServiceImpl implements IMobileDeviceService 
{
    @Autowired
    private MobileDeviceMapper mobileDeviceMapper;

    /**
     * 查询移动设备管理
     * 
     * @param id 移动设备管理ID
     * @return 移动设备管理
     */
    @Override
    public MobileDevice selectMobileDeviceById(Long id)
    {
        return mobileDeviceMapper.selectMobileDeviceById(id);
    }

    /**
     * 查询移动设备管理列表
     * 
     * @param mobileDevice 移动设备管理
     * @return 移动设备管理
     */
    @Override
    public List<MobileDevice> selectMobileDeviceList(MobileDevice mobileDevice)
    {
        return mobileDeviceMapper.selectMobileDeviceList(mobileDevice);
    }

    /**
     * 新增移动设备管理
     * 
     * @param mobileDevice 移动设备管理
     * @return 结果
     */
    @Override
    public int insertMobileDevice(MobileDevice mobileDevice)
    {
        mobileDevice.setCreateTime(DateUtils.getNowDate());
        return mobileDeviceMapper.insertMobileDevice(mobileDevice);
    }

    /**
     * 修改移动设备管理
     * 
     * @param mobileDevice 移动设备管理
     * @return 结果
     */
    @Override
    public int updateMobileDevice(MobileDevice mobileDevice)
    {
        mobileDevice.setUpdateTime(DateUtils.getNowDate());
        return mobileDeviceMapper.updateMobileDevice(mobileDevice);
    }

    /**
     * 删除移动设备管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMobileDeviceByIds(String ids)
    {
        return mobileDeviceMapper.deleteMobileDeviceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除移动设备管理信息
     * 
     * @param id 移动设备管理ID
     * @return 结果
     */
    public int deleteMobileDeviceById(Long id)
    {
        return mobileDeviceMapper.deleteMobileDeviceById(id);
    }
}
