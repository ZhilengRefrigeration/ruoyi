package com.ruoyi.wms.mapper.stock;

import com.ruoyi.wms.domain.InvTransHis;

import java.util.List;

/**
 * @author Alan Scipio
 * created on 2024/2/23
 */
public interface InvTransHisExtMapper {

    List<InvTransHis> selectPageList(InvTransHis invTransHis);

}
