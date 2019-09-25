package com.iccm.system.service;

import com.iccm.system.model.GasDevice;
import java.util.List;

/**
 * 气体监测设备Service接口
 * 
 * @author gxj
 * @date 2019-09-23
 */
public interface IGasDeviceService 
{
    /**
     * 查询气体监测设备
     * 
     * @param id 气体监测设备ID
     * @return 气体监测设备
     */
    public GasDevice selectGasDeviceById(Long id);

    /**
     * 查询气体监测设备列表
     * 
     * @param gasDevice 气体监测设备
     * @return 气体监测设备集合
     */
    public List<GasDevice> selectGasDeviceList(GasDevice gasDevice);

    /**
     * 新增气体监测设备
     * 
     * @param gasDevice 气体监测设备
     * @return 结果
     */
    public int insertGasDevice(GasDevice gasDevice);

    /**
     * 修改气体监测设备
     * 
     * @param gasDevice 气体监测设备
     * @return 结果
     */
    public int updateGasDevice(GasDevice gasDevice);

    /**
     * 批量删除气体监测设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGasDeviceByIds(String ids);

    /**
     * 删除气体监测设备信息
     * 
     * @param id 气体监测设备ID
     * @return 结果
     */
    public int deleteGasDeviceById(Long id);
}
