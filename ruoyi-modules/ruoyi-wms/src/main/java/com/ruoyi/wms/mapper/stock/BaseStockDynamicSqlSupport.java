package com.ruoyi.wms.mapper.stock;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class BaseStockDynamicSqlSupport {
    public static final BaseStock baseStock = new BaseStock();

    /**
     *   仓库代码
     */
    public static final SqlColumn<String> whsCd = baseStock.whsCd;

    /**
     *   货架号
     */
    public static final SqlColumn<String> stgBinCd = baseStock.stgBinCd;

    /**
     *   物品代码
     */
    public static final SqlColumn<String> itemCd = baseStock.itemCd;

    /**
     *   批号
     */
    public static final SqlColumn<String> lotNo = baseStock.lotNo;

    /**
     *   子批号
     */
    public static final SqlColumn<String> subLotNo = baseStock.subLotNo;

    /**
     *   从属部门ID
     */
    public static final SqlColumn<Integer> deptId = baseStock.deptId;

    /**
     *   标准单位数量
     */
    public static final SqlColumn<BigDecimal> stdUnitQty = baseStock.stdUnitQty;

    /**
     *   包装单位数量
     */
    public static final SqlColumn<BigDecimal> pkgUnitQty = baseStock.pkgUnitQty;

    /**
     *   序列号
     */
    public static final SqlColumn<String> serialNo = baseStock.serialNo;

    /**
     *   托盘ID
     */
    public static final SqlColumn<String> palletId = baseStock.palletId;

    /**
     *   父托盘ID
     */
    public static final SqlColumn<String> parentPalletId = baseStock.parentPalletId;

    /**
     *   备注1
     */
    public static final SqlColumn<String> remark1 = baseStock.remark1;

    /**
     *   备注2
     */
    public static final SqlColumn<String> remark2 = baseStock.remark2;

    /**
     *   备注3
     */
    public static final SqlColumn<String> remark3 = baseStock.remark3;

    /**
     *   备注4
     */
    public static final SqlColumn<String> remark4 = baseStock.remark4;

    /**
     *   备注5
     */
    public static final SqlColumn<String> remark5 = baseStock.remark5;

    /**
     *   更新次数
     */
    public static final SqlColumn<Integer> updateCount = baseStock.updateCount;

    /**
     *   删除标志
     */
    public static final SqlColumn<Integer> deleteFlag = baseStock.deleteFlag;

    /**
     *   创建者
     */
    public static final SqlColumn<String> createBy = baseStock.createBy;

    /**
     *   创建时间
     */
    public static final SqlColumn<Date> createTime = baseStock.createTime;

    /**
     *   更新者
     */
    public static final SqlColumn<String> updateBy = baseStock.updateBy;

    /**
     *   更新时间
     */
    public static final SqlColumn<Date> updateTime = baseStock.updateTime;

    /**
     *   备注
     */
    public static final SqlColumn<String> remark = baseStock.remark;

    public static final class BaseStock extends AliasableSqlTable<BaseStock> {
        public final SqlColumn<String> whsCd = column("WHS_CD", JDBCType.VARCHAR);

        public final SqlColumn<String> stgBinCd = column("STG_BIN_CD", JDBCType.VARCHAR);

        public final SqlColumn<String> itemCd = column("ITEM_CD", JDBCType.VARCHAR);

        public final SqlColumn<String> lotNo = column("LOT_NO", JDBCType.VARCHAR);

        public final SqlColumn<String> subLotNo = column("SUB_LOT_NO", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deptId = column("DEPT_ID", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> stdUnitQty = column("STD_UNIT_QTY", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> pkgUnitQty = column("PKG_UNIT_QTY", JDBCType.DECIMAL);

        public final SqlColumn<String> serialNo = column("SERIAL_NO", JDBCType.VARCHAR);

        public final SqlColumn<String> palletId = column("PALLET_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> parentPalletId = column("PARENT_PALLET_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> remark1 = column("REMARK_1", JDBCType.VARCHAR);

        public final SqlColumn<String> remark2 = column("REMARK_2", JDBCType.VARCHAR);

        public final SqlColumn<String> remark3 = column("REMARK_3", JDBCType.VARCHAR);

        public final SqlColumn<String> remark4 = column("REMARK_4", JDBCType.VARCHAR);

        public final SqlColumn<String> remark5 = column("REMARK_5", JDBCType.VARCHAR);

        public final SqlColumn<Integer> updateCount = column("UPDATE_COUNT", JDBCType.INTEGER);

        public final SqlColumn<Integer> deleteFlag = column("DELETE_FLAG", JDBCType.INTEGER);

        public final SqlColumn<String> createBy = column("create_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updateBy = column("update_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public BaseStock() {
            super("WMS_B_BASE_STOCK", BaseStock::new);
        }
    }
}