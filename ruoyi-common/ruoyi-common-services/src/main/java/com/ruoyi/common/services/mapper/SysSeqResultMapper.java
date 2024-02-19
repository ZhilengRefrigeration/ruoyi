package com.ruoyi.common.services.mapper;

import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.common.services.domain.SysSeqResult;
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

import static com.ruoyi.common.services.mapper.SysSeqResultDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface SysSeqResultMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<SysSeqResult>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(seqId, seqDistCd, prefix, separator1, dateVal, separator2, seqNo, remark1, remark2, remark3, remark4, remark5, createBy, createTime, updateBy, updateTime, remark);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysSeqResultResult", value = {
        @Result(column="seq_id", property="seqId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="seq_dist_cd", property="seqDistCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="prefix", property="prefix", jdbcType=JdbcType.VARCHAR),
        @Result(column="separator1", property="separator1", jdbcType=JdbcType.VARCHAR),
        @Result(column="date_val", property="dateVal", jdbcType=JdbcType.VARCHAR),
        @Result(column="separator2", property="separator2", jdbcType=JdbcType.VARCHAR),
        @Result(column="seq_no", property="seqNo", jdbcType=JdbcType.INTEGER),
        @Result(column="remark_1", property="remark1", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark_2", property="remark2", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark_3", property="remark3", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark_4", property="remark4", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark_5", property="remark5", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<SysSeqResult> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysSeqResultResult")
    Optional<SysSeqResult> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysSeqResult, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysSeqResult, completer);
    }

    default int deleteByPrimaryKey(Long seqId_) {
        return delete(c -> 
            c.where(seqId, isEqualTo(seqId_))
        );
    }

    default int insert(SysSeqResult row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, sysSeqResult, c ->
            c.map(seqId).toProperty("seqId")
            .map(seqDistCd).toProperty("seqDistCd")
            .map(prefix).toProperty("prefix")
            .map(separator1).toProperty("separator1")
            .map(dateVal).toProperty("dateVal")
            .map(separator2).toProperty("separator2")
            .map(seqNo).toProperty("seqNo")
            .map(remark1).toProperty("remark1")
            .map(remark2).toProperty("remark2")
            .map(remark3).toProperty("remark3")
            .map(remark4).toProperty("remark4")
            .map(remark5).toProperty("remark5")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(remark).toProperty("remark")
        );
    }

    default int insertMultiple(Collection<SysSeqResult> records) {
        for (SysSeqResult row : records) {
            row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        }
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, sysSeqResult, c ->
            c.map(seqId).toProperty("seqId")
            .map(seqDistCd).toProperty("seqDistCd")
            .map(prefix).toProperty("prefix")
            .map(separator1).toProperty("separator1")
            .map(dateVal).toProperty("dateVal")
            .map(separator2).toProperty("separator2")
            .map(seqNo).toProperty("seqNo")
            .map(remark1).toProperty("remark1")
            .map(remark2).toProperty("remark2")
            .map(remark3).toProperty("remark3")
            .map(remark4).toProperty("remark4")
            .map(remark5).toProperty("remark5")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(remark).toProperty("remark")
        );
    }

    default int insertSelective(SysSeqResult row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, sysSeqResult, c ->
            c.map(seqId).toPropertyWhenPresent("seqId", row::getSeqId)
            .map(seqDistCd).toPropertyWhenPresent("seqDistCd", row::getSeqDistCd)
            .map(prefix).toPropertyWhenPresent("prefix", row::getPrefix)
            .map(separator1).toPropertyWhenPresent("separator1", row::getSeparator1)
            .map(dateVal).toPropertyWhenPresent("dateVal", row::getDateVal)
            .map(separator2).toPropertyWhenPresent("separator2", row::getSeparator2)
            .map(seqNo).toPropertyWhenPresent("seqNo", row::getSeqNo)
            .map(remark1).toPropertyWhenPresent("remark1", row::getRemark1)
            .map(remark2).toPropertyWhenPresent("remark2", row::getRemark2)
            .map(remark3).toPropertyWhenPresent("remark3", row::getRemark3)
            .map(remark4).toPropertyWhenPresent("remark4", row::getRemark4)
            .map(remark5).toPropertyWhenPresent("remark5", row::getRemark5)
            .map(createBy).toPropertyWhenPresent("createBy", row::getCreateBy)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(updateBy).toPropertyWhenPresent("updateBy", row::getUpdateBy)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
            .map(remark).toPropertyWhenPresent("remark", row::getRemark)
        );
    }

    default Optional<SysSeqResult> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysSeqResult, completer);
    }

    default List<SysSeqResult> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysSeqResult, completer);
    }

    default List<SysSeqResult> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysSeqResult, completer);
    }

    default Optional<SysSeqResult> selectByPrimaryKey(Long seqId_) {
        return selectOne(c ->
            c.where(seqId, isEqualTo(seqId_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysSeqResult, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SysSeqResult row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(seqId).equalTo(row::getSeqId)
                .set(seqDistCd).equalTo(row::getSeqDistCd)
                .set(prefix).equalTo(row::getPrefix)
                .set(separator1).equalTo(row::getSeparator1)
                .set(dateVal).equalTo(row::getDateVal)
                .set(separator2).equalTo(row::getSeparator2)
                .set(seqNo).equalTo(row::getSeqNo)
                .set(remark1).equalTo(row::getRemark1)
                .set(remark2).equalTo(row::getRemark2)
                .set(remark3).equalTo(row::getRemark3)
                .set(remark4).equalTo(row::getRemark4)
                .set(remark5).equalTo(row::getRemark5)
                .set(createBy).equalTo(row::getCreateBy)
                .set(createTime).equalTo(row::getCreateTime)
                .set(updateBy).equalTo(row::getUpdateBy)
                .set(updateTime).equalTo(row::getUpdateTime)
                .set(remark).equalTo(row::getRemark);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysSeqResult row, UpdateDSL<UpdateModel> dsl) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return dsl.set(seqId).equalToWhenPresent(row::getSeqId)
                .set(seqDistCd).equalToWhenPresent(row::getSeqDistCd)
                .set(prefix).equalToWhenPresent(row::getPrefix)
                .set(separator1).equalToWhenPresent(row::getSeparator1)
                .set(dateVal).equalToWhenPresent(row::getDateVal)
                .set(separator2).equalToWhenPresent(row::getSeparator2)
                .set(seqNo).equalToWhenPresent(row::getSeqNo)
                .set(remark1).equalToWhenPresent(row::getRemark1)
                .set(remark2).equalToWhenPresent(row::getRemark2)
                .set(remark3).equalToWhenPresent(row::getRemark3)
                .set(remark4).equalToWhenPresent(row::getRemark4)
                .set(remark5).equalToWhenPresent(row::getRemark5)
                .set(createBy).equalToWhenPresent(row::getCreateBy)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(updateBy).equalToWhenPresent(row::getUpdateBy)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime)
                .set(remark).equalToWhenPresent(row::getRemark);
    }

    default int updateByPrimaryKey(SysSeqResult row) {
        return update(c ->
            c.set(seqDistCd).equalTo(row::getSeqDistCd)
            .set(prefix).equalTo(row::getPrefix)
            .set(separator1).equalTo(row::getSeparator1)
            .set(dateVal).equalTo(row::getDateVal)
            .set(separator2).equalTo(row::getSeparator2)
            .set(seqNo).equalTo(row::getSeqNo)
            .set(remark1).equalTo(row::getRemark1)
            .set(remark2).equalTo(row::getRemark2)
            .set(remark3).equalTo(row::getRemark3)
            .set(remark4).equalTo(row::getRemark4)
            .set(remark5).equalTo(row::getRemark5)
            .set(createBy).equalTo(row::getCreateBy)
            .set(createTime).equalTo(row::getCreateTime)
            .set(updateBy).equalTo(row::getUpdateBy)
            .set(updateTime).equalTo(row::getUpdateTime)
            .set(remark).equalTo(row::getRemark)
            .where(seqId, isEqualTo(row::getSeqId))
        );
    }

    default int updateByPrimaryKeySelective(SysSeqResult row) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return update(c ->
            c.set(seqDistCd).equalToWhenPresent(row::getSeqDistCd)
            .set(prefix).equalToWhenPresent(row::getPrefix)
            .set(separator1).equalToWhenPresent(row::getSeparator1)
            .set(dateVal).equalToWhenPresent(row::getDateVal)
            .set(separator2).equalToWhenPresent(row::getSeparator2)
            .set(seqNo).equalToWhenPresent(row::getSeqNo)
            .set(remark1).equalToWhenPresent(row::getRemark1)
            .set(remark2).equalToWhenPresent(row::getRemark2)
            .set(remark3).equalToWhenPresent(row::getRemark3)
            .set(remark4).equalToWhenPresent(row::getRemark4)
            .set(remark5).equalToWhenPresent(row::getRemark5)
            .set(createBy).equalToWhenPresent(row::getCreateBy)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(updateBy).equalToWhenPresent(row::getUpdateBy)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .set(remark).equalToWhenPresent(row::getRemark)
            .where(seqId, isEqualTo(row::getSeqId))
        );
    }
}