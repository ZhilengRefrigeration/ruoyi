package com.ruoyi.wms.mapper.master;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import java.sql.JDBCType;
import java.util.Date;

public final class UnitInfoDynamicSqlSupport {
    public static final UnitInfo unitInfo = new UnitInfo();

    /**
     *   单位代码
     */
    public static final SqlColumn<String> unitCode = unitInfo.unitCode;

    /**
     *   从属部门ID
     */
    public static final SqlColumn<Integer> deptId = unitInfo.deptId;

    /**
     *   单位名称
     */
    public static final SqlColumn<String> unitName = unitInfo.unitName;

    /**
     *   备注1
     */
    public static final SqlColumn<String> remark1 = unitInfo.remark1;

    /**
     *   备注2
     */
    public static final SqlColumn<String> remark2 = unitInfo.remark2;

    /**
     *   备注3
     */
    public static final SqlColumn<String> remark3 = unitInfo.remark3;

    /**
     *   备注4
     */
    public static final SqlColumn<String> remark4 = unitInfo.remark4;

    /**
     *   备注5
     */
    public static final SqlColumn<String> remark5 = unitInfo.remark5;

    /**
     *   更新次数
     */
    public static final SqlColumn<Integer> updateCount = unitInfo.updateCount;

    /**
     *   删除标志
     */
    public static final SqlColumn<Integer> deleteFlag = unitInfo.deleteFlag;

    /**
     *   创建者
     */
    public static final SqlColumn<String> createBy = unitInfo.createBy;

    /**
     *   创建时间
     */
    public static final SqlColumn<Date> createTime = unitInfo.createTime;

    /**
     *   更新者
     */
    public static final SqlColumn<String> updateBy = unitInfo.updateBy;

    /**
     *   更新时间
     */
    public static final SqlColumn<Date> updateTime = unitInfo.updateTime;

    /**
     *   备注
     */
    public static final SqlColumn<String> remark = unitInfo.remark;

    public static final class UnitInfo extends AliasableSqlTable<UnitInfo> {
        public final SqlColumn<String> unitCode = column("UNIT_CODE", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deptId = column("DEPT_ID", JDBCType.INTEGER);

        public final SqlColumn<String> unitName = column("UNIT_NAME", JDBCType.VARCHAR);

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
            super("WMS_M_UNIT_INFO", UnitInfo::new);
        }
    }
}