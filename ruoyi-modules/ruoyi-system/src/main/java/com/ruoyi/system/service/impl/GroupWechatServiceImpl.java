package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.GroupWechat;
import com.ruoyi.system.mapper.GroupWechatMapper;
import com.ruoyi.system.service.IGroupWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Service
public class GroupWechatServiceImpl extends ServiceImpl<GroupWechatMapper, GroupWechat> implements IGroupWechatService
{
    @Autowired
    private GroupWechatMapper groupWechatMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public GroupWechat selectGroupWechatById(Long id)
    {
        return groupWechatMapper.selectGroupWechatById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param groupWechat 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<GroupWechat> selectGroupWechatList(GroupWechat groupWechat)
    {
        return groupWechatMapper.selectGroupWechatList(groupWechat);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param groupWechat 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertGroupWechat(GroupWechat groupWechat)
    {
        return groupWechatMapper.insertGroupWechat(groupWechat);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param groupWechat 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateGroupWechat(GroupWechat groupWechat)
    {
        return groupWechatMapper.updateGroupWechat(groupWechat);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGroupWechatByIds(Long[] ids)
    {
        return groupWechatMapper.deleteGroupWechatByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGroupWechatById(Long id)
    {
        return groupWechatMapper.deleteGroupWechatById(id);
    }
}
