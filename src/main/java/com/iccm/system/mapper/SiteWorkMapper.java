package com.iccm.system.mapper;

import com.iccm.system.model.SiteWork;
import java.util.List;

/**
 * 现场作业Mapper接口
 * 
 * @author gxj
 * @date 2019-09-23
 */
public interface SiteWorkMapper 
{
    /**
     * 查询现场作业
     * 
     * @param id 现场作业ID
     * @return 现场作业
     */
    public SiteWork selectSiteWorkById(String id);

    /**
     * 查询现场作业列表
     * 
     * @param siteWork 现场作业
     * @return 现场作业集合
     */
    public List<SiteWork> selectSiteWorkList(SiteWork siteWork);

    /**
     * 新增现场作业
     * 
     * @param siteWork 现场作业
     * @return 结果
     */
    public int insertSiteWork(SiteWork siteWork);

    /**
     * 修改现场作业
     * 
     * @param siteWork 现场作业
     * @return 结果
     */
    public int updateSiteWork(SiteWork siteWork);

    /**
     * 删除现场作业
     * 
     * @param id 现场作业ID
     * @return 结果
     */
    public int deleteSiteWorkById(String id);

    /**
     * 批量删除现场作业
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSiteWorkByIds(String[] ids);
}
