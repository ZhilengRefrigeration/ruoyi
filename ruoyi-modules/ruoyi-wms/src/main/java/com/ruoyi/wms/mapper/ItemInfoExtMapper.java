package com.ruoyi.wms.mapper;

import com.ruoyi.wms.domain.ItemInfo;

import java.util.List;

/**
 * 物品基础信息扩展Mapper
 *
 * @author Alan Scipio
 * created on 2024/2/21
 */
public interface ItemInfoExtMapper {

    /**
     * 页面查询
     */
    List<ItemInfo> selectPageList(ItemInfo itemInfo);

}
