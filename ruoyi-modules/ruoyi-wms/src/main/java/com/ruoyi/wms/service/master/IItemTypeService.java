package com.ruoyi.wms.service.master;

import com.ruoyi.wms.domain.ItemType;

import java.util.List;

/**
 * 物品类型Service接口
 *
 * @author ryas
 * created on 2024-02-20
 */
public interface IItemTypeService {
    /**
     * 查询物品类型
     *
     * @param itemTypeCd 物品类型主键
     * @return 物品类型
     */
    ItemType selectItemTypeByItemTypeCd(String itemTypeCd);

    /**
     * 查询物品类型列表
     *
     * @param itemType 物品类型
     * @return 物品类型集合
     */
    List<ItemType> selectItemTypeList(ItemType itemType);

    /**
     * 新增物品类型
     *
     * @param itemType 物品类型
     * @return 结果
     */
    int insertItemType(ItemType itemType);

    /**
     * 修改物品类型
     *
     * @param itemType 物品类型
     * @return 结果
     */
    int updateItemType(ItemType itemType);

    /**
     * 批量删除物品类型
     *
     * @param itemTypeCds 需要删除的物品类型主键集合
     * @return 结果
     */
    int deleteItemTypeByItemTypeCds(String[] itemTypeCds);

    /**
     * 删除物品类型信息
     *
     * @param itemTypeCd 物品类型主键
     * @return 结果
     */
    int deleteItemTypeByItemTypeCd(String itemTypeCd);
}
