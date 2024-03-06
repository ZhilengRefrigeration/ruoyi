package com.ruoyi.wms.service.stock;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.domain.ExtBaseEntity;
import com.ruoyi.wms.domain.BaseStock;
import com.ruoyi.wms.domain.ItemInfo;
import com.ruoyi.wms.domain.WarehouseInfo;
import com.ruoyi.wms.domain.vo.StockVo;
import com.ruoyi.wms.exception.StockException;
import com.ruoyi.wms.mapper.stock.BaseStockDynamicSqlSupport;
import com.ruoyi.wms.mapper.stock.BaseStockExtMapper;
import com.ruoyi.wms.mapper.stock.BaseStockMapper;
import com.ruoyi.wms.service.master.IItemInfoService;
import com.ruoyi.wms.service.master.IWarehouseInfoService;
import jakarta.annotation.Resource;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
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
    @Resource
    private IWarehouseInfoService warehouseInfoService;
    @Resource
    private IItemInfoService itemInfoService;

    private final Queue<StockVo> dataQueue = new ConcurrentLinkedQueue<>();

    /**
     * 查询基本库存
     *
     * @param whsCd 基本库存主键
     * @return 基本库存
     */
    @Override
    public BaseStock selectBaseStockByPK(String whsCd, String stgBinCd, String itemCd, String lotNo, String subLotNo) {
        BaseStock queryForm = new BaseStock();
        queryForm.setWhsCd(whsCd);
        queryForm.setStgBinCd(stgBinCd);
        queryForm.setItemCd(itemCd);
        queryForm.setLotNo(lotNo);
        queryForm.setSubLotNo(subLotNo);
        List<BaseStock> result = baseStockExtMapper.selectPageList(queryForm);
        return result.isEmpty() ? null : result.getFirst();
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
     * @param stockVo 库存数据
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult instock(StockVo stockVo) {
        // 参数检查
        AjaxResult checkResult = checkInoutStock(stockVo);
        if (!checkResult.isSuccess()) {
            return checkResult;
        }
        // 队列维持数据一致性
        stockVo.setStockType(StockVo.INSTOCK);
        dataQueue.offer(stockVo);
        StockVo data = null;
        while (data == null || !data.equalsKey(stockVo)) {
            data = dataQueue.remove();
            doInoutStock(data);
        }
        return AjaxResult.success();
    }

    /**
     * 出库
     *
     * @param stockVo 库存数据
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult outstock(StockVo stockVo) {
        // 参数检查
        AjaxResult checkResult = checkInoutStock(stockVo);
        if (!checkResult.isSuccess()) {
            return checkResult;
        }
        // 队列维持数据一致性
        stockVo.setStockType(StockVo.OUTSTOCK);
        dataQueue.offer(stockVo);
        StockVo data = null;
        while (data == null || !data.equalsKey(stockVo)) {
            data = dataQueue.remove();
            doInoutStock(data);
        }
        return AjaxResult.success();
    }

    /**
     * 入出库操作
     */
    private void doInoutStock(StockVo stockVo) {
        //查询库存
        List<BaseStock> stockList = queryWhenInoutStock(stockVo);
        //更新库存
        if (stockList.isEmpty()) {
            if (stockVo.isOutstock()) {
                throw new StockException("库存不足");
            }
            //insert
            BaseStock newRecord = buildNewRecord(stockVo);
            baseStockMapper.insertSelective(newRecord);
            //写入出库履历
            invTransHisService.addInvTransHis(stockVo);
        } else {
            //update
            BaseStock oldRecord = stockList.getFirst();
            if (stockVo.isOutstock()) {
                //出库时的检查
                if (oldRecord.getStdUnitQty().compareTo(stockVo.getStdUnitQty()) < 0) {
                    throw new StockException("库存不足");
                }
            }
            BaseStock updateRecord = buildUpdateRecord(stockVo, oldRecord);
            baseStockMapper.updateByPrimaryKeySelective(updateRecord);
            //写入出库履历
            invTransHisService.addInvTransHis(stockVo);
        }
    }

    /**
     * 参数检查
     */
    private AjaxResult checkInoutStock(StockVo stockVo) {
        //非空检查
        if (stockVo == null) {
            return AjaxResult.error("stockVo is null");
        }
        if (stockVo.getStockType() == null || stockVo.getStockType() < 1 || stockVo.getStockType() > 2) {
            return AjaxResult.error("入出库类型为空或不合法");
        }
        if (StringUtils.isBlank(stockVo.getWhsCd())) {
            return AjaxResult.error("仓库代码为空");
        }
        if (StringUtils.isBlank(stockVo.getStgBinCd())) {
            return AjaxResult.error("货架号为空");
        }
        if (StringUtils.isBlank(stockVo.getItemCd())) {
            return AjaxResult.error("物品代码为空");
        }
        if (stockVo.getStdUnitQty() == null) {
            return AjaxResult.error("标准单位数量为空");
        }
        if (stockVo.getStdUnitQty().compareTo(BigDecimal.ZERO) < 0) {
            return AjaxResult.error("标准单位数量不能小于0");
        }
        if (stockVo.getPkgUnitQty() != null && stockVo.getPkgUnitQty().compareTo(BigDecimal.ZERO) < 0) {
            return AjaxResult.error("包装单位数量不能小于0");
        }
        //检查仓库代码是否存在
        WarehouseInfo warehouseInfo = warehouseInfoService.checkWhsCdExists(stockVo.getWhsCd(), false);
        if (warehouseInfo == null || warehouseInfo.isLogicDeleted()) {
            return AjaxResult.error("仓库不存在");
        }
        //检查物品代码是否存在
        ItemInfo itemInfo = itemInfoService.checkItemCdExists(stockVo.getItemCd(), false);
        if (itemInfo == null || itemInfo.isLogicDeleted()) {
            return AjaxResult.error("物品不存在");
        }
        return AjaxResult.success();
    }

    /**
     * 入出库时查询库存
     *
     * @param stockVo 查询条件
     * @return 库存数据
     */
    private List<BaseStock> queryWhenInoutStock(StockVo stockVo) {
        SelectStatementProvider query = SqlBuilder.select(BaseStockMapper.selectList)
                .from(BaseStockDynamicSqlSupport.baseStock)
                .where(BaseStockDynamicSqlSupport.deleteFlag, SqlBuilder.isEqualTo(ExtBaseEntity.NOT_DELETE))
                .and(BaseStockDynamicSqlSupport.whsCd, SqlBuilder.isEqualTo(stockVo.getWhsCd()))
                .and(BaseStockDynamicSqlSupport.stgBinCd, SqlBuilder.isEqualTo(stockVo.getStgBinCd()))
                .and(BaseStockDynamicSqlSupport.itemCd, SqlBuilder.isEqualTo(stockVo.getItemCd()))
                .and(BaseStockDynamicSqlSupport.lotNo, SqlBuilder.isEqualTo(stockVo.getLotNo()))
                .and(BaseStockDynamicSqlSupport.subLotNo, SqlBuilder.isEqualTo(stockVo.getSubLotNo()))
                .orderBy(BaseStockDynamicSqlSupport.createTime.descending())
                .limit(1)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return baseStockMapper.selectMany(query);
    }

    private BaseStock buildNewRecord(StockVo stockVo) {
        BaseStock record = new BaseStock();
        record.setWhsCd(stockVo.getWhsCd());
        record.setStgBinCd(stockVo.getStgBinCd());
        record.setItemCd(stockVo.getItemCd());
        record.setLotNo(stockVo.getLotNo());
        record.setSubLotNo(stockVo.getSubLotNo());
        record.setStdUnitQty(stockVo.getStdUnitQty());
        record.setPkgUnitQty(stockVo.getPkgUnitQty());
        return record;
    }

    private BaseStock buildUpdateRecord(StockVo stockVo, BaseStock oldRecord) {
        // 标准单位数量
        if (stockVo.isOutstock()) {
            //出库数量为负数
            stockVo.setStdUnitQty(stockVo.getStdUnitQty().negate());
        }
        BigDecimal newStdUnitQty = oldRecord.getStdUnitQty().add(stockVo.getStdUnitQty());
        oldRecord.setStdUnitQty(newStdUnitQty);

        // 包装单位数量
        if (stockVo.getPkgUnitQty() != null) {
            if (stockVo.isOutstock()) {
                //出库数量为负数
                stockVo.setPkgUnitQty(stockVo.getPkgUnitQty().negate());
            }
            BigDecimal newPkgUnitQty = oldRecord.getPkgUnitQty().add(stockVo.getPkgUnitQty());
            oldRecord.setPkgUnitQty(newPkgUnitQty);
        }

        return oldRecord;
    }

}
