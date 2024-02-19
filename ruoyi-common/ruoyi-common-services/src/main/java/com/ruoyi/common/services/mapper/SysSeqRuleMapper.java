package com.ruoyi.common.services.mapper;

import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.common.services.domain.SysSeqRule;
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

import static com.ruoyi.common.services.mapper.SysSeqRuleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface SysSeqRuleMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<SysSeqRule>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(ruleId, seqDistCd, ruleName, prefix, separator1, dateFormat, minDigits, separator2, generatorName, enableFlag, remark1, remark2, remark3, remark4, remark5, createBy, createTime, updateBy, updateTime, remark);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysSeqRuleResult", value = {
        @Result(column="rule_id", property="ruleId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="seq_dist_cd", property="seqDistCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="rule_name", property="ruleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="prefix", property="prefix", jdbcType=JdbcType.VARCHAR),
        @Result(column="separator1", property="separator1", jdbcType=JdbcType.VARCHAR),
        @Result(column="date_format", property="dateFormat", jdbcType=JdbcType.VARCHAR),
        @Result(column="min_digits", property="minDigits", jdbcType=JdbcType.INTEGER),
        @Result(column="separator2", property="separator2", jdbcType=JdbcType.VARCHAR),
        @Result(column="generator_name", property="generatorName", jdbcType=JdbcType.VARCHAR),
        @Result(column="enable_flag", property="enableFlag", jdbcType=JdbcType.INTEGER),
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
    List<SysSeqRule> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysSeqRuleResult")
    Optional<SysSeqRule> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysSeqRule, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysSeqRule, completer);
    }

    default int deleteByPrimaryKey(Long ruleId_) {
        return delete(c -> 
            c.where(ruleId, isEqualTo(ruleId_))
        );
    }

    default int insert(SysSeqRule row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, sysSeqRule, c ->
            c.map(ruleId).toProperty("ruleId")
            .map(seqDistCd).toProperty("seqDistCd")
            .map(ruleName).toProperty("ruleName")
            .map(prefix).toProperty("prefix")
            .map(separator1).toProperty("separator1")
            .map(dateFormat).toProperty("dateFormat")
            .map(minDigits).toProperty("minDigits")
            .map(separator2).toProperty("separator2")
            .map(generatorName).toProperty("generatorName")
            .map(enableFlag).toProperty("enableFlag")
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

    default int insertMultiple(Collection<SysSeqRule> records) {
        for (SysSeqRule row : records) {
            row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        }
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, sysSeqRule, c ->
            c.map(ruleId).toProperty("ruleId")
            .map(seqDistCd).toProperty("seqDistCd")
            .map(ruleName).toProperty("ruleName")
            .map(prefix).toProperty("prefix")
            .map(separator1).toProperty("separator1")
            .map(dateFormat).toProperty("dateFormat")
            .map(minDigits).toProperty("minDigits")
            .map(separator2).toProperty("separator2")
            .map(generatorName).toProperty("generatorName")
            .map(enableFlag).toProperty("enableFlag")
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

    default int insertSelective(SysSeqRule row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, sysSeqRule, c ->
            c.map(ruleId).toPropertyWhenPresent("ruleId", row::getRuleId)
            .map(seqDistCd).toPropertyWhenPresent("seqDistCd", row::getSeqDistCd)
            .map(ruleName).toPropertyWhenPresent("ruleName", row::getRuleName)
            .map(prefix).toPropertyWhenPresent("prefix", row::getPrefix)
            .map(separator1).toPropertyWhenPresent("separator1", row::getSeparator1)
            .map(dateFormat).toPropertyWhenPresent("dateFormat", row::getDateFormat)
            .map(minDigits).toPropertyWhenPresent("minDigits", row::getMinDigits)
            .map(separator2).toPropertyWhenPresent("separator2", row::getSeparator2)
            .map(generatorName).toPropertyWhenPresent("generatorName", row::getGeneratorName)
            .map(enableFlag).toPropertyWhenPresent("enableFlag", row::getEnableFlag)
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

    default Optional<SysSeqRule> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysSeqRule, completer);
    }

    default List<SysSeqRule> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysSeqRule, completer);
    }

    default List<SysSeqRule> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysSeqRule, completer);
    }

    default Optional<SysSeqRule> selectByPrimaryKey(Long ruleId_) {
        return selectOne(c ->
            c.where(ruleId, isEqualTo(ruleId_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysSeqRule, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SysSeqRule row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(ruleId).equalTo(row::getRuleId)
                .set(seqDistCd).equalTo(row::getSeqDistCd)
                .set(ruleName).equalTo(row::getRuleName)
                .set(prefix).equalTo(row::getPrefix)
                .set(separator1).equalTo(row::getSeparator1)
                .set(dateFormat).equalTo(row::getDateFormat)
                .set(minDigits).equalTo(row::getMinDigits)
                .set(separator2).equalTo(row::getSeparator2)
                .set(generatorName).equalTo(row::getGeneratorName)
                .set(enableFlag).equalTo(row::getEnableFlag)
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

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysSeqRule row, UpdateDSL<UpdateModel> dsl) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return dsl.set(ruleId).equalToWhenPresent(row::getRuleId)
                .set(seqDistCd).equalToWhenPresent(row::getSeqDistCd)
                .set(ruleName).equalToWhenPresent(row::getRuleName)
                .set(prefix).equalToWhenPresent(row::getPrefix)
                .set(separator1).equalToWhenPresent(row::getSeparator1)
                .set(dateFormat).equalToWhenPresent(row::getDateFormat)
                .set(minDigits).equalToWhenPresent(row::getMinDigits)
                .set(separator2).equalToWhenPresent(row::getSeparator2)
                .set(generatorName).equalToWhenPresent(row::getGeneratorName)
                .set(enableFlag).equalToWhenPresent(row::getEnableFlag)
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

    default int updateByPrimaryKey(SysSeqRule row) {
        return update(c ->
            c.set(seqDistCd).equalTo(row::getSeqDistCd)
            .set(ruleName).equalTo(row::getRuleName)
            .set(prefix).equalTo(row::getPrefix)
            .set(separator1).equalTo(row::getSeparator1)
            .set(dateFormat).equalTo(row::getDateFormat)
            .set(minDigits).equalTo(row::getMinDigits)
            .set(separator2).equalTo(row::getSeparator2)
            .set(generatorName).equalTo(row::getGeneratorName)
            .set(enableFlag).equalTo(row::getEnableFlag)
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
            .where(ruleId, isEqualTo(row::getRuleId))
        );
    }

    default int updateByPrimaryKeySelective(SysSeqRule row) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return update(c ->
            c.set(seqDistCd).equalToWhenPresent(row::getSeqDistCd)
            .set(ruleName).equalToWhenPresent(row::getRuleName)
            .set(prefix).equalToWhenPresent(row::getPrefix)
            .set(separator1).equalToWhenPresent(row::getSeparator1)
            .set(dateFormat).equalToWhenPresent(row::getDateFormat)
            .set(minDigits).equalToWhenPresent(row::getMinDigits)
            .set(separator2).equalToWhenPresent(row::getSeparator2)
            .set(generatorName).equalToWhenPresent(row::getGeneratorName)
            .set(enableFlag).equalToWhenPresent(row::getEnableFlag)
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
            .where(ruleId, isEqualTo(row::getRuleId))
        );
    }
}