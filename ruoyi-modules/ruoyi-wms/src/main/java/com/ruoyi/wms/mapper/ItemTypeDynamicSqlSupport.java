package com.ruoyi.wms.mapper;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import java.sql.JDBCType;
import java.util.Date;

public final class ItemTypeDynamicSqlSupport {
    public static final ItemType itemType = new ItemType();

    /**
     *   物品类型编码
     */
    public static final SqlColumn<String> itemTypeCd = itemType.itemTypeCd;

    /**
     *   从属部门ID
     */
    public static final SqlColumn<Integer> deptId = itemType.deptId;

    /**
     *   物品类型名称
     */
    public static final SqlColumn<String> itemTypeName = itemType.itemTypeName;

    /**
     *   备注1
     */
    public static final SqlColumn<String> remark1 = itemType.remark1;

    /**
     *   备注2
     */
    public static final SqlColumn<String> remark2 = itemType.remark2;

    /**
     *   备注3
     */
    public static final SqlColumn<String> remark3 = itemType.remark3;

    /**
     *   备注4
     */
    public static final SqlColumn<String> remark4 = itemType.remark4;

    /**
     *   备注5
     */
    public static final SqlColumn<String> remark5 = itemType.remark5;

    /**
     *   更新次数
     */
    public static final SqlColumn<Integer> updateCount = itemType.updateCount;

    /**
     *   更新次数
     */
    public static final SqlColumn<Integer> deleteFlag = itemType.deleteFlag;

    /**
     *   创建者
     */
    public static final SqlColumn<String> createBy = itemType.createBy;

    /**
     *   创建时间
     */
    public static final SqlColumn<Date> createTime = itemType.createTime;

    /**
     *   更新者
     */
    public static final SqlColumn<String> updateBy = itemType.updateBy;

    /**
     *   更新时间
     */
    public static final SqlColumn<Date> updateTime = itemType.updateTime;

    /**
     *   备注
     */
    public static final SqlColumn<String> remark = itemType.remark;

    public static final class ItemType extends AliasableSqlTable<ItemType> {
        public final SqlColumn<String> itemTypeCd = column("ITEM_TYPE_CD", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deptId = column("DEPT_ID", JDBCType.INTEGER);

        public final SqlColumn<String> itemTypeName = column("ITEM_TYPE_NAME", JDBCType.VARCHAR);

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

        public ItemType() {
            super("WMS_M_ITEM_TYPE", ItemType::new);
        }
    }
}