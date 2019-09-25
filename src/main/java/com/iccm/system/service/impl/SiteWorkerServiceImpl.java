package com.iccm.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.SiteWorkerMapper;
import com.iccm.system.model.SiteWorker;
import com.iccm.system.service.ISiteWorkerService;
import com.iccm.common.Convert;

/**
 * 作业人员Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Service
public class SiteWorkerServiceImpl implements ISiteWorkerService 
{
    @Autowired
    private SiteWorkerMapper siteWorkerMapper;

    /**
     * 查询作业人员
     * 
     * @param id 作业人员ID
     * @return 作业人员
     */
    @Override
    public SiteWorker selectSiteWorkerById(Long id)
    {
        return siteWorkerMapper.selectSiteWorkerById(id);
    }

    /**
     * 查询作业人员列表
     * 
     * @param siteWorker 作业人员
     * @return 作业人员
     */
    @Override
    public List<SiteWorker> selectSiteWorkerList(SiteWorker siteWorker)
    {
        return siteWorkerMapper.selectSiteWorkerList(siteWorker);
    }

    /**
     * 新增作业人员
     * 
     * @param siteWorker 作业人员
     * @return 结果
     */
    @Override
    public int insertSiteWorker(SiteWorker siteWorker)
    {
        return siteWorkerMapper.insertSiteWorker(siteWorker);
    }

    /**
     * 修改作业人员
     * 
     * @param siteWorker 作业人员
     * @return 结果
     */
    @Override
    public int updateSiteWorker(SiteWorker siteWorker)
    {
        return siteWorkerMapper.updateSiteWorker(siteWorker);
    }

    /**
     * 删除作业人员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSiteWorkerByIds(String ids)
    {
        return siteWorkerMapper.deleteSiteWorkerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除作业人员信息
     * 
     * @param id 作业人员ID
     * @return 结果
     */
    public int deleteSiteWorkerById(Long id)
    {
        return siteWorkerMapper.deleteSiteWorkerById(id);
    }
}
