package com.ruoyi.wms.mapper;

import static com.ruoyi.wms.mapper.UnitInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.ruoyi.wms.domain.UnitInfo;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
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
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface UnitInfoMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<UnitInfo>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    BasicColumn[] selectList = BasicColumn.columnList(unitCode, unitName, remark1, remark2, remark3, remark4, remark5, updateCount, deleteFlag, createBy, createTime, updateBy, updateTime, remark);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UnitInfoResult", value = {
        @Result(column="UNIT_CODE", property="unitCode", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="UNIT_NAME", property="unitName", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK_1", property="remark1", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK_2", property="remark2", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK_3", property="remark3", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK_4", property="remark4", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK_5", property="remark5", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_COUNT", property="updateCount", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETE_FLAG", property="deleteFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<UnitInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UnitInfoResult")
    Optional<UnitInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, unitInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, unitInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default int deleteByPrimaryKey(String unitCode_) {
        return delete(c -> 
            c.where(unitCode, isEqualTo(unitCode_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default int insert(UnitInfo row) {
        return MyBatis3Utils.insert(this::insert, row, unitInfo, c ->
            c.map(unitCode).toProperty("unitCode")
            .map(unitName).toProperty("unitName")
            .map(remark1).toProperty("remark1")
            .map(remark2).toProperty("remark2")
            .map(remark3).toProperty("remark3")
            .map(remark4).toProperty("remark4")
            .map(remark5).toProperty("remark5")
            .map(updateCount).toProperty("updateCount")
            .map(deleteFlag).toProperty("deleteFlag")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(remark).toProperty("remark")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default int insertMultiple(Collection<UnitInfo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, unitInfo, c ->
            c.map(unitCode).toProperty("unitCode")
            .map(unitName).toProperty("unitName")
            .map(remark1).toProperty("remark1")
            .map(remark2).toProperty("remark2")
            .map(remark3).toProperty("remark3")
            .map(remark4).toProperty("remark4")
            .map(remark5).toProperty("remark5")
            .map(updateCount).toProperty("updateCount")
            .map(deleteFlag).toProperty("deleteFlag")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(remark).toProperty("remark")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default int insertSelective(UnitInfo row) {
        return MyBatis3Utils.insert(this::insert, row, unitInfo, c ->
            c.map(unitCode).toPropertyWhenPresent("unitCode", row::getUnitCode)
            .map(unitName).toPropertyWhenPresent("unitName", row::getUnitName)
            .map(remark1).toPropertyWhenPresent("remark1", row::getRemark1)
            .map(remark2).toPropertyWhenPresent("remark2", row::getRemark2)
            .map(remark3).toPropertyWhenPresent("remark3", row::getRemark3)
            .map(remark4).toPropertyWhenPresent("remark4", row::getRemark4)
            .map(remark5).toPropertyWhenPresent("remark5", row::getRemark5)
            .map(updateCount).toPropertyWhenPresent("updateCount", row::getUpdateCount)
            .map(deleteFlag).toPropertyWhenPresent("deleteFlag", row::getDeleteFlag)
            .map(createBy).toPropertyWhenPresent("createBy", row::getCreateBy)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(updateBy).toPropertyWhenPresent("updateBy", row::getUpdateBy)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
            .map(remark).toPropertyWhenPresent("remark", row::getRemark)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default Optional<UnitInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, unitInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default List<UnitInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, unitInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default List<UnitInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, unitInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default Optional<UnitInfo> selectByPrimaryKey(String unitCode_) {
        return selectOne(c ->
            c.where(unitCode, isEqualTo(unitCode_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, unitInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    static UpdateDSL<UpdateModel> updateAllColumns(UnitInfo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unitCode).equalTo(row::getUnitCode)
                .set(unitName).equalTo(row::getUnitName)
                .set(remark1).equalTo(row::getRemark1)
                .set(remark2).equalTo(row::getRemark2)
                .set(remark3).equalTo(row::getRemark3)
                .set(remark4).equalTo(row::getRemark4)
                .set(remark5).equalTo(row::getRemark5)
                .set(updateCount).equalTo(row::getUpdateCount)
                .set(deleteFlag).equalTo(row::getDeleteFlag)
                .set(createBy).equalTo(row::getCreateBy)
                .set(createTime).equalTo(row::getCreateTime)
                .set(updateBy).equalTo(row::getUpdateBy)
                .set(updateTime).equalTo(row::getUpdateTime)
                .set(remark).equalTo(row::getRemark);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UnitInfo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unitCode).equalToWhenPresent(row::getUnitCode)
                .set(unitName).equalToWhenPresent(row::getUnitName)
                .set(remark1).equalToWhenPresent(row::getRemark1)
                .set(remark2).equalToWhenPresent(row::getRemark2)
                .set(remark3).equalToWhenPresent(row::getRemark3)
                .set(remark4).equalToWhenPresent(row::getRemark4)
                .set(remark5).equalToWhenPresent(row::getRemark5)
                .set(updateCount).equalToWhenPresent(row::getUpdateCount)
                .set(deleteFlag).equalToWhenPresent(row::getDeleteFlag)
                .set(createBy).equalToWhenPresent(row::getCreateBy)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(updateBy).equalToWhenPresent(row::getUpdateBy)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime)
                .set(remark).equalToWhenPresent(row::getRemark);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default int updateByPrimaryKey(UnitInfo row) {
        return update(c ->
            c.set(unitName).equalTo(row::getUnitName)
            .set(remark1).equalTo(row::getRemark1)
            .set(remark2).equalTo(row::getRemark2)
            .set(remark3).equalTo(row::getRemark3)
            .set(remark4).equalTo(row::getRemark4)
            .set(remark5).equalTo(row::getRemark5)
            .set(updateCount).equalTo(row::getUpdateCount)
            .set(deleteFlag).equalTo(row::getDeleteFlag)
            .set(createBy).equalTo(row::getCreateBy)
            .set(createTime).equalTo(row::getCreateTime)
            .set(updateBy).equalTo(row::getUpdateBy)
            .set(updateTime).equalTo(row::getUpdateTime)
            .set(remark).equalTo(row::getRemark)
            .where(unitCode, isEqualTo(row::getUnitCode))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: SF_WMS_M_UNIT_INFO")
    default int updateByPrimaryKeySelective(UnitInfo row) {
        return update(c ->
            c.set(unitName).equalToWhenPresent(row::getUnitName)
            .set(remark1).equalToWhenPresent(row::getRemark1)
            .set(remark2).equalToWhenPresent(row::getRemark2)
            .set(remark3).equalToWhenPresent(row::getRemark3)
            .set(remark4).equalToWhenPresent(row::getRemark4)
            .set(remark5).equalToWhenPresent(row::getRemark5)
            .set(updateCount).equalToWhenPresent(row::getUpdateCount)
            .set(deleteFlag).equalToWhenPresent(row::getDeleteFlag)
            .set(createBy).equalToWhenPresent(row::getCreateBy)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(updateBy).equalToWhenPresent(row::getUpdateBy)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .set(remark).equalToWhenPresent(row::getRemark)
            .where(unitCode, isEqualTo(row::getUnitCode))
        );
    }
}