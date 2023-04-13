package com.ruoyi.potenza.service;

import com.ruoyi.potenza.domain.TbBorrowerPlan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86155
* @description 针对表【tb_borrower_plan(计划表)】的数据库操作Service
* @createDate 2023-01-13 15:56:37
*/
public interface TbBorrowerPlanService extends IService<TbBorrowerPlan> {
    /**
     * 查询计划
     *
     * @param planId 计划主键
     * @return 计划
     */
    public TbBorrowerPlan selectTbBorrowerPlanByPlanId(Long planId);

    /**
     * 查询计划列表
     *
     * @param tbBorrowerPlan 计划
     * @return 计划集合
     */
    public List<TbBorrowerPlan> selectTbBorrowerPlanList(TbBorrowerPlan tbBorrowerPlan);

    /**
     * 新增计划
     *
     * @param tbBorrowerPlan 计划
     * @return 结果
     */
    public int insertTbBorrowerPlan(TbBorrowerPlan tbBorrowerPlan);

    /**
     * 修改计划
     *
     * @param tbBorrowerPlan 计划
     * @return 结果
     */
    public int updateTbBorrowerPlan(TbBorrowerPlan tbBorrowerPlan);

    /**
     * 批量删除计划
     *
     * @param planIds 需要删除的计划主键集合
     * @return 结果
     */
    public int deleteTbBorrowerPlanByPlanIds(Long[] planIds);

    /**
     * 删除计划信息
     *
     * @param planId 计划主键
     * @return 结果
     */
    public int deleteTbBorrowerPlanByPlanId(Long planId);
}
