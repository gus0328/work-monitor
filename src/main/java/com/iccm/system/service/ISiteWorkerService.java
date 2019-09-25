package com.iccm.system.service;

import com.iccm.system.model.SiteWorker;
import java.util.List;

/**
 * 作业人员Service接口
 * 
 * @author gxj
 * @date 2019-09-23
 */
public interface ISiteWorkerService 
{
    /**
     * 查询作业人员
     * 
     * @param id 作业人员ID
     * @return 作业人员
     */
    public SiteWorker selectSiteWorkerById(Long id);

    /**
     * 查询作业人员列表
     * 
     * @param siteWorker 作业人员
     * @return 作业人员集合
     */
    public List<SiteWorker> selectSiteWorkerList(SiteWorker siteWorker);

    /**
     * 新增作业人员
     * 
     * @param siteWorker 作业人员
     * @return 结果
     */
    public int insertSiteWorker(SiteWorker siteWorker);

    /**
     * 修改作业人员
     * 
     * @param siteWorker 作业人员
     * @return 结果
     */
    public int updateSiteWorker(SiteWorker siteWorker);

    /**
     * 批量删除作业人员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSiteWorkerByIds(String ids);

    /**
     * 删除作业人员信息
     * 
     * @param id 作业人员ID
     * @return 结果
     */
    public int deleteSiteWorkerById(Long id);

}
