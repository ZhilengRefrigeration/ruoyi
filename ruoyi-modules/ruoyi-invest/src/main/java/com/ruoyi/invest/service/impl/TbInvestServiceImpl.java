package com.ruoyi.invest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.invest.domain.TbInvest;
import com.ruoyi.invest.service.TbInvestService;
import com.ruoyi.invest.mapper.TbInvestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TbInvestServiceImpl extends ServiceImpl<TbInvestMapper, TbInvest>
    implements TbInvestService{
    @Autowired
    private TbInvestMapper tbInvestMapper;

    /**
     * 查询投资
     *
     * @param investId 投资主键
     * @return 投资
     */
    @Override
    public TbInvest selectTbInvestByInvestId(Long investId)
    {
        return tbInvestMapper.selectTbInvestByInvestId(investId);
    }

    /**
     * 查询投资列表
     *
     * @param tbInvest 投资
     * @return 投资
     */
    @Override
    public List<TbInvest> selectTbInvestList(TbInvest tbInvest)
    {
        return tbInvestMapper.selectTbInvestList(tbInvest);
    }

    /**
     * 新增投资
     *
     * @param tbInvest 投资
     * @return 结果
     */
    @Override
    public int insertTbInvest(TbInvest tbInvest)
    {
        tbInvest.setCreateTime(DateUtils.getNowDate());
        return tbInvestMapper.insertTbInvest(tbInvest);
    }

    /**
     * 修改投资
     *
     * @param tbInvest 投资
     * @return 结果
     */
    @Override
    public int updateTbInvest(TbInvest tbInvest)
    {
        tbInvest.setUpdateTime(DateUtils.getNowDate());
        return tbInvestMapper.updateTbInvest(tbInvest);
    }

    /**
     * 批量删除投资
     *
     * @param investIds 需要删除的投资主键
     * @return 结果
     */
    @Override
    public int deleteTbInvestByInvestIds(Long[] investIds)
    {
        return tbInvestMapper.deleteTbInvestByInvestIds(investIds);
    }

    /**
     * 删除投资信息
     *
     * @param investId 投资主键
     * @return 结果
     */
    @Override
    public int deleteTbInvestByInvestId(Long investId)
    {
        return tbInvestMapper.deleteTbInvestByInvestId(investId);
    }
}




