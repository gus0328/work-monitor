package com.iccm.system.mapper;

import com.iccm.system.model.SysPost;
import java.util.List;

/**
 * 岗位信息Mapper接口
 * 
 * @author gxj
 * @date 2019-09-11
 */
public interface SysPostMapper 
{
    /**
     * 查询岗位信息
     * 
     * @param postId 岗位信息ID
     * @return 岗位信息
     */
    public SysPost selectSysPostById(Long postId);

    /**
     * 查询岗位信息列表
     * 
     * @param sysPost 岗位信息
     * @return 岗位信息集合
     */
    public List<SysPost> selectSysPostList(SysPost sysPost);

    /**
     * 新增岗位信息
     * 
     * @param sysPost 岗位信息
     * @return 结果
     */
    public int insertSysPost(SysPost sysPost);

    /**
     * 修改岗位信息
     * 
     * @param sysPost 岗位信息
     * @return 结果
     */
    public int updateSysPost(SysPost sysPost);

    /**
     * 删除岗位信息
     * 
     * @param postId 岗位信息ID
     * @return 结果
     */
    public int deleteSysPostById(Long postId);

    /**
     * 批量删除岗位信息
     * 
     * @param postIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysPostByIds(String[] postIds);

    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
    public List<SysPost> selectPostsByUserId(Long userId);
}
