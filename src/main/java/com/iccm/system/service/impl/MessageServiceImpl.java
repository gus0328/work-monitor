package com.iccm.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.iccm.common.SysUtils;
import com.iccm.system.mapper.UserMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iccm.system.mapper.MessageMapper;
import com.iccm.system.model.Message;
import com.iccm.system.service.IMessageService;
import com.iccm.common.Convert;

/**
 * 消息管理Service业务层处理
 *
 * @author gxj
 * @date 2019-09-17
 */
@Service
public class MessageServiceImpl implements IMessageService
{
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMessageMapper userMessageMapper;

    /**
     * 查询消息管理
     *
     * @param id 消息管理ID
     * @return 消息管理
     */
    @Override
    public Message selectMessageById(String id)
    {
        return messageMapper.selectMessageById(id);
    }

    /**
     * 查询消息管理列表
     *
     * @param message 消息管理
     * @return 消息管理
     */
    @Override
    public List<Message> selectMessageList(Message message)
    {
        return messageMapper.selectMessageList(message);
    }

    /**
     * 查询消息管理列表
     *
     * @param message 消息管理
     * @return 消息管理
     */
    @Override
    public List<Message> selectMessageList1(Message message)
    {
        return messageMapper.selectMessageList1(message);
    }

    /**
     * 新增消息管理
     *
     * @param message 消息管理
     * @return 结果
     */
    @Override
    public int insertMessage(Message message)
    {
        return messageMapper.insertMessage(message);
    }

    /**
     * 修改消息管理
     *
     * @param message 消息管理
     * @return 结果
     */
    @Override
    public int updateMessage(Message message)
    {
        return messageMapper.updateMessage(message);
    }

    /**
     * 删除消息管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMessageByIds(String ids)
    {
        return messageMapper.deleteMessageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除消息管理信息
     *
     * @param id 消息管理ID
     * @return 结果
     */
    public int deleteMessageById(String id)
    {
        return messageMapper.deleteMessageById(id);
    }

    /**
     * 根据状态查询消息列表
     * @return
     */
    public List<Message> queryListByStatus(){
        return messageMapper.queryListByStatus(SysUtils.getSysUser().getUserId());
    }

    /**
     * 查询已删除的消息
     * @return
     */
    public List<Message> queryDelListByStatus(){
        return messageMapper.queryDelListByStatus(SysUtils.getSysUser().getUserId());
    }

    @Override
    public int queryUnreadCount() {
        return messageMapper.queryUnreadCount(SysUtils.getSysUser().getUserId());
    }

    @Override
    public int queryReadCount() {
        return messageMapper.queryReadCount(SysUtils.getSysUser().getUserId());
    }
}