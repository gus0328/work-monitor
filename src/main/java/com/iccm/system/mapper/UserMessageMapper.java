package com.iccm.system.mapper;

import com.iccm.system.model.UserMessage;
import java.util.List;

/**
 * 用户消息Mapper接口
 *
 * @author gxj
 * @date 2019-09-17
 */
public interface UserMessageMapper
{
    /**
     * 查询用户消息
     *
     * @param messageId 用户消息ID
     * @return 用户消息
     */
    public UserMessage selectUserMessageById(String messageId);

    /**
     * 查询用户消息列表
     *
     * @param userMessage 用户消息
     * @return 用户消息集合
     */
    public List<UserMessage> selectUserMessageList(UserMessage userMessage);

    /**
     * 新增用户消息
     *
     * @param userMessage 用户消息
     * @return 结果
     */
    public int insertUserMessage(UserMessage userMessage);

    /**
     * 修改用户消息
     *
     * @param userMessage 用户消息
     * @return 结果
     */
    public int updateUserMessage(UserMessage userMessage);

    /**
     * 删除用户消息
     *
     * @param messageId 用户消息ID
     * @return 结果
     */
    public int deleteUserMessageById(String messageId);

    /**
     * 批量删除用户消息
     *
     * @param messageIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserMessageByIds(String[] messageIds);
}