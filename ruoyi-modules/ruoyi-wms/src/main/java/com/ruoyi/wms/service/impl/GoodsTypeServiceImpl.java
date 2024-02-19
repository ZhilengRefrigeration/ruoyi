package com.ruoyi.wms.service.impl;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.ExtBaseEntity;
import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.common.services.ISysSequenceService;
import com.ruoyi.common.services.constants.SeqType;
import com.ruoyi.wms.domain.GoodsType;
import com.ruoyi.wms.mapper.GoodsTypeDynamicSqlSupport;
import com.ruoyi.wms.mapper.GoodsTypeMapper;
import com.ruoyi.wms.service.IGoodsTypeService;
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
 * 物品类型管理Service业务层处理
 *
 * @author ryas
 * created on 2024-02-18
 */
@Service
public class GoodsTypeServiceImpl implements IGoodsTypeService {

    @Resource
    private GoodsTypeMapper goodsTypeMapper;
    @Resource
    private ISysSequenceService sequenceService;

    /**
     * 查询物品类型管理
     *
     * @param goodsTypeCd 物品类型管理主键
     * @return 物品类型管理
     */
    @Override
    public GoodsType selectGoodsTypeByGoodsTypeCd(String goodsTypeCd) {
        Optional<GoodsType> result = goodsTypeMapper.selectOne(dsl -> dsl.where(GoodsTypeDynamicSqlSupport.goodsTypeCd, SqlBuilder.isEqualTo(goodsTypeCd)));
        return result.orElse(null);
    }

    /**
     * 查询物品类型管理列表
     *
     * @param goodsType 物品类型管理
     * @return 物品类型管理
     */
    @Override
    public List<GoodsType> selectGoodsTypeList(GoodsType goodsType) {
        SelectStatementProvider provider = SqlBuilder.select(GoodsTypeMapper.selectList)
                .from(GoodsTypeDynamicSqlSupport.goodsType)
                .where(GoodsTypeDynamicSqlSupport.deleteFlag, SqlBuilder.isEqualTo(ExtBaseEntity.NOT_DELETE))
                .and(GoodsTypeDynamicSqlSupport.goodsTypeCd, SqlBuilder.isEqualToWhenPresent(goodsType.getGoodsTypeCd()))
                .and(GoodsTypeDynamicSqlSupport.goodsTypeName, SqlBuilder.isLikeWhenPresent(goodsType.getGoodsTypeName() == null ? null : "%" + goodsType.getGoodsTypeName() + "%"))
                .and(GoodsTypeDynamicSqlSupport.remark1, SqlBuilder.isLikeWhenPresent(goodsType.getRemark1() == null ? null : "%" + goodsType.getRemark1() + "%"))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return goodsTypeMapper.selectMany(provider);
    }

    /**
     * 新增物品类型管理
     *
     * @param goodsType 物品类型管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertGoodsType(GoodsType goodsType) {
        if (StringUtils.isBlank(goodsType.getGoodsTypeCd())) {
            String goodsTypeCd = sequenceService.getNextSequence(SeqType.GOODE_TYPE_CD);
            goodsType.setGoodsTypeCd(goodsTypeCd);
        }
        return goodsTypeMapper.insertSelective(goodsType);
    }

    /**
     * 修改物品类型管理
     *
     * @param goodsType 物品类型管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateGoodsType(GoodsType goodsType) {
        return goodsTypeMapper.updateByPrimaryKeySelective(goodsType);
    }

    /**
     * 批量删除物品类型管理
     *
     * @param goodsTypeCds 需要删除的物品类型管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteGoodsTypeByGoodsTypeCds(String[] goodsTypeCds) {
        String userId = SecurityUtilsExt.getUserIdStr();
        UpdateStatementProvider provider = SqlBuilder.update(GoodsTypeDynamicSqlSupport.goodsType)
                .set(GoodsTypeDynamicSqlSupport.deleteFlag).equalTo(ExtBaseEntity.DELETED)
                .set(GoodsTypeDynamicSqlSupport.updateTime).equalTo(DateUtils.getNowDate())
                .set(GoodsTypeDynamicSqlSupport.updateBy).equalTo(userId)
                .where(GoodsTypeDynamicSqlSupport.goodsTypeCd, SqlBuilder.isIn(goodsTypeCds))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return goodsTypeMapper.update(provider);
    }

    /**
     * 删除物品类型管理信息
     *
     * @param goodsTypeCd 物品类型管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteGoodsTypeByGoodsTypeCd(String goodsTypeCd) {
        GoodsType record = new GoodsType();
        record.setGoodsTypeCd(goodsTypeCd);
        record.setDeleteFlag(ExtBaseEntity.DELETED);
        record.setUpdateTime(DateUtils.getNowDate());
        return goodsTypeMapper.updateByPrimaryKey(record);
    }
}
