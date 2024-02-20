package com.ruoyi.common.services.mapper;

import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.common.services.domain.JobDataLog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.ruoyi.common.services.mapper.JobDataLogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface JobDataLogMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<JobDataLog>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(logId, startTime, endTime, message, logType, status, jobClass, jobMethod, createBy, createTime, updateBy, updateTime, remark);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="JobDataLogResult", value = {
        @Result(column="log_id", property="logId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="message", property="message", jdbcType=JdbcType.VARCHAR),
        @Result(column="log_type", property="logType", jdbcType=JdbcType.CHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
        @Result(column="job_class", property="jobClass", jdbcType=JdbcType.VARCHAR),
        @Result(column="job_method", property="jobMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<JobDataLog> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("JobDataLogResult")
    Optional<JobDataLog> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, jobDataLog, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, jobDataLog, completer);
    }

    default int deleteByPrimaryKey(Long logId_) {
        return delete(c -> 
            c.where(logId, isEqualTo(logId_))
        );
    }

    default int insert(JobDataLog row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, jobDataLog, c ->
            c.map(logId).toProperty("logId")
            .map(startTime).toProperty("startTime")
            .map(endTime).toProperty("endTime")
            .map(message).toProperty("message")
            .map(logType).toProperty("logType")
            .map(status).toProperty("status")
            .map(jobClass).toProperty("jobClass")
            .map(jobMethod).toProperty("jobMethod")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(remark).toProperty("remark")
        );
    }

    default int insertMultiple(Collection<JobDataLog> records) {
        for (JobDataLog row : records) {
            row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        }
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, jobDataLog, c ->
            c.map(logId).toProperty("logId")
            .map(startTime).toProperty("startTime")
            .map(endTime).toProperty("endTime")
            .map(message).toProperty("message")
            .map(logType).toProperty("logType")
            .map(status).toProperty("status")
            .map(jobClass).toProperty("jobClass")
            .map(jobMethod).toProperty("jobMethod")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(remark).toProperty("remark")
        );
    }

    default int insertSelective(JobDataLog row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, jobDataLog, c ->
            c.map(logId).toPropertyWhenPresent("logId", row::getLogId)
            .map(startTime).toPropertyWhenPresent("startTime", row::getStartTime)
            .map(endTime).toPropertyWhenPresent("endTime", row::getEndTime)
            .map(message).toPropertyWhenPresent("message", row::getMessage)
            .map(logType).toPropertyWhenPresent("logType", row::getLogType)
            .map(status).toPropertyWhenPresent("status", row::getStatus)
            .map(jobClass).toPropertyWhenPresent("jobClass", row::getJobClass)
            .map(jobMethod).toPropertyWhenPresent("jobMethod", row::getJobMethod)
            .map(createBy).toPropertyWhenPresent("createBy", row::getCreateBy)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(updateBy).toPropertyWhenPresent("updateBy", row::getUpdateBy)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
            .map(remark).toPropertyWhenPresent("remark", row::getRemark)
        );
    }

    default Optional<JobDataLog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, jobDataLog, completer);
    }

    default List<JobDataLog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, jobDataLog, completer);
    }

    default List<JobDataLog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, jobDataLog, completer);
    }

    default Optional<JobDataLog> selectByPrimaryKey(Long logId_) {
        return selectOne(c ->
            c.where(logId, isEqualTo(logId_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, jobDataLog, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(JobDataLog row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(logId).equalTo(row::getLogId)
                .set(startTime).equalTo(row::getStartTime)
                .set(endTime).equalTo(row::getEndTime)
                .set(message).equalTo(row::getMessage)
                .set(logType).equalTo(row::getLogType)
                .set(status).equalTo(row::getStatus)
                .set(jobClass).equalTo(row::getJobClass)
                .set(jobMethod).equalTo(row::getJobMethod)
                .set(createBy).equalTo(row::getCreateBy)
                .set(createTime).equalTo(row::getCreateTime)
                .set(updateBy).equalTo(row::getUpdateBy)
                .set(updateTime).equalTo(row::getUpdateTime)
                .set(remark).equalTo(row::getRemark);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(JobDataLog row, UpdateDSL<UpdateModel> dsl) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return dsl.set(logId).equalToWhenPresent(row::getLogId)
                .set(startTime).equalToWhenPresent(row::getStartTime)
                .set(endTime).equalToWhenPresent(row::getEndTime)
                .set(message).equalToWhenPresent(row::getMessage)
                .set(logType).equalToWhenPresent(row::getLogType)
                .set(status).equalToWhenPresent(row::getStatus)
                .set(jobClass).equalToWhenPresent(row::getJobClass)
                .set(jobMethod).equalToWhenPresent(row::getJobMethod)
                .set(createBy).equalToWhenPresent(row::getCreateBy)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(updateBy).equalToWhenPresent(row::getUpdateBy)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime)
                .set(remark).equalToWhenPresent(row::getRemark);
    }

    default int updateByPrimaryKey(JobDataLog row) {
        return update(c ->
            c.set(startTime).equalTo(row::getStartTime)
            .set(endTime).equalTo(row::getEndTime)
            .set(message).equalTo(row::getMessage)
            .set(logType).equalTo(row::getLogType)
            .set(status).equalTo(row::getStatus)
            .set(jobClass).equalTo(row::getJobClass)
            .set(jobMethod).equalTo(row::getJobMethod)
            .set(createBy).equalTo(row::getCreateBy)
            .set(createTime).equalTo(row::getCreateTime)
            .set(updateBy).equalTo(row::getUpdateBy)
            .set(updateTime).equalTo(row::getUpdateTime)
            .set(remark).equalTo(row::getRemark)
            .where(logId, isEqualTo(row::getLogId))
        );
    }

    default int updateByPrimaryKeySelective(JobDataLog row) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return update(c ->
            c.set(startTime).equalToWhenPresent(row::getStartTime)
            .set(endTime).equalToWhenPresent(row::getEndTime)
            .set(message).equalToWhenPresent(row::getMessage)
            .set(logType).equalToWhenPresent(row::getLogType)
            .set(status).equalToWhenPresent(row::getStatus)
            .set(jobClass).equalToWhenPresent(row::getJobClass)
            .set(jobMethod).equalToWhenPresent(row::getJobMethod)
            .set(createBy).equalToWhenPresent(row::getCreateBy)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(updateBy).equalToWhenPresent(row::getUpdateBy)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .set(remark).equalToWhenPresent(row::getRemark)
            .where(logId, isEqualTo(row::getLogId))
        );
    }
}