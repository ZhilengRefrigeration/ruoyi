package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.system.domain.Customer;
import com.ruoyi.system.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FollowUpMapper;
import com.ruoyi.system.domain.FollowUp;
import com.ruoyi.system.service.IFollowUpService;

/**
 * 跟进模块-客户跟进记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-07
 */
@Service
public class FollowUpServiceImpl implements IFollowUpService 
{
    @Autowired
    private FollowUpMapper followUpMapper;
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询跟进模块-客户跟进记录
     * 
     * @param id 跟进模块-客户跟进记录主键
     * @return 跟进模块-客户跟进记录
     */
    @Override
    public FollowUp selectFollowUpById(Integer id)
    {
        return followUpMapper.selectFollowUpById(id);
    }

    /**
     * 查询跟进模块-客户跟进记录列表
     * 
     * @param followUp 跟进模块-客户跟进记录
     * @return 跟进模块-客户跟进记录
     */
    @Override
    public List<FollowUp> selectFollowUpList(FollowUp followUp)
    {
        return followUpMapper.selectFollowUpList(followUp);
    }

    /**
     * 新增跟进模块-客户跟进记录
     * 
     * @param followUp 跟进模块-客户跟进记录
     * @return 结果
     */
    @Override
    public int insertFollowUp(FollowUp followUp)
    {
        followUp.setCreateTime(DateUtils.getNowDate());
        Customer customer = new Customer();
        customer.setId(followUp.getCustomerId());
        //如果跟进记录的跟进级别选择
        if("战败".equals(followUp.getFollowLevel())){
            customer.setStatus("defeat");
            customerMapper.updateCustomer(customer);
        }else if("订车".equals(followUp.getFollowLevel())||"成交".equals(followUp.getFollowLevel())){
            customer.setStatus("order");
            customerMapper.updateCustomer(customer);
        }
        return followUpMapper.insertFollowUp(followUp);
    }

    /**
     * 修改跟进模块-客户跟进记录
     * 
     * @param followUp 跟进模块-客户跟进记录
     * @return 结果
     */
    @Override
    public int updateFollowUp(FollowUp followUp)
    {
        followUp.setUpdateTime(DateUtils.getNowDate());
        return followUpMapper.updateFollowUp(followUp);
    }

    /**
     * 批量删除跟进模块-客户跟进记录
     * 
     * @param ids 需要删除的跟进模块-客户跟进记录主键
     * @return 结果
     */
    @Override
    public int deleteFollowUpByIds(Integer[] ids)
    {
        return followUpMapper.deleteFollowUpByIds(ids);
    }

    /**
     * 删除跟进模块-客户跟进记录信息
     * 
     * @param id 跟进模块-客户跟进记录主键
     * @return 结果
     */
    @Override
    public int deleteFollowUpById(Integer id)
    {
        return followUpMapper.deleteFollowUpById(id);
    }
}
