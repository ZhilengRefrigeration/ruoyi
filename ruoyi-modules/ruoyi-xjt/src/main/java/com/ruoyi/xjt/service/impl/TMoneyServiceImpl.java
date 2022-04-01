package com.ruoyi.xjt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.xjt.mapper.TMoneyMapper;
import com.ruoyi.xjt.domain.TMoney;
import com.ruoyi.xjt.service.ITMoneyService;

/**
 * 支出或收入详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
@Service
public class TMoneyServiceImpl implements ITMoneyService 
{
    @Autowired
    private TMoneyMapper tMoneyMapper;

    /**
     * 查询支出或收入详情
     * 
     * @param id 支出或收入详情主键
     * @return 支出或收入详情
     */
    @Override
    public TMoney selectTMoneyById(Long id)
    {
        return tMoneyMapper.selectTMoneyById(id);
    }

    /**
     * 查询支出或收入详情列表
     * 
     * @param tMoney 支出或收入详情
     * @return 支出或收入详情
     */
    @Override
    public List<TMoney> selectTMoneyList(TMoney tMoney)
    {
        return tMoneyMapper.selectTMoneyList(tMoney);
    }

    /**
     * 新增支出或收入详情
     * 
     * @param tMoney 支出或收入详情
     * @return 结果
     */
    @Override
    public int insertTMoney(TMoney tMoney)
    {
        return tMoneyMapper.insertTMoney(tMoney);
    }

    /**
     * 修改支出或收入详情
     * 
     * @param tMoney 支出或收入详情
     * @return 结果
     */
    @Override
    public int updateTMoney(TMoney tMoney)
    {
        return tMoneyMapper.updateTMoney(tMoney);
    }

    /**
     * 批量删除支出或收入详情
     * 
     * @param ids 需要删除的支出或收入详情主键
     * @return 结果
     */
    @Override
    public int deleteTMoneyByIds(Long[] ids)
    {
        return tMoneyMapper.deleteTMoneyByIds(ids);
    }

    /**
     * 删除支出或收入详情信息
     * 
     * @param id 支出或收入详情主键
     * @return 结果
     */
    @Override
    public int deleteTMoneyById(Long id)
    {
        return tMoneyMapper.deleteTMoneyById(id);
    }
}
