package com.ruoyi.system.mapper;

import java.util.List;

import com.mybatisflex.core.BaseMapper;
import com.ruoyi.system.domain.CustomerOrder;
import com.ruoyi.system.domain.vo.CustomerOrderVo;

/**
 * 客户-订车Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-01
 */
public interface CustomerOrderMapper extends BaseMapper<CustomerOrder>
{
    /**
     * 查询客户-订车
     * 
     * @param id 客户-订车主键
     * @return 客户-订车
     */
    public CustomerOrder selectCustomerOrderById(Long id);

    /**
     * 查询客户-订车列表
     * 
     * @param customerOrder 客户-订车
     * @return 客户-订车集合
     */
    public List<CustomerOrder> selectCustomerOrderList(CustomerOrder customerOrder);

    /**
     * 新增客户-订车
     * 
     * @param customerOrder 客户-订车
     * @return 结果
     */
    public int insertCustomerOrder(CustomerOrder customerOrder);

    /**
     * 修改客户-订车
     * 
     * @param customerOrder 客户-订车
     * @return 结果
     */
    public int updateCustomerOrder(CustomerOrder customerOrder);

    /**
     * 删除客户-订车
     * 
     * @param id 客户-订车主键
     * @return 结果
     */
    public int deleteCustomerOrderById(Long id);

    /**
     * 批量删除客户-订车
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerOrderByIds(Long[] ids);

    List<CustomerOrderVo> getCustomerOrderPage(CustomerOrderVo customerOrder);
}
