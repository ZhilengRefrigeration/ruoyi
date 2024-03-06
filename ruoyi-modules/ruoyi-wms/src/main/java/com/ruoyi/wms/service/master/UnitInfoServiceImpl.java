package com.ruoyi.wms.service.master;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.ExtBaseEntity;
import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.common.services.ISysSequenceService;
import com.ruoyi.common.services.constants.SeqType;
import com.ruoyi.wms.domain.UnitInfo;
import com.ruoyi.wms.mapper.master.UnitInfoDynamicSqlSupport;
import com.ruoyi.wms.mapper.master.UnitInfoMapper;
import jakarta.annotation.Resource;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private UnitInfoMapper unitInfoMapper;
    @Resource
    private ISysSequenceService sequenceService;

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
                .orderBy(UnitInfoDynamicSqlSupport.unitCode)
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
    @Transactional
    @Override
    public int insertUnitInfo(UnitInfo unitInfo) {
        //检查是否已存在单位名称
        UnitInfo existsRecord = selectByUnitName(unitInfo.getUnitName());
        if (existsRecord != null) {
            if (existsRecord.isLogicDeleted()) {
                //物理删除掉旧数据
                unitInfoMapper.deleteByPrimaryKey(existsRecord.getUnitCode());
            } else {
                throw new IllegalArgumentException("单位名称已存在");
            }
        }
        //单位代码为空时生成
        if (StringUtils.isBlank(unitInfo.getUnitCode())) {
            String unitCode = sequenceService.getNextSequence(SeqType.UNIT_CD);
            unitInfo.setUnitCode(unitCode);
        }
        return unitInfoMapper.insertSelective(unitInfo);
    }

    /**
     * 修改单位信息管理
     *
     * @param unitInfo 单位信息管理
     * @return 结果
     */
    @Transactional
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
    @Transactional
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
    @Transactional
    @Override
    public int deleteUnitInfoByUnitCode(String unitCode) {
        UnitInfo record = new UnitInfo();
        record.setUnitCode(unitCode);
        record.setDeleteFlag(ExtBaseEntity.DELETED);
        return unitInfoMapper.updateByPrimaryKey(record);
    }

    /**
     * 如果不存在就新增
     *
     * @param unitName 单位名称
     * @param remark   备注
     */
    @Transactional
    @Override
    public void addIfNotExist(String unitName, String remark) {
        UnitInfo existsRecord = selectByUnitName(unitName);
        if (existsRecord != null) {
            if (!existsRecord.isLogicDeleted()) {
                String unitCode = sequenceService.getNextSequence(SeqType.UNIT_CD);
                existsRecord.setUnitCode(unitCode);
                existsRecord.setDeleteFlag(ExtBaseEntity.NOT_DELETE);
                existsRecord.setRemark1(remark == null ? "" : remark);
                unitInfoMapper.updateByPrimaryKeySelective(existsRecord);
            }
        } else {
            UnitInfo newRecord = new UnitInfo();
            String unitCode = sequenceService.getNextSequence(SeqType.UNIT_CD);
            newRecord.setUnitCode(unitCode);
            newRecord.setUnitName(unitName);
            newRecord.setRemark1(remark);
            unitInfoMapper.insertSelective(newRecord);
        }
    }

    private UnitInfo selectByUnitName(String unitName) {
        Optional<UnitInfo> result = unitInfoMapper.selectOne(dsl -> dsl.where(UnitInfoDynamicSqlSupport.unitName, SqlBuilder.isEqualTo(unitName)));
        return result.orElse(null);
    }

}
