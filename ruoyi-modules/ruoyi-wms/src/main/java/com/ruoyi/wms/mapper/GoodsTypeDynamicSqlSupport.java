package com.ruoyi.wms.mapper;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class GoodsTypeDynamicSqlSupport {
    public static final GoodsType goodsType = new GoodsType();

    /**
     *   物品类型编码
     */
    public static final SqlColumn<String> goodsTypeCd = goodsType.goodsTypeCd;

    /**
     *   从属部门ID
     */
    public static final SqlColumn<Integer> deptId = goodsType.deptId;

    /**
     *   物品类型名称
     */
    public static final SqlColumn<String> goodsTypeName = goodsType.goodsTypeName;

    /**
     *   备注1
     */
    public static final SqlColumn<String> remark1 = goodsType.remark1;

    /**
     *   备注2
     */
    public static final SqlColumn<String> remark2 = goodsType.remark2;

    /**
     *   备注3
     */
    public static final SqlColumn<String> remark3 = goodsType.remark3;

    /**
     *   备注4
     */
    public static final SqlColumn<String> remark4 = goodsType.remark4;

    /**
     *   备注5
     */
    public static final SqlColumn<String> remark5 = goodsType.remark5;

    /**
     *   更新次数
     */
    public static final SqlColumn<Integer> updateCount = goodsType.updateCount;

    /**
     *   更新次数
     */
    public static final SqlColumn<Integer> deleteFlag = goodsType.deleteFlag;

    /**
     *   创建者
     */
    public static final SqlColumn<String> createBy = goodsType.createBy;

    /**
     *   创建时间
     */
    public static final SqlColumn<Date> createTime = goodsType.createTime;

    /**
     *   更新者
     */
    public static final SqlColumn<String> updateBy = goodsType.updateBy;

    /**
     *   更新时间
     */
    public static final SqlColumn<Date> updateTime = goodsType.updateTime;

    /**
     *   备注
     */
    public static final SqlColumn<String> remark = goodsType.remark;

    public static final class GoodsType extends AliasableSqlTable<GoodsType> {
        public final SqlColumn<String> goodsTypeCd = column("GOODS_TYPE_CD", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deptId = column("DEPT_ID", JDBCType.INTEGER);

        public final SqlColumn<String> goodsTypeName = column("GOODS_TYPE_NAME", JDBCType.VARCHAR);

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

        public GoodsType() {
            super("WMS_M_GOODS_TYPE_INFO", GoodsType::new);
        }
    }
}