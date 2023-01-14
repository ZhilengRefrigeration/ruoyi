package com.ruoyi.invest.service;

import com.ruoyi.invest.domain.TbInvest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface TbInvestService extends IService<TbInvest> {
    /**
     * 查询投资
     *
     * @param investId 投资主键
     * @return 投资
     */
    public TbInvest selectTbInvestByInvestId(Long investId);

    /**
     * 查询投资列表
     *
     * @param tbInvest 投资
     * @return 投资集合
     */
    public List<TbInvest> selectTbInvestList(TbInvest tbInvest);

    /**
     * 新增投资
     *
     * @param tbInvest 投资
     * @return 结果
     */
    public int insertTbInvest(TbInvest tbInvest);

    /**
     * 修改投资
     *
     * @param tbInvest 投资
     * @return 结果
     */
    public int updateTbInvest(TbInvest tbInvest);

    /**
     * 批量删除投资
     *
     * @param investIds 需要删除的投资主键集合
     * @return 结果
     */
    public int deleteTbInvestByInvestIds(Long[] investIds);

    /**
     * 删除投资信息
     *
     * @param investId 投资主键
     * @return 结果
     */
    public int deleteTbInvestByInvestId(Long investId);
}
