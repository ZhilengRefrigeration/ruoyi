package com.ruoyi.wms.mapper;

import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.wms.domain.GoodsType;
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

import static com.ruoyi.wms.mapper.GoodsTypeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface GoodsTypeMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<GoodsType>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(goodsTypeCd, deptId, goodsTypeName, remark1, remark2, remark3, remark4, remark5, updateCount, deleteFlag, createBy, createTime, updateBy, updateTime, remark);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="GoodsTypeResult", value = {
        @Result(column="GOODS_TYPE_CD", property="goodsTypeCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEPT_ID", property="deptId", jdbcType=JdbcType.INTEGER),
        @Result(column="GOODS_TYPE_NAME", property="goodsTypeName", jdbcType=JdbcType.VARCHAR),
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
    List<GoodsType> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("GoodsTypeResult")
    Optional<GoodsType> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, goodsType, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, goodsType, completer);
    }

    default int deleteByPrimaryKey(String goodsTypeCd_) {
        return delete(c -> 
            c.where(goodsTypeCd, isEqualTo(goodsTypeCd_))
        );
    }

    default int insert(GoodsType row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, goodsType, c ->
            c.map(goodsTypeCd).toProperty("goodsTypeCd")
            .map(deptId).toProperty("deptId")
            .map(goodsTypeName).toProperty("goodsTypeName")
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

    default int insertMultiple(Collection<GoodsType> records) {
        for (GoodsType row : records) {
            row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        }
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, goodsType, c ->
            c.map(goodsTypeCd).toProperty("goodsTypeCd")
            .map(deptId).toProperty("deptId")
            .map(goodsTypeName).toProperty("goodsTypeName")
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

    default int insertSelective(GoodsType row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, goodsType, c ->
            c.map(goodsTypeCd).toPropertyWhenPresent("goodsTypeCd", row::getGoodsTypeCd)
            .map(deptId).toPropertyWhenPresent("deptId", row::getDeptId)
            .map(goodsTypeName).toPropertyWhenPresent("goodsTypeName", row::getGoodsTypeName)
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

    default Optional<GoodsType> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, goodsType, completer);
    }

    default List<GoodsType> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, goodsType, completer);
    }

    default List<GoodsType> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, goodsType, completer);
    }

    default Optional<GoodsType> selectByPrimaryKey(String goodsTypeCd_) {
        return selectOne(c ->
            c.where(goodsTypeCd, isEqualTo(goodsTypeCd_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, goodsType, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(GoodsType row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(goodsTypeCd).equalTo(row::getGoodsTypeCd)
                .set(deptId).equalTo(row::getDeptId)
                .set(goodsTypeName).equalTo(row::getGoodsTypeName)
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

    static UpdateDSL<UpdateModel> updateSelectiveColumns(GoodsType row, UpdateDSL<UpdateModel> dsl) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return dsl.set(goodsTypeCd).equalToWhenPresent(row::getGoodsTypeCd)
                .set(deptId).equalToWhenPresent(row::getDeptId)
                .set(goodsTypeName).equalToWhenPresent(row::getGoodsTypeName)
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

    default int updateByPrimaryKey(GoodsType row) {
        return update(c ->
            c.set(deptId).equalTo(row::getDeptId)
            .set(goodsTypeName).equalTo(row::getGoodsTypeName)
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
            .where(goodsTypeCd, isEqualTo(row::getGoodsTypeCd))
        );
    }

    default int updateByPrimaryKeySelective(GoodsType row) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return update(c ->
            c.set(deptId).equalToWhenPresent(row::getDeptId)
            .set(goodsTypeName).equalToWhenPresent(row::getGoodsTypeName)
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
            .where(goodsTypeCd, isEqualTo(row::getGoodsTypeCd))
        );
    }
}