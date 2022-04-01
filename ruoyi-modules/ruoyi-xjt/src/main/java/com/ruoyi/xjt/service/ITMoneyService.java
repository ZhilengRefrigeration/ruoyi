package com.ruoyi.xjt.service;

import java.util.List;
import com.ruoyi.xjt.domain.TMoney;

/**
 * 支出或收入详情Service接口
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
public interface ITMoneyService 
{
    /**
     * 查询支出或收入详情
     * 
     * @param id 支出或收入详情主键
     * @return 支出或收入详情
     */
    public TMoney selectTMoneyById(Long id);

    /**
     * 查询支出或收入详情列表
     * 
     * @param tMoney 支出或收入详情
     * @return 支出或收入详情集合
     */
    public List<TMoney> selectTMoneyList(TMoney tMoney);

    /**
     * 新增支出或收入详情
     * 
     * @param tMoney 支出或收入详情
     * @return 结果
     */
    public int insertTMoney(TMoney tMoney);

    /**
     * 修改支出或收入详情
     * 
     * @param tMoney 支出或收入详情
     * @return 结果
     */
    public int updateTMoney(TMoney tMoney);

    /**
     * 批量删除支出或收入详情
     * 
     * @param ids 需要删除的支出或收入详情主键集合
     * @return 结果
     */
    public int deleteTMoneyByIds(Long[] ids);

    /**
     * 删除支出或收入详情信息
     * 
     * @param id 支出或收入详情主键
     * @return 结果
     */
    public int deleteTMoneyById(Long id);
}
