package com.iccm.system.service;

import com.iccm.system.model.MobileDevice;
import java.util.List;

/**
 * 移动设备管理Service接口
 * 
 * @author gxj
 * @date 2019-10-14
 */
public interface IMobileDeviceService 
{
    /**
     * 查询移动设备管理
     * 
     * @param id 移动设备管理ID
     * @return 移动设备管理
     */
    public MobileDevice selectMobileDeviceById(Long id);

    /**
     * 查询移动设备管理列表
     * 
     * @param mobileDevice 移动设备管理
     * @return 移动设备管理集合
     */
    public List<MobileDevice> selectMobileDeviceList(MobileDevice mobileDevice);

    /**
     * 新增移动设备管理
     * 
     * @param mobileDevice 移动设备管理
     * @return 结果
     */
    public int insertMobileDevice(MobileDevice mobileDevice);

    /**
     * 修改移动设备管理
     * 
     * @param mobileDevice 移动设备管理
     * @return 结果
     */
    public int updateMobileDevice(MobileDevice mobileDevice);

    /**
     * 批量删除移动设备管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMobileDeviceByIds(String ids);

    /**
     * 删除移动设备管理信息
     * 
     * @param id 移动设备管理ID
     * @return 结果
     */
    public int deleteMobileDeviceById(Long id);
}
