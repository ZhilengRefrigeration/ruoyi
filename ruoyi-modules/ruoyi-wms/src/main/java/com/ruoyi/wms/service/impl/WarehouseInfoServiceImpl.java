package com.ruoyi.wms.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.ExtBaseEntity;
import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.common.services.ISysSequenceService;
import com.ruoyi.common.services.constants.SeqType;
import com.ruoyi.wms.domain.WarehouseInfo;
import com.ruoyi.wms.mapper.WarehouseInfoDynamicSqlSupport;
import com.ruoyi.wms.mapper.WarehouseInfoMapper;
import com.ruoyi.wms.service.IWarehouseInfoService;
import jakarta.annotation.Resource;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 仓库基础信息Service业务层处理
 *
 * @author ryas
 * created on 2024-02-18
 */
@Service
public class WarehouseInfoServiceImpl implements IWarehouseInfoService {

    @Resource
    private WarehouseInfoMapper warehouseInfoMapper;
    @Resource
    private ISysSequenceService sequenceService;

    /**
     * 查询仓库基础信息
     *
     * @param whsCd 仓库基础信息主键
     * @return 仓库基础信息
     */
    @Override
    public WarehouseInfo selectWarehouseInfoByWhsCd(String whsCd) {
        Optional<WarehouseInfo> result = warehouseInfoMapper.selectOne(dsl -> dsl.where(WarehouseInfoDynamicSqlSupport.whsCd, SqlBuilder.isEqualTo(whsCd)));
        return result.orElse(null);
    }

    /**
     * 查询仓库基础信息列表
     *
     * @param warehouseInfo 仓库基础信息
     * @return 仓库基础信息
     */
    @Override
    public List<WarehouseInfo> selectWarehouseInfoList(WarehouseInfo warehouseInfo) {
        SelectStatementProvider provider = SqlBuilder.select(WarehouseInfoMapper.selectList)
                .from(WarehouseInfoDynamicSqlSupport.warehouseInfo)
                .where(WarehouseInfoDynamicSqlSupport.deleteFlag, SqlBuilder.isEqualTo(ExtBaseEntity.NOT_DELETE))
                .and(WarehouseInfoDynamicSqlSupport.whsCd, SqlBuilder.isEqualToWhenPresent(warehouseInfo.getWhsCd()))
                .and(WarehouseInfoDynamicSqlSupport.whsName, SqlBuilder.isLikeWhenPresent(warehouseInfo.getWhsName() == null ? null : "%" + warehouseInfo.getWhsName() + "%"))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return warehouseInfoMapper.selectMany(provider);
    }

    /**
     * 新增仓库基础信息
     *
     * @param warehouseInfo 仓库基础信息
     * @return 结果
     */
    @Override
    public int insertWarehouseInfo(WarehouseInfo warehouseInfo) {
        if (StringUtils.isBlank(warehouseInfo.getWhsCd())) {
            String whsCd = sequenceService.getNextSequence(SeqType.WHS_CD);
            warehouseInfo.setWhsCd(whsCd);
        }
        return warehouseInfoMapper.insertSelective(warehouseInfo);
    }

    /**
     * 修改仓库基础信息
     *
     * @param warehouseInfo 仓库基础信息
     * @return 结果
     */
    @Override
    public int updateWarehouseInfo(WarehouseInfo warehouseInfo) {
        return warehouseInfoMapper.updateByPrimaryKeySelective(warehouseInfo);
    }

    /**
     * 批量删除仓库基础信息
     *
     * @param whsCds 需要删除的仓库基础信息主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseInfoByWhsCds(String[] whsCds) {
        String userId = SecurityUtilsExt.getUserIdStr();
        UpdateStatementProvider provider = SqlBuilder.update(WarehouseInfoDynamicSqlSupport.warehouseInfo)
                .set(WarehouseInfoDynamicSqlSupport.deleteFlag).equalTo(ExtBaseEntity.DELETED)
                .set(WarehouseInfoDynamicSqlSupport.updateTime).equalTo(DateUtils.getNowDate())
                .set(WarehouseInfoDynamicSqlSupport.updateBy).equalTo(userId)
                .where(WarehouseInfoDynamicSqlSupport.whsCd, SqlBuilder.isIn(whsCds))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return warehouseInfoMapper.update(provider);
    }

    /**
     * 删除仓库基础信息信息
     *
     * @param whsCd 仓库基础信息主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseInfoByWhsCd(String whsCd) {
        WarehouseInfo record = new WarehouseInfo();
        record.setWhsCd(whsCd);
        record.setDeleteFlag(ExtBaseEntity.DELETED);
        record.setUpdateTime(DateUtils.getNowDate());
        return warehouseInfoMapper.updateByPrimaryKey(record);
    }
}
