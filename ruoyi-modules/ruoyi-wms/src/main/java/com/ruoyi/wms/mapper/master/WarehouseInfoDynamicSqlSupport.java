package com.ruoyi.wms.mapper.master;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class WarehouseInfoDynamicSqlSupport {
    public static final WarehouseInfo warehouseInfo = new WarehouseInfo();

    /**
     *   仓库代码
     */
    public static final SqlColumn<String> whsCd = warehouseInfo.whsCd;

    /**
     *   从属部门ID
     */
    public static final SqlColumn<Integer> deptId = warehouseInfo.deptId;

    /**
     *   仓库名称
     */
    public static final SqlColumn<String> whsName = warehouseInfo.whsName;

    /**
     *   仓库类型代码
     */
    public static final SqlColumn<String> whsTypeCd = warehouseInfo.whsTypeCd;

    /**
     *   缩写
     */
    public static final SqlColumn<String> abbr = warehouseInfo.abbr;

    /**
     *   地址1
     */
    public static final SqlColumn<String> addr1 = warehouseInfo.addr1;

    /**
     *   地址2
     */
    public static final SqlColumn<String> addr2 = warehouseInfo.addr2;

    /**
     *   地址3
     */
    public static final SqlColumn<String> addr3 = warehouseInfo.addr3;

    /**
     *   联系电话
     */
    public static final SqlColumn<String> phoneNo = warehouseInfo.phoneNo;

    /**
     *   邮编
     */
    public static final SqlColumn<String> zipCd = warehouseInfo.zipCd;

    /**
     *   联系邮箱
     */
    public static final SqlColumn<String> email = warehouseInfo.email;

    /**
     *   传真号
     */
    public static final SqlColumn<String> faxNo = warehouseInfo.faxNo;

    /**
     *   负责人
     */
    public static final SqlColumn<String> respPerson = warehouseInfo.respPerson;

    /**
     *   区域
     */
    public static final SqlColumn<String> area = warehouseInfo.area;

    /**
     *   租赁费用
     */
    public static final SqlColumn<BigDecimal> rentalFee = warehouseInfo.rentalFee;

    /**
     *   存储费用
     */
    public static final SqlColumn<BigDecimal> storingFee = warehouseInfo.storingFee;

    public static final SqlColumn<String> remark1 = warehouseInfo.remark1;

    public static final SqlColumn<String> remark2 = warehouseInfo.remark2;

    public static final SqlColumn<String> remark3 = warehouseInfo.remark3;

    public static final SqlColumn<String> remark4 = warehouseInfo.remark4;

    public static final SqlColumn<String> remark5 = warehouseInfo.remark5;

    /**
     *   更新次数
     */
    public static final SqlColumn<Integer> updateCount = warehouseInfo.updateCount;

    /**
     *   删除标志
     */
    public static final SqlColumn<Integer> deleteFlag = warehouseInfo.deleteFlag;

    /**
     *   创建者
     */
    public static final SqlColumn<String> createBy = warehouseInfo.createBy;

    /**
     *   创建时间
     */
    public static final SqlColumn<Date> createTime = warehouseInfo.createTime;

    /**
     *   更新者
     */
    public static final SqlColumn<String> updateBy = warehouseInfo.updateBy;

    /**
     *   更新时间
     */
    public static final SqlColumn<Date> updateTime = warehouseInfo.updateTime;

    /**
     *   备注
     */
    public static final SqlColumn<String> remark = warehouseInfo.remark;

    public static final class WarehouseInfo extends AliasableSqlTable<WarehouseInfo> {
        public final SqlColumn<String> whsCd = column("WHS_CD", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deptId = column("DEPT_ID", JDBCType.INTEGER);

        public final SqlColumn<String> whsName = column("WHS_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> whsTypeCd = column("WHS_TYPE_CD", JDBCType.VARCHAR);

        public final SqlColumn<String> abbr = column("ABBR", JDBCType.VARCHAR);

        public final SqlColumn<String> addr1 = column("ADDR_1", JDBCType.VARCHAR);

        public final SqlColumn<String> addr2 = column("ADDR_2", JDBCType.VARCHAR);

        public final SqlColumn<String> addr3 = column("ADDR_3", JDBCType.VARCHAR);

        public final SqlColumn<String> phoneNo = column("PHONE_NO", JDBCType.VARCHAR);

        public final SqlColumn<String> zipCd = column("ZIP_CD", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("EMAIL", JDBCType.VARCHAR);

        public final SqlColumn<String> faxNo = column("FAX_NO", JDBCType.VARCHAR);

        public final SqlColumn<String> respPerson = column("RESP_PERSON", JDBCType.VARCHAR);

        public final SqlColumn<String> area = column("AREA", JDBCType.VARCHAR);

        public final SqlColumn<BigDecimal> rentalFee = column("RENTAL_FEE", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> storingFee = column("STORING_FEE", JDBCType.DECIMAL);

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

        public WarehouseInfo() {
            super("WMS_M_WAREHOUSE_INFO", WarehouseInfo::new);
        }
    }
}