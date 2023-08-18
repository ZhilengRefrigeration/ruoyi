package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.system.domain.BusiChart;
import com.ruoyi.system.mapper.BusiChartMapper;
import com.ruoyi.system.service.IBusiChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试图Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-21
 */
@Service
public class BusiChartServiceImpl implements IBusiChartService 
{
    @Autowired
    private BusiChartMapper busiChartMapper;

    /**
     * 查询测试图
     * 
     * @param id 测试图主键
     * @return 测试图
     */
    @Override
    public BusiChart selectBusiChartById(Long id)
    {
        return busiChartMapper.selectBusiChartById(id);
    }

    /**
     * 查询测试图列表
     * 
     * @param busiChart 测试图
     * @return 测试图
     */
    @Override
    public List<BusiChart> selectBusiChartList(BusiChart busiChart)
    {
        return busiChartMapper.selectBusiChartList(busiChart);
    }

    /**
     * 新增测试图
     * 
     * @param busiChart 测试图
     * @return 结果
     */
    @Override
    public int insertBusiChart(BusiChart busiChart)
    {
        busiChart.setCreateTime(DateUtils.getNowDate());
        return busiChartMapper.insertBusiChart(busiChart);
    }

    /**
     * 修改测试图
     * 
     * @param busiChart 测试图
     * @return 结果
     */
    @Override
    public int updateBusiChart(BusiChart busiChart)
    {
        return busiChartMapper.updateBusiChart(busiChart);
    }

    /**
     * 批量删除测试图
     * 
     * @param ids 需要删除的测试图主键
     * @return 结果
     */
    @Override
    public int deleteBusiChartByIds(Long[] ids)
    {
        return busiChartMapper.deleteBusiChartByIds(ids);
    }

    /**
     * 删除测试图信息
     * 
     * @param id 测试图主键
     * @return 结果
     */
    @Override
    public int deleteBusiChartById(Long id)
    {
        return busiChartMapper.deleteBusiChartById(id);
    }
}
