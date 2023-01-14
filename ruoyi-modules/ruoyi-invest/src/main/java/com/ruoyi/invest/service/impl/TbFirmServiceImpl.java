package com.ruoyi.invest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.invest.domain.TbFirm;
import com.ruoyi.invest.service.TbFirmService;
import com.ruoyi.invest.mapper.TbFirmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TbFirmServiceImpl extends ServiceImpl<TbFirmMapper, TbFirm>
    implements TbFirmService{
    @Autowired
    private TbFirmMapper tbFirmMapper;

    /**
     * 查询企业
     *
     * @param firmId 企业主键
     * @return 企业
     */
    @Override
    public TbFirm selectTbFirmByFirmId(Long firmId)
    {
        return tbFirmMapper.selectTbFirmByFirmId(firmId);
    }

    /**
     * 查询企业列表
     *
     * @param tbFirm 企业
     * @return 企业
     */
    @Override
    public List<TbFirm> selectTbFirmList(TbFirm tbFirm)
    {
        return tbFirmMapper.selectTbFirmList(tbFirm);
    }

    /**
     * 新增企业
     *
     * @param tbFirm 企业
     * @return 结果
     */
    @Override
    public int insertTbFirm(TbFirm tbFirm)
    {
        tbFirm.setCreateTime(DateUtils.getNowDate());
        return tbFirmMapper.insertTbFirm(tbFirm);
    }

    /**
     * 修改企业
     *
     * @param tbFirm 企业
     * @return 结果
     */
    @Override
    public int updateTbFirm(TbFirm tbFirm)
    {
        tbFirm.setUpdateTime(DateUtils.getNowDate());
        return tbFirmMapper.updateTbFirm(tbFirm);
    }

    /**
     * 批量删除企业
     *
     * @param firmIds 需要删除的企业主键
     * @return 结果
     */
    @Override
    public int deleteTbFirmByFirmIds(Long[] firmIds)
    {
        return tbFirmMapper.deleteTbFirmByFirmIds(firmIds);
    }

    /**
     * 删除企业信息
     *
     * @param firmId 企业主键
     * @return 结果
     */
    @Override
    public int deleteTbFirmByFirmId(Long firmId)
    {
        return tbFirmMapper.deleteTbFirmByFirmId(firmId);
    }
}




