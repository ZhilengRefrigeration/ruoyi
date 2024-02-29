package com.ruoyi.wms.service.stock;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.domain.ExtBaseEntity;
import com.ruoyi.wms.domain.BaseStock;
import com.ruoyi.wms.mapper.stock.BaseStockDynamicSqlSupport;
import com.ruoyi.wms.mapper.stock.BaseStockExtMapper;
import com.ruoyi.wms.mapper.stock.BaseStockMapper;
import jakarta.annotation.Resource;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 基本库存Service业务层处理
 *
 * @author ryas
 * created on 2024-02-22
 */
@Service
public class BaseStockServiceImpl implements IBaseStockService {

    @Resource
    private BaseStockMapper baseStockMapper;
    @Resource
    private BaseStockExtMapper baseStockExtMapper;
    @Resource
    private IInvTransHisService invTransHisService;

    private final Queue<BaseStock> dataQueue = new ConcurrentLinkedQueue<>();

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

    /**
     * 入库
     *
     * @param baseStock 库存数据
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult instock(BaseStock baseStock) {
        //TODO 参数检查

        //队列维持数据一致性
        dataQueue.offer(baseStock);
        BaseStock data = dataQueue.remove();
        doInoutStock(data);
        return AjaxResult.success();
    }

    /**
     * 出库
     *
     * @param baseStock 库存数据
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult outstock(BaseStock baseStock) {
        //TODO 参数检查

        //队列维持数据一致性
        dataQueue.offer(baseStock);
        BaseStock data = dataQueue.remove();
        doInoutStock(data);
        return AjaxResult.success();
    }

    /**
     * 入出库操作
     */
    private void doInoutStock(BaseStock form) {
        //查询库存
        List<BaseStock> stockList = queryWhenInoutStock(form);
        //更新库存
        if (stockList.isEmpty()) {

            //insert
            baseStockMapper.insert(form);
            //TODO 写入出库履历
        } else {
            //update
            BaseStock oldRecord = stockList.getFirst();
            BigDecimal newStdUnitQty = oldRecord.getStdUnitQty().add(form.getStdUnitQty());
            BigDecimal newPkgUnitQty = oldRecord.getPkgUnitQty().add(form.getPkgUnitQty());
            oldRecord.setStdUnitQty(newStdUnitQty);
            oldRecord.setPkgUnitQty(newPkgUnitQty);
            baseStockMapper.updateByPrimaryKey(oldRecord);
            //TODO 写入出库履历
        }
    }

    /**
     * 入出库时查询库存
     *
     * @param form 查询条件
     * @return 库存数据
     */
    private List<BaseStock> queryWhenInoutStock(BaseStock form) {
        SelectStatementProvider query = SqlBuilder.select(BaseStockMapper.selectList)
                .from(BaseStockDynamicSqlSupport.baseStock)
                .where(BaseStockDynamicSqlSupport.deleteFlag, SqlBuilder.isEqualTo(ExtBaseEntity.NOT_DELETE))
                .and(BaseStockDynamicSqlSupport.whsCd, SqlBuilder.isEqualTo(form.getWhsCd()))
                .and(BaseStockDynamicSqlSupport.stgBinCd, SqlBuilder.isEqualTo(form.getStgBinCd()))
                .and(BaseStockDynamicSqlSupport.itemCd, SqlBuilder.isEqualTo(form.getItemCd()))
                .and(BaseStockDynamicSqlSupport.lotNo, SqlBuilder.isEqualTo(form.getLotNo()))
                .and(BaseStockDynamicSqlSupport.subLotNo, SqlBuilder.isEqualTo(form.getSubLotNo()))
                .orderBy(BaseStockDynamicSqlSupport.createTime.descending())
                .limit(1)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return baseStockMapper.selectMany(query);
    }

}
