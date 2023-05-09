package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FollowUp;

/**
 * 跟进模块-客户跟进记录Service接口
 * 
 * @author ruoyi
 * @date 2023-05-07
 */
public interface IFollowUpService 
{
    /**
     * 查询跟进模块-客户跟进记录
     * 
     * @param id 跟进模块-客户跟进记录主键
     * @return 跟进模块-客户跟进记录
     */
    public FollowUp selectFollowUpById(Integer id);

    /**
     * 查询跟进模块-客户跟进记录列表
     * 
     * @param followUp 跟进模块-客户跟进记录
     * @return 跟进模块-客户跟进记录集合
     */
    public List<FollowUp> selectFollowUpList(FollowUp followUp);

    /**
     * 新增跟进模块-客户跟进记录
     * 
     * @param followUp 跟进模块-客户跟进记录
     * @return 结果
     */
    public int insertFollowUp(FollowUp followUp);

    /**
     * 修改跟进模块-客户跟进记录
     * 
     * @param followUp 跟进模块-客户跟进记录
     * @return 结果
     */
    public int updateFollowUp(FollowUp followUp);

    /**
     * 批量删除跟进模块-客户跟进记录
     * 
     * @param ids 需要删除的跟进模块-客户跟进记录主键集合
     * @return 结果
     */
    public int deleteFollowUpByIds(Integer[] ids);

    /**
     * 删除跟进模块-客户跟进记录信息
     * 
     * @param id 跟进模块-客户跟进记录主键
     * @return 结果
     */
    public int deleteFollowUpById(Integer id);
}
