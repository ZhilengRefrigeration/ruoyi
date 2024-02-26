package com.ruoyi.wms.service.stock;

import com.ruoyi.wms.domain.BaseStock;
import com.ruoyi.wms.mapper.stock.BaseStockDynamicSqlSupport;
import com.ruoyi.wms.mapper.stock.BaseStockExtMapper;
import com.ruoyi.wms.mapper.stock.BaseStockMapper;
import jakarta.annotation.Resource;
import org.mybatis.dynamic.sql.SqlBuilder;
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
    @Resource
    private BaseStockExtMapper baseStockExtMapper;

    /**
     * 查询基本库存
     *
     * @param whsCd 基本库存主键
     * @return 基本库存
     */
    @Override
    public BaseStock selectBaseStockByPK(String whsCd, String stgBinCd, String itemCd, String lotNo, String subLotNo) {
        Optional<BaseStock> result = baseStockMapper.selectOne(dsl ->
            dsl.where(BaseStockDynamicSqlSupport.whsCd, SqlBuilder.isEqualTo(whsCd))
                    .and(BaseStockDynamicSqlSupport.stgBinCd, SqlBuilder.isEqualTo(stgBinCd))
                    .and(BaseStockDynamicSqlSupport.itemCd, SqlBuilder.isEqualTo(itemCd))
                    .and(BaseStockDynamicSqlSupport.lotNo, SqlBuilder.isEqualTo(lotNo))
                    .and(BaseStockDynamicSqlSupport.subLotNo, SqlBuilder.isEqualTo(subLotNo))
        );
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
        return baseStockExtMapper.selectPageList(baseStock);
    }

}
