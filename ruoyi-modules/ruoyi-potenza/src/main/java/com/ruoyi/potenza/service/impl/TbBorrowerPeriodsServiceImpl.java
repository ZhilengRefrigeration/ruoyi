package com.ruoyi.potenza.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.potenza.domain.TbBorrowerPeriods;
import com.ruoyi.potenza.service.TbBorrowerPeriodsService;
import com.ruoyi.potenza.mapper.TbBorrowerPeriodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 86155
* @description 针对表【tb_borrower_periods(贷款周期表)】的数据库操作Service实现
* @createDate 2023-01-13 15:56:37
*/
@Service
public class TbBorrowerPeriodsServiceImpl extends ServiceImpl<TbBorrowerPeriodsMapper, TbBorrowerPeriods>
    implements TbBorrowerPeriodsService{
    @Autowired
    private TbBorrowerPeriodsMapper tbBorrowerPeriodsMapper;

    /**
     * 查询贷款周期
     *
     * @param periodsId 贷款周期主键
     * @return 贷款周期
     */
    @Override
    public TbBorrowerPeriods selectTbBorrowerPeriodsByPeriodsId(Long periodsId)
    {
        return tbBorrowerPeriodsMapper.selectTbBorrowerPeriodsByPeriodsId(periodsId);
    }

    /**
     * 查询贷款周期列表
     *
     * @param tbBorrowerPeriods 贷款周期
     * @return 贷款周期
     */
    @Override
    public List<TbBorrowerPeriods> selectTbBorrowerPeriodsList(TbBorrowerPeriods tbBorrowerPeriods)
    {
        return tbBorrowerPeriodsMapper.selectTbBorrowerPeriodsList(tbBorrowerPeriods);
    }

    /**
     * 新增贷款周期
     *
     * @param tbBorrowerPeriods 贷款周期
     * @return 结果
     */
    @Override
    public int insertTbBorrowerPeriods(TbBorrowerPeriods tbBorrowerPeriods)
    {
        tbBorrowerPeriods.setCreateTime(DateUtils.getNowDate());
        return tbBorrowerPeriodsMapper.insertTbBorrowerPeriods(tbBorrowerPeriods);
    }

    /**
     * 修改贷款周期
     *
     * @param tbBorrowerPeriods 贷款周期
     * @return 结果
     */
    @Override
    public int updateTbBorrowerPeriods(TbBorrowerPeriods tbBorrowerPeriods)
    {
        tbBorrowerPeriods.setUpdateTime(DateUtils.getNowDate());
        return tbBorrowerPeriodsMapper.updateTbBorrowerPeriods(tbBorrowerPeriods);
    }

    /**
     * 批量删除贷款周期
     *
     * @param periodsIds 需要删除的贷款周期主键
     * @return 结果
     */
    @Override
    public int deleteTbBorrowerPeriodsByPeriodsIds(Long[] periodsIds)
    {
        return tbBorrowerPeriodsMapper.deleteTbBorrowerPeriodsByPeriodsIds(periodsIds);
    }

    /**
     * 删除贷款周期信息
     *
     * @param periodsId 贷款周期主键
     * @return 结果
     */
    @Override
    public int deleteTbBorrowerPeriodsByPeriodsId(Long periodsId)
    {
        return tbBorrowerPeriodsMapper.deleteTbBorrowerPeriodsByPeriodsId(periodsId);
    }

}




