package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FollowUp;

/**
 * 跟进模块-客户跟进记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-07
 */
public interface FollowUpMapper 
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
     * 删除跟进模块-客户跟进记录
     * 
     * @param id 跟进模块-客户跟进记录主键
     * @return 结果
     */
    public int deleteFollowUpById(Integer id);

    /**
     * 批量删除跟进模块-客户跟进记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFollowUpByIds(Integer[] ids);
}
