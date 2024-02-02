package com.ruoyi.wms.service;

import com.ruoyi.wms.domain.UnitInfo;

import java.util.List;

/**
 * 单位信息管理Service接口
 *
 * @author ruoyi
 * created on 2024-02-02
 */
public interface IUnitInfoService {
    /**
     * 查询单位信息管理
     *
     * @param orgCd 单位信息管理主键
     * @return 单位信息管理
     */
        UnitInfo selectUnitInfoByOrgCd(String orgCd);

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
     * @param orgCds 需要删除的单位信息管理主键集合
     * @return 结果
     */
    int deleteUnitInfoByOrgCds(String[] orgCds);

    /**
     * 删除单位信息管理信息
     *
     * @param orgCd 单位信息管理主键
     * @return 结果
     */
    int deleteUnitInfoByOrgCd(String orgCd);
}
