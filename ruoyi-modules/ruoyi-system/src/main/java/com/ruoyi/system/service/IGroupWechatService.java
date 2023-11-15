package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.GroupWechat;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface IGroupWechatService  extends IService<GroupWechat>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public GroupWechat selectGroupWechatById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param groupWechat 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<GroupWechat> selectGroupWechatList(GroupWechat groupWechat);

    /**
     * 新增【请填写功能名称】
     * 
     * @param groupWechat 【请填写功能名称】
     * @return 结果
     */
    public int insertGroupWechat(GroupWechat groupWechat);

    /**
     * 修改【请填写功能名称】
     * 
     * @param groupWechat 【请填写功能名称】
     * @return 结果
     */
    public int updateGroupWechat(GroupWechat groupWechat);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteGroupWechatByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteGroupWechatById(Long id);
}
