package com.iccm.system.mapper;

import com.iccm.system.model.SiteWorkerVo;
import com.iccm.system.model.WearDevice;
import java.util.List;

/**
 * 穿戴设备Mapper接口
 * 
 * @author gxj
 * @date 2019-09-23
 */
public interface WearDeviceMapper 
{
    /**
     * 查询穿戴设备
     * 
     * @param id 穿戴设备ID
     * @return 穿戴设备
     */
    public WearDevice selectWearDeviceById(Long id);

    /**
     * 查询穿戴设备列表
     * 
     * @param wearDevice 穿戴设备
     * @return 穿戴设备集合
     */
    public List<WearDevice> selectWearDeviceList(WearDevice wearDevice);

    /**
     * 新增穿戴设备
     * 
     * @param wearDevice 穿戴设备
     * @return 结果
     */
    public int insertWearDevice(WearDevice wearDevice);

    /**
     * 修改穿戴设备
     * 
     * @param wearDevice 穿戴设备
     * @return 结果
     */
    public int updateWearDevice(WearDevice wearDevice);

    /**
     * 删除穿戴设备
     * 
     * @param id 穿戴设备ID
     * @return 结果
     */
    public int deleteWearDeviceById(Long id);

    /**
     * 批量删除穿戴设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWearDeviceByIds(String[] ids);

    /**
     * 设备下拉数据
     * @return
     */
    public List<SiteWorkerVo.WearDevice> getSelectList(WearDevice wearDevice);

    /**
     * 查询正在作业的穿戴设备
     * @return
     */
    public List<String> getRunningItemId();
}
