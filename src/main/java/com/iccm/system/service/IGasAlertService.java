package com.iccm.system.service;

import com.iccm.system.model.GasAlert;

import java.util.List;

/**
 * Created by Administrator on 2019/11/24.
 */
public interface IGasAlertService {

    /**
     * 查询气体报警设置
     *
     * @param id 气体报警设置ID
     * @return 气体报警设置
     */
    public GasAlert selectGasAlertById(Long id);

    /**
     * 查询气体报警设置列表
     *
     * @param gasAlert 气体报警设置
     * @return 气体报警设置集合
     */
    public List<GasAlert> selectGasAlertList(GasAlert gasAlert);

    /**
     * 新增气体报警设置
     *
     * @param gasAlert 气体报警设置
     * @return 结果
     */
    public int insertGasAlert(GasAlert gasAlert);

    /**
     * 修改气体报警设置
     *
     * @param gasAlert 气体报警设置
     * @return 结果
     */
    public int updateGasAlert(GasAlert gasAlert);

    /**
     * 批量删除气体报警设置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGasAlertByIds(String ids);

    /**
     * 删除气体报警设置信息
     *
     * @param id 气体报警设置ID
     * @return 结果
     */
    public int deleteGasAlertById(Long id);
}
