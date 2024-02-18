package com.ruoyi.wms.service;

import java.util.List;

import com.ruoyi.wms.domain .GoodsType;

/**
 * 物品类型管理Service接口
 *
 * @author ryas
 * created on 2024-02-18
 */
public interface IGoodsTypeService {
    /**
     * 查询物品类型管理
     *
     * @param goodsTypeCd 物品类型管理主键
     * @return 物品类型管理
     */
    GoodsType selectGoodsTypeByGoodsTypeCd(String goodsTypeCd);

    /**
     * 查询物品类型管理列表
     *
     * @param goodsType 物品类型管理
     * @return 物品类型管理集合
     */
    List<GoodsType> selectGoodsTypeList(GoodsType goodsType);

    /**
     * 新增物品类型管理
     *
     * @param goodsType 物品类型管理
     * @return 结果
     */
    int insertGoodsType(GoodsType goodsType);

    /**
     * 修改物品类型管理
     *
     * @param goodsType 物品类型管理
     * @return 结果
     */
    int updateGoodsType(GoodsType goodsType);

    /**
     * 批量删除物品类型管理
     *
     * @param goodsTypeCds 需要删除的物品类型管理主键集合
     * @return 结果
     */
    int deleteGoodsTypeByGoodsTypeCds(String[] goodsTypeCds);

    /**
     * 删除物品类型管理信息
     *
     * @param goodsTypeCd 物品类型管理主键
     * @return 结果
     */
    int deleteGoodsTypeByGoodsTypeCd(String goodsTypeCd);
}
