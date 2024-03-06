package com.ruoyi.wms.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Alan Scipio
 * created on 2024/3/6
 */
@Data
public class StockVo {

    /**
     * 入出库类型: 1.入库
     */
    public static final int INSTOCK = 1;

    /**
     * 入出库类型: 2.出库
     */
    public static final int OUTSTOCK = 2;

    /**
     * 入出库类型
     */
    private Integer stockType;

    /**
     * 仓库代码
     */
    private String whsCd;

    /**
     * 货架号
     */
    private String stgBinCd;

    /**
     * 批号
     */
    private String lotNo;

    /**
     * 子批号
     */
    private String subLotNo;

    /**
     * 物品代码
     */
    private String itemCd;

    /**
     * 从属部门ID
     */
    private Integer deptId;

    /**
     * 标准单位数量
     */
    private BigDecimal stdUnitQty;

    /**
     * 包装单位数量
     */
    private BigDecimal pkgUnitQty;

    /**
     * 托盘ID
     */
    private String palletId;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 业务区分
     */
    private String businessCls;

    /**
     * 序列号
     */
    private String serialNo;

    /**
     * 入出库理由
     */
    private String reason;

    /**
     * 交易单号
     */
    private String transOrderNo;

    /**
     * 交易单明细号
     */
    private String transOrderDetlNo;

    @JsonIgnore
    public boolean isOutstock() {
        return stockType != null && stockType == OUTSTOCK;
    }

    @JsonIgnore
    public boolean isInstock() {
        return stockType != null && stockType == INSTOCK;
    }

    @JsonIgnore
    public boolean equalsKey(StockVo stockVo) {
        if (stockVo == null) {
            return false;
        }
        if (stockType == null || !stockType.equals(stockVo.stockType)) {
            return false;
        }
        if (whsCd == null || !whsCd.equals(stockVo.whsCd)) {
            return false;
        }
        if (stgBinCd == null || !stgBinCd.equals(stockVo.stgBinCd)) {
            return false;
        }
        if (itemCd == null || !itemCd.equals(stockVo.itemCd)) {
            return false;
        }
        if (lotNo != null && !lotNo.equals(stockVo.lotNo)) {
            return false;
        }
        if (subLotNo != null && !subLotNo.equals(stockVo.subLotNo)) {
            return false;
        }
        if (stdUnitQty != null && stdUnitQty.compareTo(stockVo.stdUnitQty) != 0) {
            return false;
        }
        if (pkgUnitQty != null && pkgUnitQty.compareTo(stockVo.pkgUnitQty) != 0) {
            return false;
        }
        return true;
    }

}
