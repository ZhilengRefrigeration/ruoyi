package com.ruoyi.common.services.mapper;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class SysSeqRuleDynamicSqlSupport {
    public static final SysSeqRule sysSeqRule = new SysSeqRule();

    /**
     *   规则ID
     */
    public static final SqlColumn<Long> ruleId = sysSeqRule.ruleId;

    /**
     *   序列号识别码
     */
    public static final SqlColumn<String> seqDistCd = sysSeqRule.seqDistCd;

    /**
     *   规则名称
     */
    public static final SqlColumn<String> ruleName = sysSeqRule.ruleName;

    /**
     *   前缀
     */
    public static final SqlColumn<String> prefix = sysSeqRule.prefix;

    /**
     *   分隔符1
     */
    public static final SqlColumn<String> separator1 = sysSeqRule.separator1;

    /**
     *   日期格式
     */
    public static final SqlColumn<String> dateFormat = sysSeqRule.dateFormat;

    /**
     *   序列号数字部分的最小位数，不足补0
     */
    public static final SqlColumn<Integer> minDigits = sysSeqRule.minDigits;

    /**
     *   分隔符2
     */
    public static final SqlColumn<String> separator2 = sysSeqRule.separator2;

    /**
     *   生成器名称(或类全名)，自定义的生成器可忽略前面的规则自行生成
     */
    public static final SqlColumn<String> generatorName = sysSeqRule.generatorName;

    /**
     *   是否启用
     */
    public static final SqlColumn<Integer> enableFlag = sysSeqRule.enableFlag;

    /**
     *   备注1
     */
    public static final SqlColumn<String> remark1 = sysSeqRule.remark1;

    /**
     *   备注2
     */
    public static final SqlColumn<String> remark2 = sysSeqRule.remark2;

    /**
     *   备注3
     */
    public static final SqlColumn<String> remark3 = sysSeqRule.remark3;

    /**
     *   备注4
     */
    public static final SqlColumn<String> remark4 = sysSeqRule.remark4;

    /**
     *   备注5
     */
    public static final SqlColumn<String> remark5 = sysSeqRule.remark5;

    /**
     *   创建者
     */
    public static final SqlColumn<String> createBy = sysSeqRule.createBy;

    /**
     *   创建时间
     */
    public static final SqlColumn<Date> createTime = sysSeqRule.createTime;

    /**
     *   更新者
     */
    public static final SqlColumn<String> updateBy = sysSeqRule.updateBy;

    /**
     *   更新时间
     */
    public static final SqlColumn<Date> updateTime = sysSeqRule.updateTime;

    /**
     *   备注
     */
    public static final SqlColumn<String> remark = sysSeqRule.remark;

    public static final class SysSeqRule extends AliasableSqlTable<SysSeqRule> {
        public final SqlColumn<Long> ruleId = column("rule_id", JDBCType.BIGINT);

        public final SqlColumn<String> seqDistCd = column("seq_dist_cd", JDBCType.VARCHAR);

        public final SqlColumn<String> ruleName = column("rule_name", JDBCType.VARCHAR);

        public final SqlColumn<String> prefix = column("prefix", JDBCType.VARCHAR);

        public final SqlColumn<String> separator1 = column("separator1", JDBCType.VARCHAR);

        public final SqlColumn<String> dateFormat = column("date_format", JDBCType.VARCHAR);

        public final SqlColumn<Integer> minDigits = column("min_digits", JDBCType.INTEGER);

        public final SqlColumn<String> separator2 = column("separator2", JDBCType.VARCHAR);

        public final SqlColumn<String> generatorName = column("generator_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> enableFlag = column("enable_flag", JDBCType.INTEGER);

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

        public SysSeqRule() {
            super("sys_seq_rule", SysSeqRule::new);
        }
    }
}