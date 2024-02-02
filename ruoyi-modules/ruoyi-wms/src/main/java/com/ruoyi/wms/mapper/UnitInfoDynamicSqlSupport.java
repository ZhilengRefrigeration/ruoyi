package com.ruoyi.wms.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class UnitInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    public static final UnitInfo unitInfo = new UnitInfo();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.ORG_CD")
    public static final SqlColumn<String> orgCd = unitInfo.orgCd;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.UNIT")
    public static final SqlColumn<String> unit = unitInfo.unit;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.UNIT_NAME")
    public static final SqlColumn<String> unitName = unitInfo.unitName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.UNIT_CONV_RATE")
    public static final SqlColumn<String> unitConvRate = unitInfo.unitConvRate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.SRC_CONV_UNIT")
    public static final SqlColumn<String> srcConvUnit = unitInfo.srcConvUnit;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.REMARK_1")
    public static final SqlColumn<String> remark1 = unitInfo.remark1;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.REMARK_2")
    public static final SqlColumn<String> remark2 = unitInfo.remark2;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.REMARK_3")
    public static final SqlColumn<String> remark3 = unitInfo.remark3;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.REMARK_4")
    public static final SqlColumn<String> remark4 = unitInfo.remark4;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.REMARK_5")
    public static final SqlColumn<String> remark5 = unitInfo.remark5;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.UPDATE_COUNT")
    public static final SqlColumn<Integer> updateCount = unitInfo.updateCount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.DELETE_FLAG")
    public static final SqlColumn<Integer> deleteFlag = unitInfo.deleteFlag;

    /**
     * Database Column Remarks:
     *   创建者
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.create_by")
    public static final SqlColumn<String> createBy = unitInfo.createBy;

    /**
     * Database Column Remarks:
     *   创建时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.create_time")
    public static final SqlColumn<Date> createTime = unitInfo.createTime;

    /**
     * Database Column Remarks:
     *   更新者
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.update_by")
    public static final SqlColumn<String> updateBy = unitInfo.updateBy;

    /**
     * Database Column Remarks:
     *   更新时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.update_time")
    public static final SqlColumn<Date> updateTime = unitInfo.updateTime;

    /**
     * Database Column Remarks:
     *   备注
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: SF_WMS_M_UNIT_INFO.remark")
    public static final SqlColumn<String> remark = unitInfo.remark;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    public static final class UnitInfo extends AliasableSqlTable<UnitInfo> {
        public final SqlColumn<String> orgCd = column("ORG_CD", JDBCType.VARCHAR);

        public final SqlColumn<String> unit = column("UNIT", JDBCType.VARCHAR);

        public final SqlColumn<String> unitName = column("UNIT_NAME", JDBCType.VARCHAR);

        public final SqlColumn<String> unitConvRate = column("UNIT_CONV_RATE", JDBCType.VARCHAR);

        public final SqlColumn<String> srcConvUnit = column("SRC_CONV_UNIT", JDBCType.VARCHAR);

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

        public UnitInfo() {
            super("SF_WMS_M_UNIT_INFO", UnitInfo::new);
        }
    }
}