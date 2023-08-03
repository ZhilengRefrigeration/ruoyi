package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Customer;
import com.ruoyi.system.domain.FollowUp;
import com.ruoyi.system.domain.vo.CustomerVo;

/**
 * 客户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-06
 */
public interface CustomerMapper 
{
    /**
     * 查询客户信息
     * 
     * @param id 客户信息主键
     * @return 客户信息
     */
    public Customer selectCustomerById(Long id);

    /**
     * 查询客户信息列表
     * 
     * @param customer 客户信息
     * @return 客户信息集合
     */
    public List<CustomerVo> selectCustomerList(CustomerVo customer);

    /**
     * 新增客户信息
     * 
     * @param customer 客户信息
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改客户信息
     * 
     * @param customer 客户信息
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 删除客户信息
     * 
     * @param id 客户信息主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);

    /**
     * 批量删除客户信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(Long[] ids);

    /**
     * 批量删除跟进模块-客户跟进记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFollowUpByCustomerIds(Long[] ids);
    
    /**
     * 批量新增跟进模块-客户跟进记录
     * 
     * @param followUpList 跟进模块-客户跟进记录列表
     * @return 结果
     */
    public int batchFollowUp(List<FollowUp> followUpList);
    

    /**
     * 通过客户信息主键删除跟进模块-客户跟进记录信息
     * 
     * @param id 客户信息ID
     * @return 结果
     */
    public int deleteFollowUpByCustomerId(Long id);
    //预约记录列表
    List<CustomerVo> selectCustomerMakerList(CustomerVo customer);
}
