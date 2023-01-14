package com.ruoyi.potenza.service;

import com.ruoyi.potenza.domain.TbBorrowerPeriods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86155
* @description 针对表【tb_borrower_periods(贷款周期表)】的数据库操作Service
* @createDate 2023-01-13 15:56:37
*/
public interface TbBorrowerPeriodsService extends IService<TbBorrowerPeriods> {
    /**
     * 查询贷款周期
     *
     * @param periodsId 贷款周期主键
     * @return 贷款周期
     */
    public TbBorrowerPeriods selectTbBorrowerPeriodsByPeriodsId(Long periodsId);

    /**
     * 查询贷款周期列表
     *
     * @param tbBorrowerPeriods 贷款周期
     * @return 贷款周期集合
     */
    public List<TbBorrowerPeriods> selectTbBorrowerPeriodsList(TbBorrowerPeriods tbBorrowerPeriods);

    /**
     * 新增贷款周期
     *
     * @param tbBorrowerPeriods 贷款周期
     * @return 结果
     */
    public int insertTbBorrowerPeriods(TbBorrowerPeriods tbBorrowerPeriods);

    /**
     * 修改贷款周期
     *
     * @param tbBorrowerPeriods 贷款周期
     * @return 结果
     */
    public int updateTbBorrowerPeriods(TbBorrowerPeriods tbBorrowerPeriods);

    /**
     * 批量删除贷款周期
     *
     * @param periodsIds 需要删除的贷款周期主键集合
     * @return 结果
     */
    public int deleteTbBorrowerPeriodsByPeriodsIds(Long[] periodsIds);

    /**
     * 删除贷款周期信息
     *
     * @param periodsId 贷款周期主键
     * @return 结果
     */
    public int deleteTbBorrowerPeriodsByPeriodsId(Long periodsId);
}
