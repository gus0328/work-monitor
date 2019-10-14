package com.iccm.system.service;

import com.iccm.system.model.Message;
import java.util.List;

/**
 * 消息管理Service接口
 *
 * @author gxj
 * @date 2019-09-17
 */
public interface IMessageService
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
     * 查询消息管理列表
     *
     * @param message 消息管理
     * @return 消息管理集合
     */
    public List<Message> selectMessageList1(Message message);

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
     * 批量删除消息管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMessageByIds(String ids);

    /**
     * 删除消息管理信息
     *
     * @param id 消息管理ID
     * @return 结果
     */
    public int deleteMessageById(String id);

    /**
     * 根据状态查询消息列表
     * @return
     */
    public List<Message> queryListByStatus();

    /**
     * 查询已删除的消息
     * @return
     */
    public List<Message> queryDelListByStatus();

    /**
     * 查询未读总数
     * @return
     */
    public int queryUnreadCount();

    /**
     * 查询已读总数
     * @return
     */
    public int queryReadCount();
}