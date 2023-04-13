package com.ruoyi.invest.service;

import com.ruoyi.invest.domain.TbFirm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface TbFirmService extends IService<TbFirm> {
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
     * 批量删除企业
     *
     * @param firmIds 需要删除的企业主键集合
     * @return 结果
     */
    public int deleteTbFirmByFirmIds(Long[] firmIds);

    /**
     * 删除企业信息
     *
     * @param firmId 企业主键
     * @return 结果
     */
    public int deleteTbFirmByFirmId(Long firmId);
}
