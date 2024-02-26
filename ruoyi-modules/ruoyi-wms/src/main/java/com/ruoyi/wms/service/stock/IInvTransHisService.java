package com.ruoyi.wms.service.stock;

import com.ruoyi.wms.domain.InvTransHis;

import java.util.List;

/**
 * 入出库履历Service接口
 *
 * @author ryas
 * created on 2024-02-23
 */
public interface IInvTransHisService {
    /**
     * 查询入出库履历
     *
     * @param invTransNo 入出库履历主键
     * @return 入出库履历
     */
    InvTransHis selectInvTransHisByInvTransNo(String invTransNo);

    /**
     * 查询入出库履历列表
     *
     * @param invTransHis 入出库履历
     * @return 入出库履历集合
     */
    List<InvTransHis> selectInvTransHisList(InvTransHis invTransHis);

    /**
     * 批量删除入出库履历
     *
     * @param invTransNos 需要删除的入出库履历主键集合
     * @return 结果
     */
    int deleteInvTransHisByInvTransNos(String[] invTransNos);

    /**
     * 删除入出库履历信息
     *
     * @param invTransNo 入出库履历主键
     * @return 结果
     */
    int deleteInvTransHisByInvTransNo(String invTransNo);
}
