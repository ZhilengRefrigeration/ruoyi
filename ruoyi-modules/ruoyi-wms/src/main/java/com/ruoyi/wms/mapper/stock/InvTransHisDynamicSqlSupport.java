package com.ruoyi.wms.mapper.stock;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class InvTransHisDynamicSqlSupport {
    public static final InvTransHis invTransHis = new InvTransHis();

    /**
     *   入出库履历号
     */
    public static final SqlColumn<String> invTransNo = invTransHis.invTransNo;

    /**
     *   从属部门ID
     */
    public static final SqlColumn<Integer> deptId = invTransHis.deptId;

    /**
     *   入出库类型(1:入库,2:出库)
     */
    public static final SqlColumn<Integer> invTransType = invTransHis.invTransType;

    /**
     *   仓库代码
     */
    public static final SqlColumn<String> whsCd = invTransHis.whsCd;

    /**
     *   货架号
     */
    public static final SqlColumn<String> stgBinCd = invTransHis.stgBinCd;

    /**
     *   托盘ID
     */
    public static final SqlColumn<String> palletId = invTransHis.palletId;

    /**
     *   标准单位数量
     */
    public static final SqlColumn<BigDecimal> stdUnitQty = invTransHis.stdUnitQty;

    /**
     *   包装单位数量
     */
    public static final SqlColumn<BigDecimal> pkgUnitQty = invTransHis.pkgUnitQty;

    /**
     *   交易单号
     */
    public static final SqlColumn<String> transOrderNo = invTransHis.transOrderNo;

    /**
     *   交易单明细号
     */
    public static final SqlColumn<String> transOrderDetlNo = invTransHis.transOrderDetlNo;

    /**
     *   操作员
     */
    public static final SqlColumn<String> operator = invTransHis.operator;

    /**
     *   业务区分
     */
    public static final SqlColumn<String> businessCls = invTransHis.businessCls;

    /**
     *   物品代码
     */
    public static final SqlColumn<String> itemCd = invTransHis.itemCd;

    /**
     *   批号
     */
    public static final SqlColumn<String> lotNo = invTransHis.lotNo;

    /**
     *   子批号
     */
    public static final SqlColumn<String> subLotNo = invTransHis.subLotNo;

    /**
     *   序列号
     */
    public static final SqlColumn<String> serialNo = invTransHis.serialNo;

    /**
     *   入出库理由
     */
    public static final SqlColumn<String> reason = invTransHis.reason;

    /**
     *   备注1
     */
    public static final SqlColumn<String> remark1 = invTransHis.remark1;

    /**
     *   备注2
     */
    public static final SqlColumn<String> remark2 = invTransHis.remark2;

    /**
     *   备注3
     */
    public static final SqlColumn<String> remark3 = invTransHis.remark3;

    /**
     *   备注4
     */
    public static final SqlColumn<String> remark4 = invTransHis.remark4;

    /**
     *   备注5
     */
    public static final SqlColumn<String> remark5 = invTransHis.remark5;

    /**
     *   更新次数
     */
    public static final SqlColumn<Integer> updateCount = invTransHis.updateCount;

    /**
     *   删除标志
     */
    public static final SqlColumn<Integer> deleteFlag = invTransHis.deleteFlag;

    /**
     *   创建者
     */
    public static final SqlColumn<String> createBy = invTransHis.createBy;

    /**
     *   创建时间
     */
    public static final SqlColumn<Date> createTime = invTransHis.createTime;

    /**
     *   更新者
     */
    public static final SqlColumn<String> updateBy = invTransHis.updateBy;

    /**
     *   更新时间
     */
    public static final SqlColumn<Date> updateTime = invTransHis.updateTime;

    /**
     *   备注
     */
    public static final SqlColumn<String> remark = invTransHis.remark;

    public static final class InvTransHis extends AliasableSqlTable<InvTransHis> {
        public final SqlColumn<String> invTransNo = column("INV_TRANS_NO", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deptId = column("DEPT_ID", JDBCType.INTEGER);

        public final SqlColumn<Integer> invTransType = column("INV_TRANS_TYPE", JDBCType.INTEGER);

        public final SqlColumn<String> whsCd = column("WHS_CD", JDBCType.VARCHAR);

        public final SqlColumn<String> stgBinCd = column("STG_BIN_CD", JDBCType.VARCHAR);

        public final SqlColumn<String> palletId = column("PALLET_ID", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> stdUnitQty = column("STD_UNIT_QTY", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> pkgUnitQty = column("PKG_UNIT_QTY", JDBCType.DECIMAL);

        public final SqlColumn<String> transOrderNo = column("TRANS_ORDER_NO", JDBCType.VARCHAR);

        public final SqlColumn<String> transOrderDetlNo = column("TRANS_ORDER_DETL_NO", JDBCType.VARCHAR);

        public final SqlColumn<String> operator = column("OPERATOR", JDBCType.VARCHAR);

        public final SqlColumn<String> businessCls = column("BUSINESS_CLS", JDBCType.VARCHAR);

        public final SqlColumn<String> itemCd = column("ITEM_CD", JDBCType.VARCHAR);

        public final SqlColumn<String> lotNo = column("LOT_NO", JDBCType.VARCHAR);

        public final SqlColumn<String> subLotNo = column("SUB_LOT_NO", JDBCType.VARCHAR);

        public final SqlColumn<String> serialNo = column("SERIAL_NO", JDBCType.VARCHAR);

        public final SqlColumn<String> reason = column("REASON", JDBCType.VARCHAR);

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

        public InvTransHis() {
            super("WMS_B_INV_TRANS_HIS", InvTransHis::new);
        }
    }
}