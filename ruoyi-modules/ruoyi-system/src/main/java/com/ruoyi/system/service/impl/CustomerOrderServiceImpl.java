package com.ruoyi.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.system.domain.vo.CustomerOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CustomerOrderMapper;
import com.ruoyi.system.domain.CustomerOrder;
import com.ruoyi.system.service.ICustomerOrderService;

/**
 * 客户-订车Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-01
 */
@Service
public class CustomerOrderServiceImpl extends ServiceImpl<CustomerOrderMapper, CustomerOrder> implements ICustomerOrderService
{
    @Autowired
    private CustomerOrderMapper customerOrderMapper;

    /**
     * 查询客户-订车
     * 
     * @param id 客户-订车主键
     * @return 客户-订车
     */
    @Override
    public CustomerOrder selectCustomerOrderById(Long id)
    {
        return customerOrderMapper.selectCustomerOrderById(id);
    }

    /**
     * 查询客户-订车列表
     * 
     * @param customerOrder 客户-订车
     * @return 客户-订车
     */
    @Override
    public List<CustomerOrder> selectCustomerOrderList(CustomerOrder customerOrder)
    {
        return customerOrderMapper.selectCustomerOrderList(customerOrder);
    }

    /**
     * 新增客户-订车
     * 
     * @param customerOrder 客户-订车
     * @return 结果
     */
    @Override
    public int insertCustomerOrder(CustomerOrder customerOrder)
    {
        customerOrder.setCreateTime(DateUtils.getNowDate());
        return customerOrderMapper.insertCustomerOrder(customerOrder);
    }

    /**
     * 修改客户-订车
     * 
     * @param customerOrder 客户-订车
     * @return 结果
     */
    @Override
    public int updateCustomerOrder(CustomerOrder customerOrder)
    {
        customerOrder.setUpdateTime(DateUtils.getNowDate());
        return customerOrderMapper.updateCustomerOrder(customerOrder);
    }

    /**
     * 批量删除客户-订车
     * 
     * @param ids 需要删除的客户-订车主键
     * @return 结果
     */
    @Override
    public int deleteCustomerOrderByIds(Long[] ids)
    {
        return customerOrderMapper.deleteCustomerOrderByIds(ids);
    }

    /**
     * 删除客户-订车信息
     * 
     * @param id 客户-订车主键
     * @return 结果
     */
    @Override
    public int deleteCustomerOrderById(Long id)
    {
        return customerOrderMapper.deleteCustomerOrderById(id);
    }

    @Override
    public List<CustomerOrderVo> getCustomerOrderPage(CustomerOrderVo customerOrder) {
        return customerOrderMapper.getCustomerOrderPage(customerOrder);
    }
}
