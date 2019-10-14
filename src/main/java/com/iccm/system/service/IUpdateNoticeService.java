package com.iccm.system.service;

import com.iccm.system.model.UpdateNotice;
import java.util.List;

/**
 * app更新Service接口
 * 
 * @author gxj
 * @date 2019-10-10
 */
public interface IUpdateNoticeService 
{
    /**
     * 查询app更新
     * 
     * @param id app更新ID
     * @return app更新
     */
    public UpdateNotice selectUpdateNoticeById(Long id);

    /**
     * 查询app更新列表
     * 
     * @param updateNotice app更新
     * @return app更新集合
     */
    public List<UpdateNotice> selectUpdateNoticeList(UpdateNotice updateNotice);

    /**
     * 新增app更新
     * 
     * @param updateNotice app更新
     * @return 结果
     */
    public int insertUpdateNotice(UpdateNotice updateNotice);

    /**
     * 修改app更新
     * 
     * @param updateNotice app更新
     * @return 结果
     */
    public int updateUpdateNotice(UpdateNotice updateNotice);

    /**
     * 批量删除app更新
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUpdateNoticeByIds(String ids);

    /**
     * 删除app更新信息
     * 
     * @param id app更新ID
     * @return 结果
     */
    public int deleteUpdateNoticeById(Long id);
}
