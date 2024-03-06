package com.ruoyi.wms.service.master;

import com.ruoyi.wms.domain.UnitInfo;

import java.util.List;

/**
 * 单位信息管理Service接口
 *
 * @author ryas
 * created on 2024-02-05
 */
public interface IUnitInfoService {
    /**
     * 查询单位信息管理
     *
     * @param unitCode 单位信息管理主键
     * @return 单位信息管理
     */
    UnitInfo selectUnitInfoByUnitCode(String unitCode);

    /**
     * 查询单位信息管理列表
     *
     * @param unitInfo 单位信息管理
     * @return 单位信息管理集合
     */
    List<UnitInfo> selectUnitInfoList(UnitInfo unitInfo);

    /**
     * 新增单位信息管理
     *
     * @param unitInfo 单位信息管理
     * @return 结果
     */
    int insertUnitInfo(UnitInfo unitInfo);

    /**
     * 修改单位信息管理
     *
     * @param unitInfo 单位信息管理
     * @return 结果
     */
    int updateUnitInfo(UnitInfo unitInfo);

    /**
     * 批量删除单位信息管理
     *
     * @param unitCodes 需要删除的单位信息管理主键集合
     * @return 结果
     */
    int deleteUnitInfoByUnitCodes(String[] unitCodes);

    /**
     * 删除单位信息管理信息
     *
     * @param unitCode 单位信息管理主键
     * @return 结果
     */
    int deleteUnitInfoByUnitCode(String unitCode);

    /**
     * 如果不存在就新增
     *
     * @param unitName 单位名称
     * @param remark   备注
     */
    void addIfNotExist(String unitName, String remark);

    default void addIfNotExist(String unitName) {
        addIfNotExist(unitName, null);
    }

}
