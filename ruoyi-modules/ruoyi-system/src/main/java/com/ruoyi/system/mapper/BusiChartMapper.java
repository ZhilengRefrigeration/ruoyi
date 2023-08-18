package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BusiChart;

import java.util.List;

/**
 * 测试图Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-21
 */
public interface BusiChartMapper 
{
    /**
     * 查询测试图
     * 
     * @param id 测试图主键
     * @return 测试图
     */
    public BusiChart selectBusiChartById(Long id);

    /**
     * 查询测试图列表
     * 
     * @param busiChart 测试图
     * @return 测试图集合
     */
    public List<BusiChart> selectBusiChartList(BusiChart busiChart);

    /**
     * 新增测试图
     * 
     * @param busiChart 测试图
     * @return 结果
     */
    public int insertBusiChart(BusiChart busiChart);

    /**
     * 修改测试图
     * 
     * @param busiChart 测试图
     * @return 结果
     */
    public int updateBusiChart(BusiChart busiChart);

    /**
     * 删除测试图
     * 
     * @param id 测试图主键
     * @return 结果
     */
    public int deleteBusiChartById(Long id);

    /**
     * 批量删除测试图
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiChartByIds(Long[] ids);
}
