package com.ruoyi.wms.service.master;

import com.ruoyi.wms.domain.WarehouseInfo;

import java.util.List;

/**
 * 仓库基础信息Service接口
 *
 * @author ryas
 * created on 2024-02-18
 */
public interface IWarehouseInfoService {
    /**
     * 查询仓库基础信息
     *
     * @param whsCd 仓库基础信息主键
     * @return 仓库基础信息
     */
    WarehouseInfo selectWarehouseInfoByWhsCd(String whsCd);

    /**
     * 查询仓库基础信息列表
     *
     * @param warehouseInfo 仓库基础信息
     * @return 仓库基础信息集合
     */
    List<WarehouseInfo> selectWarehouseInfoList(WarehouseInfo warehouseInfo);

    /**
     * 新增仓库基础信息
     *
     * @param warehouseInfo 仓库基础信息
     * @return 结果
     */
    int insertWarehouseInfo(WarehouseInfo warehouseInfo);

    /**
     * 修改仓库基础信息
     *
     * @param warehouseInfo 仓库基础信息
     * @return 结果
     */
    int updateWarehouseInfo(WarehouseInfo warehouseInfo);

    /**
     * 批量删除仓库基础信息
     *
     * @param whsCds 需要删除的仓库基础信息主键集合
     * @return 结果
     */
    int deleteWarehouseInfoByWhsCds(String[] whsCds);

    /**
     * 删除仓库基础信息信息
     *
     * @param whsCd 仓库基础信息主键
     * @return 结果
     */
    int deleteWarehouseInfoByWhsCd(String whsCd);

    /**
     * 检查仓库代码是否存在
     *
     * @param whsCd                仓库代码
     * @param deleteIfLogicDeleted 如果是已经逻辑删除的数据，则是否顺带物理删除掉（如果为true则逻辑删除的也会返回null）
     * @return null:不存在; not null:存在
     */
    WarehouseInfo checkWhsCdExists(String whsCd, boolean deleteIfLogicDeleted);
}
