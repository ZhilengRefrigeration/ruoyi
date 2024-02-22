package com.ruoyi.wms.mapper.stock;

import static com.ruoyi.wms.mapper.stock.BaseStockDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.wms.domain.BaseStock;
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
public interface BaseStockMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<BaseStock>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(whsCd, stgBinCd, itemCd, lotNo, subLotNo, deptId, stdUnitQty, pkgUnitQty, serialNo, palletId, parentPalletId, remark1, remark2, remark3, remark4, remark5, updateCount, deleteFlag, createBy, createTime, updateBy, updateTime, remark);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BaseStockResult", value = {
        @Result(column="WHS_CD", property="whsCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="STG_BIN_CD", property="stgBinCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ITEM_CD", property="itemCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="LOT_NO", property="lotNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SUB_LOT_NO", property="subLotNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEPT_ID", property="deptId", jdbcType=JdbcType.INTEGER),
        @Result(column="STD_UNIT_QTY", property="stdUnitQty", jdbcType=JdbcType.DECIMAL),
        @Result(column="PKG_UNIT_QTY", property="pkgUnitQty", jdbcType=JdbcType.DECIMAL),
        @Result(column="SERIAL_NO", property="serialNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="PALLET_ID", property="palletId", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARENT_PALLET_ID", property="parentPalletId", jdbcType=JdbcType.VARCHAR),
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
    List<BaseStock> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BaseStockResult")
    Optional<BaseStock> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, baseStock, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, baseStock, completer);
    }

    default int deleteByPrimaryKey(String whsCd_, String stgBinCd_, String itemCd_, String lotNo_, String subLotNo_) {
        return delete(c -> 
            c.where(whsCd, isEqualTo(whsCd_))
            .and(stgBinCd, isEqualTo(stgBinCd_))
            .and(itemCd, isEqualTo(itemCd_))
            .and(lotNo, isEqualTo(lotNo_))
            .and(subLotNo, isEqualTo(subLotNo_))
        );
    }

    default int insert(BaseStock row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, baseStock, c ->
            c.map(whsCd).toProperty("whsCd")
            .map(stgBinCd).toProperty("stgBinCd")
            .map(itemCd).toProperty("itemCd")
            .map(lotNo).toProperty("lotNo")
            .map(subLotNo).toProperty("subLotNo")
            .map(deptId).toProperty("deptId")
            .map(stdUnitQty).toProperty("stdUnitQty")
            .map(pkgUnitQty).toProperty("pkgUnitQty")
            .map(serialNo).toProperty("serialNo")
            .map(palletId).toProperty("palletId")
            .map(parentPalletId).toProperty("parentPalletId")
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

    default int insertMultiple(Collection<BaseStock> records) {
        for (BaseStock row : records) {
            row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        }
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, baseStock, c ->
            c.map(whsCd).toProperty("whsCd")
            .map(stgBinCd).toProperty("stgBinCd")
            .map(itemCd).toProperty("itemCd")
            .map(lotNo).toProperty("lotNo")
            .map(subLotNo).toProperty("subLotNo")
            .map(deptId).toProperty("deptId")
            .map(stdUnitQty).toProperty("stdUnitQty")
            .map(pkgUnitQty).toProperty("pkgUnitQty")
            .map(serialNo).toProperty("serialNo")
            .map(palletId).toProperty("palletId")
            .map(parentPalletId).toProperty("parentPalletId")
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

    default int insertSelective(BaseStock row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, baseStock, c ->
            c.map(whsCd).toPropertyWhenPresent("whsCd", row::getWhsCd)
            .map(stgBinCd).toPropertyWhenPresent("stgBinCd", row::getStgBinCd)
            .map(itemCd).toPropertyWhenPresent("itemCd", row::getItemCd)
            .map(lotNo).toPropertyWhenPresent("lotNo", row::getLotNo)
            .map(subLotNo).toPropertyWhenPresent("subLotNo", row::getSubLotNo)
            .map(deptId).toPropertyWhenPresent("deptId", row::getDeptId)
            .map(stdUnitQty).toPropertyWhenPresent("stdUnitQty", row::getStdUnitQty)
            .map(pkgUnitQty).toPropertyWhenPresent("pkgUnitQty", row::getPkgUnitQty)
            .map(serialNo).toPropertyWhenPresent("serialNo", row::getSerialNo)
            .map(palletId).toPropertyWhenPresent("palletId", row::getPalletId)
            .map(parentPalletId).toPropertyWhenPresent("parentPalletId", row::getParentPalletId)
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

    default Optional<BaseStock> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, baseStock, completer);
    }

    default List<BaseStock> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, baseStock, completer);
    }

    default List<BaseStock> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, baseStock, completer);
    }

    default Optional<BaseStock> selectByPrimaryKey(String whsCd_, String stgBinCd_, String itemCd_, String lotNo_, String subLotNo_) {
        return selectOne(c ->
            c.where(whsCd, isEqualTo(whsCd_))
            .and(stgBinCd, isEqualTo(stgBinCd_))
            .and(itemCd, isEqualTo(itemCd_))
            .and(lotNo, isEqualTo(lotNo_))
            .and(subLotNo, isEqualTo(subLotNo_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, baseStock, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(BaseStock row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(whsCd).equalTo(row::getWhsCd)
                .set(stgBinCd).equalTo(row::getStgBinCd)
                .set(itemCd).equalTo(row::getItemCd)
                .set(lotNo).equalTo(row::getLotNo)
                .set(subLotNo).equalTo(row::getSubLotNo)
                .set(deptId).equalTo(row::getDeptId)
                .set(stdUnitQty).equalTo(row::getStdUnitQty)
                .set(pkgUnitQty).equalTo(row::getPkgUnitQty)
                .set(serialNo).equalTo(row::getSerialNo)
                .set(palletId).equalTo(row::getPalletId)
                .set(parentPalletId).equalTo(row::getParentPalletId)
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

    static UpdateDSL<UpdateModel> updateSelectiveColumns(BaseStock row, UpdateDSL<UpdateModel> dsl) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return dsl.set(whsCd).equalToWhenPresent(row::getWhsCd)
                .set(stgBinCd).equalToWhenPresent(row::getStgBinCd)
                .set(itemCd).equalToWhenPresent(row::getItemCd)
                .set(lotNo).equalToWhenPresent(row::getLotNo)
                .set(subLotNo).equalToWhenPresent(row::getSubLotNo)
                .set(deptId).equalToWhenPresent(row::getDeptId)
                .set(stdUnitQty).equalToWhenPresent(row::getStdUnitQty)
                .set(pkgUnitQty).equalToWhenPresent(row::getPkgUnitQty)
                .set(serialNo).equalToWhenPresent(row::getSerialNo)
                .set(palletId).equalToWhenPresent(row::getPalletId)
                .set(parentPalletId).equalToWhenPresent(row::getParentPalletId)
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

    default int updateByPrimaryKey(BaseStock row) {
        return update(c ->
            c.set(deptId).equalTo(row::getDeptId)
            .set(stdUnitQty).equalTo(row::getStdUnitQty)
            .set(pkgUnitQty).equalTo(row::getPkgUnitQty)
            .set(serialNo).equalTo(row::getSerialNo)
            .set(palletId).equalTo(row::getPalletId)
            .set(parentPalletId).equalTo(row::getParentPalletId)
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
            .where(whsCd, isEqualTo(row::getWhsCd))
            .and(stgBinCd, isEqualTo(row::getStgBinCd))
            .and(itemCd, isEqualTo(row::getItemCd))
            .and(lotNo, isEqualTo(row::getLotNo))
            .and(subLotNo, isEqualTo(row::getSubLotNo))
        );
    }

    default int updateByPrimaryKeySelective(BaseStock row) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return update(c ->
            c.set(deptId).equalToWhenPresent(row::getDeptId)
            .set(stdUnitQty).equalToWhenPresent(row::getStdUnitQty)
            .set(pkgUnitQty).equalToWhenPresent(row::getPkgUnitQty)
            .set(serialNo).equalToWhenPresent(row::getSerialNo)
            .set(palletId).equalToWhenPresent(row::getPalletId)
            .set(parentPalletId).equalToWhenPresent(row::getParentPalletId)
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
            .where(whsCd, isEqualTo(row::getWhsCd))
            .and(stgBinCd, isEqualTo(row::getStgBinCd))
            .and(itemCd, isEqualTo(row::getItemCd))
            .and(lotNo, isEqualTo(row::getLotNo))
            .and(subLotNo, isEqualTo(row::getSubLotNo))
        );
    }
}