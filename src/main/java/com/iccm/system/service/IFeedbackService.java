package com.iccm.system.service;

import com.iccm.system.model.Feedback;
import java.util.List;

/**
 * 反馈意见Service接口
 * 
 * @author gxj
 * @date 2019-10-10
 */
public interface IFeedbackService 
{
    /**
     * 查询反馈意见
     * 
     * @param id 反馈意见ID
     * @return 反馈意见
     */
    public Feedback selectFeedbackById(Long id);

    /**
     * 查询反馈意见列表
     * 
     * @param feedback 反馈意见
     * @return 反馈意见集合
     */
    public List<Feedback> selectFeedbackList(Feedback feedback);

    /**
     * 新增反馈意见
     * 
     * @param feedback 反馈意见
     * @return 结果
     */
    public int insertFeedback(Feedback feedback);

    /**
     * 修改反馈意见
     * 
     * @param feedback 反馈意见
     * @return 结果
     */
    public int updateFeedback(Feedback feedback);

    /**
     * 批量删除反馈意见
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFeedbackByIds(String ids);

    /**
     * 删除反馈意见信息
     * 
     * @param id 反馈意见ID
     * @return 结果
     */
    public int deleteFeedbackById(Long id);
}
