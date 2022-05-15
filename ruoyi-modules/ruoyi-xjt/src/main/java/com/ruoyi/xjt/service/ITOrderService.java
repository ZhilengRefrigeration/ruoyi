package com.ruoyi.xjt.service;

import java.util.List;
import com.ruoyi.xjt.domain.TOrder;

/**
 * 缴费订单Service接口
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
public interface ITOrderService 
{
    /**
     * 查询缴费订单
     * 
     * @param id 缴费订单主键
     * @return 缴费订单
     */
    public TOrder selectTOrderById(Long id);

    /**
     * 查询缴费订单列表
     * 
     * @param tOrder 缴费订单
     * @return 缴费订单集合
     */
    public List<TOrder> selectTOrderList(TOrder tOrder);

    /**
     * 新增缴费订单
     * 
     * @param tOrder 缴费订单
     * @return 结果
     */
    public int insertTOrder(TOrder tOrder);

    /**
     * 修改缴费订单
     * 
     * @param tOrder 缴费订单
     * @return 结果
     */
    public int updateTOrder(TOrder tOrder);

    /**
     * 批量删除缴费订单
     * 
     * @param ids 需要删除的缴费订单主键集合
     * @return 结果
     */
    public int deleteTOrderByIds(Long[] ids);

    /**
     * 删除缴费订单信息
     * 
     * @param id 缴费订单主键
     * @return 结果
     */
    public int deleteTOrderById(Long id);
}
