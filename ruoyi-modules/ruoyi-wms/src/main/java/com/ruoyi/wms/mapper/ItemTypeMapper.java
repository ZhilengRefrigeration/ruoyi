package com.ruoyi.wms.mapper;

import static com.ruoyi.wms.mapper.ItemTypeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.wms.domain.ItemType;
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
public interface ItemTypeMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<ItemType>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(itemTypeCd, deptId, itemTypeName, remark1, remark2, remark3, remark4, remark5, updateCount, deleteFlag, createBy, createTime, updateBy, updateTime, remark);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ItemTypeResult", value = {
        @Result(column="ITEM_TYPE_CD", property="itemTypeCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEPT_ID", property="deptId", jdbcType=JdbcType.INTEGER),
        @Result(column="ITEM_TYPE_NAME", property="itemTypeName", jdbcType=JdbcType.VARCHAR),
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
    List<ItemType> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ItemTypeResult")
    Optional<ItemType> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, itemType, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, itemType, completer);
    }

    default int deleteByPrimaryKey(String itemTypeCd_) {
        return delete(c -> 
            c.where(itemTypeCd, isEqualTo(itemTypeCd_))
        );
    }

    default int insert(ItemType row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, itemType, c ->
            c.map(itemTypeCd).toProperty("itemTypeCd")
            .map(deptId).toProperty("deptId")
            .map(itemTypeName).toProperty("itemTypeName")
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

    default int insertMultiple(Collection<ItemType> records) {
        for (ItemType row : records) {
            row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        }
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, itemType, c ->
            c.map(itemTypeCd).toProperty("itemTypeCd")
            .map(deptId).toProperty("deptId")
            .map(itemTypeName).toProperty("itemTypeName")
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

    default int insertSelective(ItemType row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, itemType, c ->
            c.map(itemTypeCd).toPropertyWhenPresent("itemTypeCd", row::getItemTypeCd)
            .map(deptId).toPropertyWhenPresent("deptId", row::getDeptId)
            .map(itemTypeName).toPropertyWhenPresent("itemTypeName", row::getItemTypeName)
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

    default Optional<ItemType> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, itemType, completer);
    }

    default List<ItemType> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, itemType, completer);
    }

    default List<ItemType> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, itemType, completer);
    }

    default Optional<ItemType> selectByPrimaryKey(String itemTypeCd_) {
        return selectOne(c ->
            c.where(itemTypeCd, isEqualTo(itemTypeCd_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, itemType, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(ItemType row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(itemTypeCd).equalTo(row::getItemTypeCd)
                .set(deptId).equalTo(row::getDeptId)
                .set(itemTypeName).equalTo(row::getItemTypeName)
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

    static UpdateDSL<UpdateModel> updateSelectiveColumns(ItemType row, UpdateDSL<UpdateModel> dsl) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return dsl.set(itemTypeCd).equalToWhenPresent(row::getItemTypeCd)
                .set(deptId).equalToWhenPresent(row::getDeptId)
                .set(itemTypeName).equalToWhenPresent(row::getItemTypeName)
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

    default int updateByPrimaryKey(ItemType row) {
        return update(c ->
            c.set(deptId).equalTo(row::getDeptId)
            .set(itemTypeName).equalTo(row::getItemTypeName)
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
            .where(itemTypeCd, isEqualTo(row::getItemTypeCd))
        );
    }

    default int updateByPrimaryKeySelective(ItemType row) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return update(c ->
            c.set(deptId).equalToWhenPresent(row::getDeptId)
            .set(itemTypeName).equalToWhenPresent(row::getItemTypeName)
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
            .where(itemTypeCd, isEqualTo(row::getItemTypeCd))
        );
    }
}