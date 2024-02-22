package com.ruoyi.wms.service.stock;

import com.ruoyi.common.core.web.domain.ExtBaseEntity;
import com.ruoyi.wms.domain.BaseStock;
import com.ruoyi.wms.mapper.stock.BaseStockDynamicSqlSupport;
import com.ruoyi.wms.mapper.stock.BaseStockMapper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 基本库存Service业务层处理
 *
 * @author ryas
 * created on 2024-02-22
 */
@Service
public class BaseStockServiceImpl implements IBaseStockService {
    @Autowired
    private BaseStockMapper baseStockMapper;

    /**
     * 查询基本库存
     *
     * @param whsCd 基本库存主键
     * @return 基本库存
     */
    @Override
    public BaseStock selectBaseStockByWhsCd(String whsCd) {
        Optional<BaseStock> result = baseStockMapper.selectOne(dsl -> dsl.where(BaseStockDynamicSqlSupport.whsCd, SqlBuilder.isEqualTo(whsCd)));
        return result.orElse(null);
    }

    /**
     * 查询基本库存列表
     *
     * @param baseStock 基本库存
     * @return 基本库存
     */
    @Override
    public List<BaseStock> selectBaseStockList(BaseStock baseStock) {
        SelectStatementProvider provider = SqlBuilder.select(BaseStockMapper.selectList)
                .from(BaseStockDynamicSqlSupport.baseStock)
                .where(BaseStockDynamicSqlSupport.deleteFlag, SqlBuilder.isEqualTo(ExtBaseEntity.NOT_DELETE))
                .and(BaseStockDynamicSqlSupport.whsCd, SqlBuilder.isEqualToWhenPresent(baseStock.getWhsCd()))
                .and(BaseStockDynamicSqlSupport.stgBinCd, SqlBuilder.isEqualToWhenPresent(baseStock.getStgBinCd()))
                .and(BaseStockDynamicSqlSupport.itemCd, SqlBuilder.isLikeWhenPresent(baseStock.getItemCd() == null ? null : "%" + baseStock.getItemCd() + "%"))
                .and(BaseStockDynamicSqlSupport.lotNo, SqlBuilder.isLikeWhenPresent(baseStock.getLotNo() == null ? null : "%" + baseStock.getLotNo() + "%"))
                .orderBy(BaseStockDynamicSqlSupport.whsCd)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return baseStockMapper.selectMany(provider);
    }

}
