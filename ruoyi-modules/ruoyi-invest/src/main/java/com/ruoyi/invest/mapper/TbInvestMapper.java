package com.ruoyi.invest.mapper;

import com.ruoyi.invest.domain.TbInvest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.ruoyi.invest.domain.TbInvest
 */
@Mapper
public interface TbInvestMapper extends BaseMapper<TbInvest> {
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
     * 删除投资
     *
     * @param investId 投资主键
     * @return 结果
     */
    public int deleteTbInvestByInvestId(Long investId);

    /**
     * 批量删除投资
     *
     * @param investIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbInvestByInvestIds(Long[] investIds);
}




