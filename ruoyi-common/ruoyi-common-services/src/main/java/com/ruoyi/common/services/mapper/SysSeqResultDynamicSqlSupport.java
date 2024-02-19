package com.ruoyi.common.services.mapper;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class SysSeqResultDynamicSqlSupport {
    public static final SysSeqResult sysSeqResult = new SysSeqResult();

    /**
     *   序列号ID
     */
    public static final SqlColumn<Long> seqId = sysSeqResult.seqId;

    /**
     *   序列号识别码
     */
    public static final SqlColumn<String> seqDistCd = sysSeqResult.seqDistCd;

    /**
     *   前缀
     */
    public static final SqlColumn<String> prefix = sysSeqResult.prefix;

    /**
     *   分隔符1
     */
    public static final SqlColumn<String> separator1 = sysSeqResult.separator1;

    /**
     *   日期值
     */
    public static final SqlColumn<String> dateVal = sysSeqResult.dateVal;

    /**
     *   分隔符2
     */
    public static final SqlColumn<String> separator2 = sysSeqResult.separator2;

    /**
     *   当前序列号
     */
    public static final SqlColumn<Integer> seqNo = sysSeqResult.seqNo;

    /**
     *   备注1
     */
    public static final SqlColumn<String> remark1 = sysSeqResult.remark1;

    /**
     *   备注2
     */
    public static final SqlColumn<String> remark2 = sysSeqResult.remark2;

    /**
     *   备注3
     */
    public static final SqlColumn<String> remark3 = sysSeqResult.remark3;

    /**
     *   备注4
     */
    public static final SqlColumn<String> remark4 = sysSeqResult.remark4;

    /**
     *   备注5
     */
    public static final SqlColumn<String> remark5 = sysSeqResult.remark5;

    /**
     *   创建者
     */
    public static final SqlColumn<String> createBy = sysSeqResult.createBy;

    /**
     *   创建时间
     */
    public static final SqlColumn<Date> createTime = sysSeqResult.createTime;

    /**
     *   更新者
     */
    public static final SqlColumn<String> updateBy = sysSeqResult.updateBy;

    /**
     *   更新时间
     */
    public static final SqlColumn<Date> updateTime = sysSeqResult.updateTime;

    /**
     *   备注
     */
    public static final SqlColumn<String> remark = sysSeqResult.remark;

    public static final class SysSeqResult extends AliasableSqlTable<SysSeqResult> {
        public final SqlColumn<Long> seqId = column("seq_id", JDBCType.BIGINT);

        public final SqlColumn<String> seqDistCd = column("seq_dist_cd", JDBCType.VARCHAR);

        public final SqlColumn<String> prefix = column("prefix", JDBCType.VARCHAR);

        public final SqlColumn<String> separator1 = column("separator1", JDBCType.VARCHAR);

        public final SqlColumn<String> dateVal = column("date_val", JDBCType.VARCHAR);

        public final SqlColumn<String> separator2 = column("separator2", JDBCType.VARCHAR);

        public final SqlColumn<Integer> seqNo = column("seq_no", JDBCType.INTEGER);

        public final SqlColumn<String> remark1 = column("remark_1", JDBCType.VARCHAR);

        public final SqlColumn<String> remark2 = column("remark_2", JDBCType.VARCHAR);

        public final SqlColumn<String> remark3 = column("remark_3", JDBCType.VARCHAR);

        public final SqlColumn<String> remark4 = column("remark_4", JDBCType.VARCHAR);

        public final SqlColumn<String> remark5 = column("remark_5", JDBCType.VARCHAR);

        public final SqlColumn<String> createBy = column("create_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updateBy = column("update_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public SysSeqResult() {
            super("sys_seq_result", SysSeqResult::new);
        }
    }
}