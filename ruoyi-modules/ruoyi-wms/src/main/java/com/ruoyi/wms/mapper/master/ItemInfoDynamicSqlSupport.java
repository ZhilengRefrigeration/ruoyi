package com.ruoyi.wms.mapper.master;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class ItemInfoDynamicSqlSupport {
    public static final ItemInfo itemInfo = new ItemInfo();

    /**
     *   物品代码
     */
    public static final SqlColumn<String> itemCd = itemInfo.itemCd;

    /**
     *   从属部门ID
     */
    public static final SqlColumn<Integer> deptId = itemInfo.deptId;

    /**
     *   物品名称
     */
    public static final SqlColumn<String> itemName = itemInfo.itemName;

    /**
     *   供应商
     */
    public static final SqlColumn<String> supplier = itemInfo.supplier;

    /**
     *   筹备提前期
     */
    public static final SqlColumn<BigDecimal> prepLeadTime = itemInfo.prepLeadTime;

    /**
     *   入库提前期
     */
    public static final SqlColumn<BigDecimal> instockLeadTime = itemInfo.instockLeadTime;

    /**
     *   放置期
     */
    public static final SqlColumn<BigDecimal> restingPeriod = itemInfo.restingPeriod;

    /**
     *   出库提前期
     */
    public static final SqlColumn<BigDecimal> outstockLeadTime = itemInfo.outstockLeadTime;

    /**
     *   规格1
     */
    public static final SqlColumn<String> spec1 = itemInfo.spec1;

    /**
     *   规格2
     */
    public static final SqlColumn<String> spec2 = itemInfo.spec2;

    /**
     *   规格3
     */
    public static final SqlColumn<String> spec3 = itemInfo.spec3;

    /**
     *   规格4
     */
    public static final SqlColumn<String> spec4 = itemInfo.spec4;

    /**
     *   规格5
     */
    public static final SqlColumn<String> spec5 = itemInfo.spec5;

    /**
     *   分类1
     */
    public static final SqlColumn<String> cls1 = itemInfo.cls1;

    /**
     *   分类2
     */
    public static final SqlColumn<String> cls2 = itemInfo.cls2;

    /**
     *   分类3
     */
    public static final SqlColumn<String> cls3 = itemInfo.cls3;

    /**
     *   分类4
     */
    public static final SqlColumn<String> cls4 = itemInfo.cls4;

    /**
     *   分类5
     */
    public static final SqlColumn<String> cls5 = itemInfo.cls5;

    /**
     *   更新次数
     */
    public static final SqlColumn<Integer> updateCount = itemInfo.updateCount;

    /**
     *   删除标志
     */
    public static final SqlColumn<Integer> deleteFlag = itemInfo.deleteFlag;

    /**
     *   创建者
     */
    public static final SqlColumn<String> createBy = itemInfo.createBy;

    /**
     *   创建时间
     */
    public static final SqlColumn<Date> createTime = itemInfo.createTime;

    /**
     *   更新者
     */
    public static final SqlColumn<String> updateBy = itemInfo.updateBy;

    /**
     *   更新时间
     */
    public static final SqlColumn<Date> updateTime = itemInfo.updateTime;

    /**
     *   备注
     */
    public static final SqlColumn<String> remark = itemInfo.remark;

    /**
     *   备注1
     */
    public static final SqlColumn<String> remark1 = itemInfo.remark1;

    /**
     *   备注2
     */
    public static final SqlColumn<String> remark2 = itemInfo.remark2;

    /**
     *   备注3
     */
    public static final SqlColumn<String> remark3 = itemInfo.remark3;

    /**
     *   备注4
     */
    public static final SqlColumn<String> remark4 = itemInfo.remark4;

    /**
     *   备注5
     */
    public static final SqlColumn<String> remark5 = itemInfo.remark5;

    /**
     *   供货周期
     */
    public static final SqlColumn<String> deliveryPeriod = itemInfo.deliveryPeriod;

    /**
     *   默认库位号
     */
    public static final SqlColumn<String> defaultStgBinCd = itemInfo.defaultStgBinCd;

    /**
     *   启用标志
     */
    public static final SqlColumn<Integer> enableFlg = itemInfo.enableFlg;

    /**
     *   安全库存量
     */
    public static final SqlColumn<BigDecimal> safetyStock = itemInfo.safetyStock;

    /**
     *   最大库存量
     */
    public static final SqlColumn<BigDecimal> maxInvQty = itemInfo.maxInvQty;

    /**
     *   发起购买的阈值
     */
    public static final SqlColumn<BigDecimal> purchLimitQty = itemInfo.purchLimitQty;

    /**
     *   物品区分
     */
    public static final SqlColumn<String> goodsCls = itemInfo.goodsCls;

    /**
     *   批号管理区分(0:不管理, 1:管理)
     */
    public static final SqlColumn<Integer> lotNoMgmtCls = itemInfo.lotNoMgmtCls;

    /**
     *   物品类型代码
     */
    public static final SqlColumn<String> itemTypeCd = itemInfo.itemTypeCd;

    /**
     *   标准单位代码
     */
    public static final SqlColumn<String> stdUnitCd = itemInfo.stdUnitCd;

    /**
     *   包装单位代码
     */
    public static final SqlColumn<String> pkgUnitCd = itemInfo.pkgUnitCd;

    /**
     *   出库申请最小数量
     */
    public static final SqlColumn<BigDecimal> outstockReqMinQty = itemInfo.outstockReqMinQty;

    /**
     *   出库单位区分
     */
    public static final SqlColumn<String> outstockUnitCls = itemInfo.outstockUnitCls;

    /**
     *   单位净重
     */
    public static final SqlColumn<BigDecimal> netWeightPerUnit = itemInfo.netWeightPerUnit;

    /**
     *   自身体积(M3)
     */
    public static final SqlColumn<BigDecimal> ownVolM3 = itemInfo.ownVolM3;

    /**
     *   尺寸(长)
     */
    public static final SqlColumn<BigDecimal> sizeD = itemInfo.sizeD;

    /**
     *   尺寸(宽)
     */
    public static final SqlColumn<BigDecimal> sizeW = itemInfo.sizeW;

    /**
     *   尺寸(高)
     */
    public static final SqlColumn<BigDecimal> sizeH = itemInfo.sizeH;

    /**
     *   包装要求描述
     */
    public static final SqlColumn<String> pkgRqmtDesc = itemInfo.pkgRqmtDesc;

    /**
     *   码放要求描述
     */
    public static final SqlColumn<String> stackingRqmtDesc = itemInfo.stackingRqmtDesc;

    /**
     *   储存要求描述
     */
    public static final SqlColumn<String> stgRqmtDesc = itemInfo.stgRqmtDesc;

    /**
     *   生产商
     */
    public static final SqlColumn<String> manufacturer = itemInfo.manufacturer;

    /**
     *   图片ID
     */
    public static final SqlColumn<String> pictureId = itemInfo.pictureId;

    /**
     *   图片URL
     */
    public static final SqlColumn<String> pictureUrl = itemInfo.pictureUrl;

    public static final class ItemInfo extends AliasableSqlTable<ItemInfo> {
        public final SqlColumn<String> itemCd = column("ITEM_CD", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deptId = column("DEPT_ID", JDBCType.INTEGER);

        public final SqlColumn<String> itemName = column("ITEM_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> supplier = column("SUPPLIER", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> prepLeadTime = column("PREP_LEAD_TIME", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> instockLeadTime = column("INSTOCK_LEAD_TIME", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> restingPeriod = column("RESTING_PERIOD", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> outstockLeadTime = column("OUTSTOCK_LEAD_TIME", JDBCType.DECIMAL);

        public final SqlColumn<String> spec1 = column("SPEC_1", JDBCType.VARCHAR);

        public final SqlColumn<String> spec2 = column("SPEC_2", JDBCType.VARCHAR);

        public final SqlColumn<String> spec3 = column("SPEC_3", JDBCType.VARCHAR);

        public final SqlColumn<String> spec4 = column("SPEC_4", JDBCType.VARCHAR);

        public final SqlColumn<String> spec5 = column("SPEC_5", JDBCType.VARCHAR);

        public final SqlColumn<String> cls1 = column("CLS_1", JDBCType.VARCHAR);

        public final SqlColumn<String> cls2 = column("CLS_2", JDBCType.VARCHAR);

        public final SqlColumn<String> cls3 = column("CLS_3", JDBCType.VARCHAR);

        public final SqlColumn<String> cls4 = column("CLS_4", JDBCType.VARCHAR);

        public final SqlColumn<String> cls5 = column("CLS_5", JDBCType.VARCHAR);

        public final SqlColumn<Integer> updateCount = column("UPDATE_COUNT", JDBCType.INTEGER);

        public final SqlColumn<Integer> deleteFlag = column("DELETE_FLAG", JDBCType.INTEGER);

        public final SqlColumn<String> createBy = column("create_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updateBy = column("update_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public final SqlColumn<String> remark1 = column("REMARK_1", JDBCType.VARCHAR);

        public final SqlColumn<String> remark2 = column("REMARK_2", JDBCType.VARCHAR);

        public final SqlColumn<String> remark3 = column("REMARK_3", JDBCType.VARCHAR);

        public final SqlColumn<String> remark4 = column("REMARK_4", JDBCType.VARCHAR);

        public final SqlColumn<String> remark5 = column("REMARK_5", JDBCType.VARCHAR);

        public final SqlColumn<String> deliveryPeriod = column("DELIVERY_PERIOD", JDBCType.VARCHAR);

        public final SqlColumn<String> defaultStgBinCd = column("DEFAULT_STG_BIN_CD", JDBCType.VARCHAR);

        public final SqlColumn<Integer> enableFlg = column("ENABLE_FLG", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> safetyStock = column("SAFETY_STOCK", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> maxInvQty = column("MAX_INV_QTY", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> purchLimitQty = column("PURCH_LIMIT_QTY", JDBCType.DECIMAL);

        public final SqlColumn<String> goodsCls = column("GOODS_CLS", JDBCType.VARCHAR);

        public final SqlColumn<Integer> lotNoMgmtCls = column("LOT_NO_MGMT_CLS", JDBCType.INTEGER);

        public final SqlColumn<String> itemTypeCd = column("ITEM_TYPE_CD", JDBCType.VARCHAR);

        public final SqlColumn<String> stdUnitCd = column("STD_UNIT_CD", JDBCType.VARCHAR);

        public final SqlColumn<String> pkgUnitCd = column("PKG_UNIT_CD", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> outstockReqMinQty = column("OUTSTOCK_REQ_MIN_QTY", JDBCType.DECIMAL);

        public final SqlColumn<String> outstockUnitCls = column("OUTSTOCK_UNIT_CLS", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> netWeightPerUnit = column("NET_WEIGHT_PER_UNIT", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> ownVolM3 = column("OWN_VOL_M3", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> sizeD = column("SIZE_D", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> sizeW = column("SIZE_W", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> sizeH = column("SIZE_H", JDBCType.DECIMAL);

        public final SqlColumn<String> pkgRqmtDesc = column("PKG_RQMT_DESC", JDBCType.VARCHAR);

        public final SqlColumn<String> stackingRqmtDesc = column("STACKING_RQMT_DESC", JDBCType.VARCHAR);

        public final SqlColumn<String> stgRqmtDesc = column("STG_RQMT_DESC", JDBCType.VARCHAR);

        public final SqlColumn<String> manufacturer = column("MANUFACTURER", JDBCType.VARCHAR);

        public final SqlColumn<String> pictureId = column("PICTURE_ID", JDBCType.VARCHAR);

        public final SqlColumn<String> pictureUrl = column("PICTURE_URL", JDBCType.VARCHAR);

        public ItemInfo() {
            super("WMS_M_ITEM_INFO", ItemInfo::new);
        }
    }
}