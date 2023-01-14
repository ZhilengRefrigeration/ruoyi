package com.ruoyi.invest.mapper;

import com.ruoyi.invest.domain.TbFirm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.ruoyi.invest.domain.TbFirm
 */
@Mapper
public interface TbFirmMapper extends BaseMapper<TbFirm> {
    /**
     * 查询企业
     *
     * @param firmId 企业主键
     * @return 企业
     */
    public TbFirm selectTbFirmByFirmId(Long firmId);

    /**
     * 查询企业列表
     *
     * @param tbFirm 企业
     * @return 企业集合
     */
    public List<TbFirm> selectTbFirmList(TbFirm tbFirm);

    /**
     * 新增企业
     *
     * @param tbFirm 企业
     * @return 结果
     */
    public int insertTbFirm(TbFirm tbFirm);

    /**
     * 修改企业
     *
     * @param tbFirm 企业
     * @return 结果
     */
    public int updateTbFirm(TbFirm tbFirm);

    /**
     * 删除企业
     *
     * @param firmId 企业主键
     * @return 结果
     */
    public int deleteTbFirmByFirmId(Long firmId);

    /**
     * 批量删除企业
     *
     * @param firmIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbFirmByFirmIds(Long[] firmIds);
}




