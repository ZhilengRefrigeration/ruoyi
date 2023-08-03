package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.system.domain.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.core.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.FollowUp;
import com.ruoyi.system.mapper.CustomerMapper;
import com.ruoyi.system.domain.Customer;
import com.ruoyi.system.service.ICustomerService;

/**
 * 客户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-06
 */
@Service
public class CustomerServiceImpl implements ICustomerService 
{
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询客户信息
     * 
     * @param id 客户信息主键
     * @return 客户信息
     */
    @Override
    public Customer selectCustomerById(Long id)
    {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询客户信息列表
     * 
     * @param customer 客户信息
     * @return 客户信息
     */
    @Override
    public List<CustomerVo> selectCustomerList(CustomerVo customer)
    {
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增客户信息
     * 
     * @param customer 客户信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCustomer(Customer customer)
    {
        customer.setCreateTime(DateUtils.getNowDate());
        int rows = customerMapper.insertCustomer(customer);
        insertFollowUp(customer);
        return rows;
    }

    /**
     * 修改客户信息
     * 
     * @param customer 客户信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCustomer(Customer customer)
    {
        customer.setUpdateTime(DateUtils.getNowDate());
     /*   customerMapper.deleteFollowUpByCustomerId(customer.getId());
        insertFollowUp(customer);*/
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除客户信息
     * 
     * @param ids 需要删除的客户信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCustomerByIds(Long[] ids)
    {
        customerMapper.deleteFollowUpByCustomerIds(ids);
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除客户信息信息
     * 
     * @param id 客户信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCustomerById(Long id)
    {
        customerMapper.deleteFollowUpByCustomerId(id);
        return customerMapper.deleteCustomerById(id);
    }

    @Override
    public List<CustomerVo> selectCustomerMakerList(CustomerVo customer) {
        return customerMapper.selectCustomerMakerList(customer);
    }

    /**
     * 新增跟进模块-客户跟进记录信息
     * 
     * @param customer 客户信息对象
     */
    public void insertFollowUp(Customer customer)
    {
        List<FollowUp> followUpList = customer.getFollowUpList();
        Long id = customer.getId();
        if (StringUtils.isNotNull(followUpList))
        {
            List<FollowUp> list = new ArrayList<FollowUp>();
            for (FollowUp followUp : followUpList)
            {
                followUp.setCustomerId(id);
                list.add(followUp);
            }
            if (list.size() > 0)
            {
                customerMapper.batchFollowUp(list);
            }
        }
    }
}
