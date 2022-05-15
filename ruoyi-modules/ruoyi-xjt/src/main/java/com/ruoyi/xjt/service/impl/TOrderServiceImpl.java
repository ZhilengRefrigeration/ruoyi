package com.ruoyi.xjt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.xjt.mapper.TOrderMapper;
import com.ruoyi.xjt.domain.TOrder;
import com.ruoyi.xjt.service.ITOrderService;

/**
 * 缴费订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
@Service
public class TOrderServiceImpl implements ITOrderService 
{
    @Autowired
    private TOrderMapper tOrderMapper;

    /**
     * 查询缴费订单
     * 
     * @param id 缴费订单主键
     * @return 缴费订单
     */
    @Override
    public TOrder selectTOrderById(Long id)
    {
        return tOrderMapper.selectTOrderById(id);
    }

    /**
     * 查询缴费订单列表
     * 
     * @param tOrder 缴费订单
     * @return 缴费订单
     */
    @Override
    public List<TOrder> selectTOrderList(TOrder tOrder)
    {
        return tOrderMapper.selectTOrderList(tOrder);
    }

    /**
     * 新增缴费订单
     * 
     * @param tOrder 缴费订单
     * @return 结果
     */
    @Override
    public int insertTOrder(TOrder tOrder)
    {
        return tOrderMapper.insertTOrder(tOrder);
    }

    /**
     * 修改缴费订单
     * 
     * @param tOrder 缴费订单
     * @return 结果
     */
    @Override
    public int updateTOrder(TOrder tOrder)
    {
        return tOrderMapper.updateTOrder(tOrder);
    }

    /**
     * 批量删除缴费订单
     * 
     * @param ids 需要删除的缴费订单主键
     * @return 结果
     */
    @Override
    public int deleteTOrderByIds(Long[] ids)
    {
        return tOrderMapper.deleteTOrderByIds(ids);
    }

    /**
     * 删除缴费订单信息
     * 
     * @param id 缴费订单主键
     * @return 结果
     */
    @Override
    public int deleteTOrderById(Long id)
    {
        return tOrderMapper.deleteTOrderById(id);
    }
}
