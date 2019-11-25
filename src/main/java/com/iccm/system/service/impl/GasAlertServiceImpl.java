package com.iccm.system.service.impl;

import com.iccm.common.Convert;
import com.iccm.system.mapper.GasAlertMapper;
import com.iccm.system.model.GasAlert;
import com.iccm.system.service.IGasAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/11/24.
 */
@Service
public class GasAlertServiceImpl implements IGasAlertService{

    @Autowired
    private GasAlertMapper gasAlertMapper;

    /**
     * 查询气体报警设置
     *
     * @param id 气体报警设置ID
     * @return 气体报警设置
     */
    @Override
    public GasAlert selectGasAlertById(Long id)
    {
        return gasAlertMapper.selectGasAlertById(id);
    }

    /**
     * 查询气体报警设置列表
     *
     * @param gasAlert 气体报警设置
     * @return 气体报警设置
     */
    @Override
    public List<GasAlert> selectGasAlertList(GasAlert gasAlert)
    {
        return gasAlertMapper.selectGasAlertList(gasAlert);
    }

    /**
     * 新增气体报警设置
     *
     * @param gasAlert 气体报警设置
     * @return 结果
     */
    @Override
    public int insertGasAlert(GasAlert gasAlert)
    {
        return gasAlertMapper.insertGasAlert(gasAlert);
    }

    /**
     * 修改气体报警设置
     *
     * @param gasAlert 气体报警设置
     * @return 结果
     */
    @Override
    public int updateGasAlert(GasAlert gasAlert)
    {
        return gasAlertMapper.updateGasAlert(gasAlert);
    }

    /**
     * 删除气体报警设置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGasAlertByIds(String ids)
    {
        return gasAlertMapper.deleteGasAlertByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除气体报警设置信息
     *
     * @param id 气体报警设置ID
     * @return 结果
     */
    @Override
    public int deleteGasAlertById(Long id)
    {
        return gasAlertMapper.deleteGasAlertById(id);
    }
}
