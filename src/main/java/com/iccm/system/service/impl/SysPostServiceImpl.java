package com.iccm.system.service.impl;

import java.util.List;
import com.iccm.common.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.SysPostMapper;
import com.iccm.system.model.SysPost;
import com.iccm.system.service.ISysPostService;
import com.iccm.common.Convert;

/**
 * 岗位信息Service业务层处理
 * 
 * @author gxj
 * @date 2019-09-11
 */
@Service
public class SysPostServiceImpl implements ISysPostService 
{
    @Autowired
    private SysPostMapper sysPostMapper;

    /**
     * 查询岗位信息
     * 
     * @param postId 岗位信息ID
     * @return 岗位信息
     */
    @Override
    public SysPost selectSysPostById(Long postId)
    {
        return sysPostMapper.selectSysPostById(postId);
    }

    /**
     * 查询岗位信息列表
     * 
     * @param sysPost 岗位信息
     * @return 岗位信息
     */
    @Override
    public List<SysPost> selectSysPostList(SysPost sysPost)
    {
        return sysPostMapper.selectSysPostList(sysPost);
    }

    /**
     * 新增岗位信息
     * 
     * @param sysPost 岗位信息
     * @return 结果
     */
    @Override
    public int insertSysPost(SysPost sysPost)
    {
        sysPost.setCreateTime(DateUtils.getNowDate());
        return sysPostMapper.insertSysPost(sysPost);
    }

    /**
     * 修改岗位信息
     * 
     * @param sysPost 岗位信息
     * @return 结果
     */
    @Override
    public int updateSysPost(SysPost sysPost)
    {
        sysPost.setUpdateTime(DateUtils.getNowDate());
        return sysPostMapper.updateSysPost(sysPost);
    }

    /**
     * 删除岗位信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysPostByIds(String ids)
    {
        return sysPostMapper.deleteSysPostByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除岗位信息信息
     * 
     * @param postId 岗位信息ID
     * @return 结果
     */
    public int deleteSysPostById(Long postId)
    {
        return sysPostMapper.deleteSysPostById(postId);
    }
}
