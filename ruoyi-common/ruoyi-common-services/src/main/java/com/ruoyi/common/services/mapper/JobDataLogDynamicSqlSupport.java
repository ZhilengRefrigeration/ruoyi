package com.ruoyi.common.services.mapper;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import java.sql.JDBCType;
import java.util.Date;

public final class JobDataLogDynamicSqlSupport {
    public static final JobDataLog jobDataLog = new JobDataLog();

    /**
     *   任务数据日志ID
     */
    public static final SqlColumn<Long> logId = jobDataLog.logId;

    /**
     *   执行开始时间
     */
    public static final SqlColumn<Date> startTime = jobDataLog.startTime;

    /**
     *   执行结束时间
     */
    public static final SqlColumn<Date> endTime = jobDataLog.endTime;

    /**
     *   日志内容
     */
    public static final SqlColumn<String> message = jobDataLog.message;

    /**
     *   日志类型
     */
    public static final SqlColumn<String> logType = jobDataLog.logType;

    /**
     *   状态（0正常执行 1异常）
     */
    public static final SqlColumn<String> status = jobDataLog.status;

    /**
     *   任务执行类
     */
    public static final SqlColumn<String> jobClass = jobDataLog.jobClass;

    /**
     *   任务执行方法名
     */
    public static final SqlColumn<String> jobMethod = jobDataLog.jobMethod;

    /**
     *   创建者
     */
    public static final SqlColumn<String> createBy = jobDataLog.createBy;

    /**
     *   创建时间
     */
    public static final SqlColumn<Date> createTime = jobDataLog.createTime;

    /**
     *   更新者
     */
    public static final SqlColumn<String> updateBy = jobDataLog.updateBy;

    /**
     *   更新时间
     */
    public static final SqlColumn<Date> updateTime = jobDataLog.updateTime;

    /**
     *   备注信息
     */
    public static final SqlColumn<String> remark = jobDataLog.remark;

    public static final class JobDataLog extends AliasableSqlTable<JobDataLog> {
        public final SqlColumn<Long> logId = column("log_id", JDBCType.BIGINT);

        public final SqlColumn<Date> startTime = column("start_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> endTime = column("end_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> message = column("message", JDBCType.VARCHAR);

        public final SqlColumn<String> logType = column("log_type", JDBCType.CHAR);

        public final SqlColumn<String> status = column("status", JDBCType.CHAR);

        public final SqlColumn<String> jobClass = column("job_class", JDBCType.VARCHAR);

        public final SqlColumn<String> jobMethod = column("job_method", JDBCType.VARCHAR);

        public final SqlColumn<String> createBy = column("create_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updateBy = column("update_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public JobDataLog() {
            super("sys_job_data_log", JobDataLog::new);
        }
    }
}