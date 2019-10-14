package com.iccm.system.service.impl;

import java.util.List;
import com.iccm.common.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.FeedbackMapper;
import com.iccm.system.model.Feedback;
import com.iccm.system.service.IFeedbackService;
import com.iccm.common.Convert;

/**
 * 反馈意见Service业务层处理
 * 
 * @author gxj
 * @date 2019-10-10
 */
@Service
public class FeedbackServiceImpl implements IFeedbackService 
{
    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 查询反馈意见
     * 
     * @param id 反馈意见ID
     * @return 反馈意见
     */
    @Override
    public Feedback selectFeedbackById(Long id)
    {
        return feedbackMapper.selectFeedbackById(id);
    }

    /**
     * 查询反馈意见列表
     * 
     * @param feedback 反馈意见
     * @return 反馈意见
     */
    @Override
    public List<Feedback> selectFeedbackList(Feedback feedback)
    {
        return feedbackMapper.selectFeedbackList(feedback);
    }

    /**
     * 新增反馈意见
     * 
     * @param feedback 反馈意见
     * @return 结果
     */
    @Override
    public int insertFeedback(Feedback feedback)
    {
        feedback.setCreateTime(DateUtils.getNowDate());
        return feedbackMapper.insertFeedback(feedback);
    }

    /**
     * 修改反馈意见
     * 
     * @param feedback 反馈意见
     * @return 结果
     */
    @Override
    public int updateFeedback(Feedback feedback)
    {
        feedback.setUpdateTime(DateUtils.getNowDate());
        return feedbackMapper.updateFeedback(feedback);
    }

    /**
     * 删除反馈意见对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFeedbackByIds(String ids)
    {
        return feedbackMapper.deleteFeedbackByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除反馈意见信息
     * 
     * @param id 反馈意见ID
     * @return 结果
     */
    public int deleteFeedbackById(Long id)
    {
        return feedbackMapper.deleteFeedbackById(id);
    }
}
