package com.ruoyi.wms.mapper.stock;

import com.ruoyi.wms.domain.BaseStock;

import java.util.List;

/**
 * @author Alan Scipio
 * created on 2024/2/22
 */
public interface BaseStockExtMapper {

    List<BaseStock> selectPageList(BaseStock baseStock);

}
