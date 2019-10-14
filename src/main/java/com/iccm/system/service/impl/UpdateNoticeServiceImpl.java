package com.iccm.system.service.impl;

import java.util.List;
import com.iccm.common.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.UpdateNoticeMapper;
import com.iccm.system.model.UpdateNotice;
import com.iccm.system.service.IUpdateNoticeService;
import com.iccm.common.Convert;

/**
 * app更新Service业务层处理
 * 
 * @author gxj
 * @date 2019-10-10
 */
@Service
public class UpdateNoticeServiceImpl implements IUpdateNoticeService 
{
    @Autowired
    private UpdateNoticeMapper updateNoticeMapper;

    /**
     * 查询app更新
     * 
     * @param id app更新ID
     * @return app更新
     */
    @Override
    public UpdateNotice selectUpdateNoticeById(Long id)
    {
        return updateNoticeMapper.selectUpdateNoticeById(id);
    }

    /**
     * 查询app更新列表
     * 
     * @param updateNotice app更新
     * @return app更新
     */
    @Override
    public List<UpdateNotice> selectUpdateNoticeList(UpdateNotice updateNotice)
    {
        return updateNoticeMapper.selectUpdateNoticeList(updateNotice);
    }

    /**
     * 新增app更新
     * 
     * @param updateNotice app更新
     * @return 结果
     */
    @Override
    public int insertUpdateNotice(UpdateNotice updateNotice)
    {
        updateNotice.setCreateTime(DateUtils.getNowDate());
        return updateNoticeMapper.insertUpdateNotice(updateNotice);
    }

    /**
     * 修改app更新
     * 
     * @param updateNotice app更新
     * @return 结果
     */
    @Override
    public int updateUpdateNotice(UpdateNotice updateNotice)
    {
        updateNotice.setUpdateTime(DateUtils.getNowDate());
        return updateNoticeMapper.updateUpdateNotice(updateNotice);
    }

    /**
     * 删除app更新对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUpdateNoticeByIds(String ids)
    {
        return updateNoticeMapper.deleteUpdateNoticeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除app更新信息
     * 
     * @param id app更新ID
     * @return 结果
     */
    public int deleteUpdateNoticeById(Long id)
    {
        return updateNoticeMapper.deleteUpdateNoticeById(id);
    }
}
