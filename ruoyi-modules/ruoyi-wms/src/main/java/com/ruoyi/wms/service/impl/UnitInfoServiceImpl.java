package com.ruoyi.wms.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.uuid.snowflake.SnowFlakeIdGenerator;
import com.ruoyi.common.core.web.domain.ExtBaseEntity;
import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.wms.domain.UnitInfo;
import com.ruoyi.wms.mapper.UnitInfoDynamicSqlSupport;
import com.ruoyi.wms.mapper.UnitInfoMapper;
import com.ruoyi.wms.service.IUnitInfoService;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 单位信息管理Service业务层处理
 *
 * @author ryas
 * created on 2024-02-05
 */
@Service
public class UnitInfoServiceImpl implements IUnitInfoService {
    @Autowired
    private UnitInfoMapper unitInfoMapper;

    /**
     * 查询单位信息管理
     *
     * @param unitCode 单位信息管理主键
     * @return 单位信息管理
     */
    @Override
    public UnitInfo selectUnitInfoByUnitCode(String unitCode) {
        Optional<UnitInfo> result = unitInfoMapper.selectOne(dsl -> dsl.where(UnitInfoDynamicSqlSupport.unitCode, SqlBuilder.isEqualTo(unitCode)));
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
        SelectStatementProvider provider = SqlBuilder.select(UnitInfoMapper.selectList)
                .from(UnitInfoDynamicSqlSupport.unitInfo)
                .where(UnitInfoDynamicSqlSupport.deleteFlag, SqlBuilder.isEqualTo(ExtBaseEntity.NOT_DELETE))
                .and(UnitInfoDynamicSqlSupport.unitCode, SqlBuilder.isEqualToWhenPresent(unitInfo.getUnitCode()))
                .and(UnitInfoDynamicSqlSupport.unitName, SqlBuilder.isLikeWhenPresent(unitInfo.getUnitName()))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return unitInfoMapper.selectMany(provider);
    }

    /**
     * 新增单位信息管理
     *
     * @param unitInfo 单位信息管理
     * @return 结果
     */
    @Override
    public int insertUnitInfo(UnitInfo unitInfo) {
        if (StringUtils.isBlank(unitInfo.getUnitCode())) {
            unitInfo.setUnitCode(SnowFlakeIdGenerator.nextId());
        }
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
        return unitInfoMapper.updateByPrimaryKeySelective(unitInfo);
    }

    /**
     * 批量删除单位信息管理
     *
     * @param unitCodes 需要删除的单位信息管理主键
     * @return 结果
     */
    @Override
    public int deleteUnitInfoByUnitCodes(String[] unitCodes) {
        String userId = SecurityUtilsExt.getUserIdStr();
        UpdateStatementProvider provider = SqlBuilder.update(UnitInfoDynamicSqlSupport.unitInfo)
                .set(UnitInfoDynamicSqlSupport.deleteFlag).equalTo(ExtBaseEntity.DELETED)
                .set(UnitInfoDynamicSqlSupport.updateTime).equalTo(DateUtils.getNowDate())
                .set(UnitInfoDynamicSqlSupport.updateBy).equalTo(userId)
                .where(UnitInfoDynamicSqlSupport.unitCode, SqlBuilder.isIn(unitCodes))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return unitInfoMapper.update(provider);
    }

    /**
     * 删除单位信息管理信息
     *
     * @param unitCode 单位信息管理主键
     * @return 结果
     */
    @Override
    public int deleteUnitInfoByUnitCode(String unitCode) {
        UnitInfo record = new UnitInfo();
        record.setUnitCode(unitCode);
        record.setDeleteFlag(ExtBaseEntity.DELETED);
        return unitInfoMapper.updateByPrimaryKey(record);
    }
}
