package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.Message;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface IMessageService extends IService<Message>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Message selectMessageById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param message 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Message> selectMessageList(Message message);

    /**
     * 新增【请填写功能名称】
     * 
     * @param message 【请填写功能名称】
     * @return 结果
     */
    public int insertMessage(Message message);

    /**
     * 修改【请填写功能名称】
     * 
     * @param message 【请填写功能名称】
     * @return 结果
     */
    public int updateMessage(Message message);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteMessageByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMessageById(Long id);
}