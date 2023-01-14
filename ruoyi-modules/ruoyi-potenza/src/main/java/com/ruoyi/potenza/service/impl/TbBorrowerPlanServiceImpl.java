package com.ruoyi.potenza.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.potenza.domain.TbBorrowerPlan;
import com.ruoyi.potenza.service.TbBorrowerPlanService;
import com.ruoyi.potenza.mapper.TbBorrowerPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 86155
* @description 针对表【tb_borrower_plan(计划表)】的数据库操作Service实现
* @createDate 2023-01-13 15:56:37
*/
@Service
public class TbBorrowerPlanServiceImpl extends ServiceImpl<TbBorrowerPlanMapper, TbBorrowerPlan>
    implements TbBorrowerPlanService{
    @Autowired
    private TbBorrowerPlanMapper tbBorrowerPlanMapper;

    /**
     * 查询计划
     *
     * @param planId 计划主键
     * @return 计划
     */
    @Override
    public TbBorrowerPlan selectTbBorrowerPlanByPlanId(Long planId)
    {
        return tbBorrowerPlanMapper.selectTbBorrowerPlanByPlanId(planId);
    }

    /**
     * 查询计划列表
     *
     * @param tbBorrowerPlan 计划
     * @return 计划
     */
    @Override
    public List<TbBorrowerPlan> selectTbBorrowerPlanList(TbBorrowerPlan tbBorrowerPlan)
    {
        return tbBorrowerPlanMapper.selectTbBorrowerPlanList(tbBorrowerPlan);
    }

    /**
     * 新增计划
     *
     * @param tbBorrowerPlan 计划
     * @return 结果
     */
    @Override
    public int insertTbBorrowerPlan(TbBorrowerPlan tbBorrowerPlan)
    {
        tbBorrowerPlan.setCreateTime(DateUtils.getNowDate());
        return tbBorrowerPlanMapper.insertTbBorrowerPlan(tbBorrowerPlan);
    }

    /**
     * 修改计划
     *
     * @param tbBorrowerPlan 计划
     * @return 结果
     */
    @Override
    public int updateTbBorrowerPlan(TbBorrowerPlan tbBorrowerPlan)
    {
        tbBorrowerPlan.setUpdateTime(DateUtils.getNowDate());
        return tbBorrowerPlanMapper.updateTbBorrowerPlan(tbBorrowerPlan);
    }

    /**
     * 批量删除计划
     *
     * @param planIds 需要删除的计划主键
     * @return 结果
     */
    @Override
    public int deleteTbBorrowerPlanByPlanIds(Long[] planIds)
    {
        return tbBorrowerPlanMapper.deleteTbBorrowerPlanByPlanIds(planIds);
    }

    /**
     * 删除计划信息
     *
     * @param planId 计划主键
     * @return 结果
     */
    @Override
    public int deleteTbBorrowerPlanByPlanId(Long planId)
    {
        return tbBorrowerPlanMapper.deleteTbBorrowerPlanByPlanId(planId);
    }
}




