package com.ruoyi.wms.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.wms.domain.UnitInfo;
import com.ruoyi.wms.mapper.UnitInfoDynamicSqlSupport;
import com.ruoyi.wms.mapper.UnitInfoMapper;
import com.ruoyi.wms.service.IUnitInfoService;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 单位信息管理Service业务层处理
 *
 * @author ruoyi
 * created on 2024-02-02
 */
@Service
public class UnitInfoServiceImpl implements IUnitInfoService {
    @Autowired
    private UnitInfoMapper unitInfoMapper;

    /**
     * 查询单位信息管理
     *
     * @param orgCd 单位信息管理主键
     * @return 单位信息管理
     */
    @Override
    public UnitInfo selectUnitInfoByOrgCd(String orgCd) {
        Optional<UnitInfo> result = unitInfoMapper.selectOne(dsl -> dsl.where(UnitInfoDynamicSqlSupport.orgCd, SqlBuilder.isEqualTo(orgCd)));
        return result.orElse(null);
    }

    /**
     * 查询单位信息管理列表
     *
     * @param unitInfo 单位信息管理
     * @return 单位信息管理
     */
    @Override
    public List<UnitInfo> selectUnitInfoList(UnitInfo unitInfo) {
        return unitInfoMapper.select(dsl -> dsl
                .where(UnitInfoDynamicSqlSupport.orgCd, SqlBuilder.isEqualToWhenPresent(unitInfo.getOrgCd()))
                .and(UnitInfoDynamicSqlSupport.unit, SqlBuilder.isEqualToWhenPresent(unitInfo.getUnit()))
                .and(UnitInfoDynamicSqlSupport.unitName, SqlBuilder.isEqualToWhenPresent(unitInfo.getUnitName()))
                .and(UnitInfoDynamicSqlSupport.unitConvRate, SqlBuilder.isEqualToWhenPresent(unitInfo.getUnitConvRate()))
                .and(UnitInfoDynamicSqlSupport.srcConvUnit, SqlBuilder.isEqualToWhenPresent(unitInfo.getSrcConvUnit()))
                .and(UnitInfoDynamicSqlSupport.remark1, SqlBuilder.isEqualToWhenPresent(unitInfo.getRemark1()))
                .and(UnitInfoDynamicSqlSupport.remark2, SqlBuilder.isEqualToWhenPresent(unitInfo.getRemark2()))
                .and(UnitInfoDynamicSqlSupport.remark3, SqlBuilder.isEqualToWhenPresent(unitInfo.getRemark3()))
                .and(UnitInfoDynamicSqlSupport.remark4, SqlBuilder.isEqualToWhenPresent(unitInfo.getRemark4()))
                .and(UnitInfoDynamicSqlSupport.remark5, SqlBuilder.isEqualToWhenPresent(unitInfo.getRemark5()))
                .and(UnitInfoDynamicSqlSupport.updateCount, SqlBuilder.isEqualToWhenPresent(unitInfo.getUpdateCount()))
                .and(UnitInfoDynamicSqlSupport.deleteFlag, SqlBuilder.isEqualToWhenPresent(unitInfo.getDeleteFlag()))
                .and(UnitInfoDynamicSqlSupport.createBy, SqlBuilder.isEqualToWhenPresent(unitInfo.getCreateBy()))
                .and(UnitInfoDynamicSqlSupport.createTime, SqlBuilder.isEqualToWhenPresent(unitInfo.getCreateTime()))
                .and(UnitInfoDynamicSqlSupport.updateBy, SqlBuilder.isEqualToWhenPresent(unitInfo.getUpdateBy()))
                .and(UnitInfoDynamicSqlSupport.updateTime, SqlBuilder.isEqualToWhenPresent(unitInfo.getUpdateTime()))
                .and(UnitInfoDynamicSqlSupport.remark, SqlBuilder.isEqualToWhenPresent(unitInfo.getRemark()))
        );
    }

    /**
     * 新增单位信息管理
     *
     * @param unitInfo 单位信息管理
     * @return 结果
     */
    @Override
    public int insertUnitInfo(UnitInfo unitInfo) {
        unitInfo.setCreateTime(DateUtils.getNowDate());
        return unitInfoMapper.insertSelective(unitInfo);
    }

    /**
     * 修改单位信息管理
     *
     * @param unitInfo 单位信息管理
     * @return 结果
     */
    @Override
    public int updateUnitInfo(UnitInfo unitInfo) {
        unitInfo.setUpdateTime(DateUtils.getNowDate());
        return unitInfoMapper.updateByPrimaryKeySelective(unitInfo);
    }

    /**
     * 批量删除单位信息管理
     *
     * @param orgCds 需要删除的单位信息管理主键
     * @return 结果
     */
    @Override
    public int deleteUnitInfoByOrgCds(String[] orgCds) {

        return unitInfoMapper.delete(dsl -> dsl.where(UnitInfoDynamicSqlSupport.orgCd, SqlBuilder.isIn(orgCds)));
    }

    /**
     * 删除单位信息管理信息
     *
     * @param orgCd 单位信息管理主键
     * @return 结果
     */
    @Override
    public int deleteUnitInfoByOrgCd(String orgCd) {
        return unitInfoMapper.delete(dsl -> dsl.where(UnitInfoDynamicSqlSupport.orgCd, SqlBuilder.isEqualTo(orgCd)));
    }
}
