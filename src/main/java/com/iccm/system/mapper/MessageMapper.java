package com.iccm.system.mapper;

import com.iccm.system.model.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消息管理Mapper接口
 *
 * @author gxj
 * @date 2019-09-17
 */
public interface MessageMapper
{
    /**
     * 查询消息管理
     *
     * @param id 消息管理ID
     * @return 消息管理
     */
    public Message selectMessageById(String id);

    /**
     * 查询消息管理列表
     *
     * @param message 消息管理
     * @return 消息管理集合
     */
    public List<Message> selectMessageList(Message message);

    /**
     * 新增消息管理
     *
     * @param message 消息管理
     * @return 结果
     */
    public int insertMessage(Message message);

    /**
     * 修改消息管理
     *
     * @param message 消息管理
     * @return 结果
     */
    public int updateMessage(Message message);

    /**
     * 删除消息管理
     *
     * @param id 消息管理ID
     * @return 结果
     */
    public int deleteMessageById(String id);

    /**
     * 批量删除消息管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMessageByIds(String[] ids);

    /**
     * 查询已收到的消息
     * @param
     * @return
     */
    public List<Message> queryListByStatus(@Param("userId") Long userId);

    /**
     * 查询已删除的消息
     * @param userId
     * @return
     */
    public List<Message> queryDelListByStatus(@Param("userId") Long userId);

    /**
     * 查询未读总数
     * @param userId
     * @return
     */
    public int queryUnreadCount(@Param("userId") Long userId);

    /**
     * 查询已读总数
     * @param userId
     * @return
     */
    public int queryReadCount(@Param("userId") Long userId);
}