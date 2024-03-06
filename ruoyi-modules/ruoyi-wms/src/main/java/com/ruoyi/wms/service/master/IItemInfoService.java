package com.ruoyi.wms.service.master;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.wms.domain.ItemInfo;

import java.util.List;

/**
 * 物品基础信息Service接口
 *
 * @author ryas
 * created on 2024-02-20
 */
public interface IItemInfoService {
    /**
     * 查询物品基础信息
     *
     * @param itemCd 物品基础信息主键
     * @return 物品基础信息
     */
    ItemInfo selectItemInfoByItemCd(String itemCd);

    /**
     * 查询物品基础信息列表
     *
     * @param itemInfo 物品基础信息
     * @return 物品基础信息集合
     */
    List<ItemInfo> selectItemInfoList(ItemInfo itemInfo);

    /**
     * 新增物品基础信息
     *
     * @param itemInfo 物品基础信息
     * @return 结果
     */
    AjaxResult insertItemInfo(ItemInfo itemInfo);

    /**
     * 修改物品基础信息
     *
     * @param itemInfo 物品基础信息
     * @return 结果
     */
    AjaxResult updateItemInfo(ItemInfo itemInfo);

    /**
     * 批量删除物品基础信息
     *
     * @param itemCds 需要删除的物品基础信息主键集合
     * @return 结果
     */
    int deleteItemInfoByItemCds(String[] itemCds);

    /**
     * 删除物品基础信息信息
     *
     * @param itemCd 物品基础信息主键
     * @return 结果
     */
    int deleteItemInfoByItemCd(String itemCd);

    /**
     * 检查物品代码是否已存在
     *
     * @param itemCd               物品代码
     * @param deleteIfLogicDeleted 如果是已经逻辑删除的数据，则是否顺带物理删除掉（如果为true则逻辑删除的也会返回null）
     * @return null:不存在; not null:存在
     */
    ItemInfo checkItemCdExists(String itemCd, boolean deleteIfLogicDeleted);
}
