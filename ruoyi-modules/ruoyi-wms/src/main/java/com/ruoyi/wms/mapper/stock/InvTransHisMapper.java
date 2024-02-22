package com.ruoyi.wms.mapper.stock;

import static com.ruoyi.wms.mapper.stock.InvTransHisDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.wms.domain.InvTransHis;
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
public interface InvTransHisMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<InvTransHis>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(invTransNo, deptId, invTransType, whsCd, stgBinCd, palletId, stdUnitQty, pkgUnitQty, transOrderNo, transOrderDetlNo, operator, businessCls, itemCd, lotNo, subLotNo, serialNo, reason, remark1, remark2, remark3, remark4, remark5, updateCount, deleteFlag, createBy, createTime, updateBy, updateTime, remark);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="InvTransHisResult", value = {
        @Result(column="INV_TRANS_NO", property="invTransNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEPT_ID", property="deptId", jdbcType=JdbcType.INTEGER),
        @Result(column="INV_TRANS_TYPE", property="invTransType", jdbcType=JdbcType.INTEGER),
        @Result(column="WHS_CD", property="whsCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="STG_BIN_CD", property="stgBinCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PALLET_ID", property="palletId", jdbcType=JdbcType.VARCHAR),
        @Result(column="STD_UNIT_QTY", property="stdUnitQty", jdbcType=JdbcType.DECIMAL),
        @Result(column="PKG_UNIT_QTY", property="pkgUnitQty", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRANS_ORDER_NO", property="transOrderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANS_ORDER_DETL_NO", property="transOrderDetlNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPERATOR", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="BUSINESS_CLS", property="businessCls", jdbcType=JdbcType.VARCHAR),
        @Result(column="ITEM_CD", property="itemCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOT_NO", property="lotNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="SUB_LOT_NO", property="subLotNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="SERIAL_NO", property="serialNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="REASON", property="reason", jdbcType=JdbcType.VARCHAR),
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
    List<InvTransHis> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("InvTransHisResult")
    Optional<InvTransHis> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, invTransHis, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, invTransHis, completer);
    }

    default int deleteByPrimaryKey(String invTransNo_) {
        return delete(c -> 
            c.where(invTransNo, isEqualTo(invTransNo_))
        );
    }

    default int insert(InvTransHis row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, invTransHis, c ->
            c.map(invTransNo).toProperty("invTransNo")
            .map(deptId).toProperty("deptId")
            .map(invTransType).toProperty("invTransType")
            .map(whsCd).toProperty("whsCd")
            .map(stgBinCd).toProperty("stgBinCd")
            .map(palletId).toProperty("palletId")
            .map(stdUnitQty).toProperty("stdUnitQty")
            .map(pkgUnitQty).toProperty("pkgUnitQty")
            .map(transOrderNo).toProperty("transOrderNo")
            .map(transOrderDetlNo).toProperty("transOrderDetlNo")
            .map(operator).toProperty("operator")
            .map(businessCls).toProperty("businessCls")
            .map(itemCd).toProperty("itemCd")
            .map(lotNo).toProperty("lotNo")
            .map(subLotNo).toProperty("subLotNo")
            .map(serialNo).toProperty("serialNo")
            .map(reason).toProperty("reason")
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

    default int insertMultiple(Collection<InvTransHis> records) {
        for (InvTransHis row : records) {
            row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        }
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, invTransHis, c ->
            c.map(invTransNo).toProperty("invTransNo")
            .map(deptId).toProperty("deptId")
            .map(invTransType).toProperty("invTransType")
            .map(whsCd).toProperty("whsCd")
            .map(stgBinCd).toProperty("stgBinCd")
            .map(palletId).toProperty("palletId")
            .map(stdUnitQty).toProperty("stdUnitQty")
            .map(pkgUnitQty).toProperty("pkgUnitQty")
            .map(transOrderNo).toProperty("transOrderNo")
            .map(transOrderDetlNo).toProperty("transOrderDetlNo")
            .map(operator).toProperty("operator")
            .map(businessCls).toProperty("businessCls")
            .map(itemCd).toProperty("itemCd")
            .map(lotNo).toProperty("lotNo")
            .map(subLotNo).toProperty("subLotNo")
            .map(serialNo).toProperty("serialNo")
            .map(reason).toProperty("reason")
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

    default int insertSelective(InvTransHis row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, invTransHis, c ->
            c.map(invTransNo).toPropertyWhenPresent("invTransNo", row::getInvTransNo)
            .map(deptId).toPropertyWhenPresent("deptId", row::getDeptId)
            .map(invTransType).toPropertyWhenPresent("invTransType", row::getInvTransType)
            .map(whsCd).toPropertyWhenPresent("whsCd", row::getWhsCd)
            .map(stgBinCd).toPropertyWhenPresent("stgBinCd", row::getStgBinCd)
            .map(palletId).toPropertyWhenPresent("palletId", row::getPalletId)
            .map(stdUnitQty).toPropertyWhenPresent("stdUnitQty", row::getStdUnitQty)
            .map(pkgUnitQty).toPropertyWhenPresent("pkgUnitQty", row::getPkgUnitQty)
            .map(transOrderNo).toPropertyWhenPresent("transOrderNo", row::getTransOrderNo)
            .map(transOrderDetlNo).toPropertyWhenPresent("transOrderDetlNo", row::getTransOrderDetlNo)
            .map(operator).toPropertyWhenPresent("operator", row::getOperator)
            .map(businessCls).toPropertyWhenPresent("businessCls", row::getBusinessCls)
            .map(itemCd).toPropertyWhenPresent("itemCd", row::getItemCd)
            .map(lotNo).toPropertyWhenPresent("lotNo", row::getLotNo)
            .map(subLotNo).toPropertyWhenPresent("subLotNo", row::getSubLotNo)
            .map(serialNo).toPropertyWhenPresent("serialNo", row::getSerialNo)
            .map(reason).toPropertyWhenPresent("reason", row::getReason)
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

    default Optional<InvTransHis> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, invTransHis, completer);
    }

    default List<InvTransHis> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, invTransHis, completer);
    }

    default List<InvTransHis> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, invTransHis, completer);
    }

    default Optional<InvTransHis> selectByPrimaryKey(String invTransNo_) {
        return selectOne(c ->
            c.where(invTransNo, isEqualTo(invTransNo_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, invTransHis, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(InvTransHis row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(invTransNo).equalTo(row::getInvTransNo)
                .set(deptId).equalTo(row::getDeptId)
                .set(invTransType).equalTo(row::getInvTransType)
                .set(whsCd).equalTo(row::getWhsCd)
                .set(stgBinCd).equalTo(row::getStgBinCd)
                .set(palletId).equalTo(row::getPalletId)
                .set(stdUnitQty).equalTo(row::getStdUnitQty)
                .set(pkgUnitQty).equalTo(row::getPkgUnitQty)
                .set(transOrderNo).equalTo(row::getTransOrderNo)
                .set(transOrderDetlNo).equalTo(row::getTransOrderDetlNo)
                .set(operator).equalTo(row::getOperator)
                .set(businessCls).equalTo(row::getBusinessCls)
                .set(itemCd).equalTo(row::getItemCd)
                .set(lotNo).equalTo(row::getLotNo)
                .set(subLotNo).equalTo(row::getSubLotNo)
                .set(serialNo).equalTo(row::getSerialNo)
                .set(reason).equalTo(row::getReason)
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

    static UpdateDSL<UpdateModel> updateSelectiveColumns(InvTransHis row, UpdateDSL<UpdateModel> dsl) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return dsl.set(invTransNo).equalToWhenPresent(row::getInvTransNo)
                .set(deptId).equalToWhenPresent(row::getDeptId)
                .set(invTransType).equalToWhenPresent(row::getInvTransType)
                .set(whsCd).equalToWhenPresent(row::getWhsCd)
                .set(stgBinCd).equalToWhenPresent(row::getStgBinCd)
                .set(palletId).equalToWhenPresent(row::getPalletId)
                .set(stdUnitQty).equalToWhenPresent(row::getStdUnitQty)
                .set(pkgUnitQty).equalToWhenPresent(row::getPkgUnitQty)
                .set(transOrderNo).equalToWhenPresent(row::getTransOrderNo)
                .set(transOrderDetlNo).equalToWhenPresent(row::getTransOrderDetlNo)
                .set(operator).equalToWhenPresent(row::getOperator)
                .set(businessCls).equalToWhenPresent(row::getBusinessCls)
                .set(itemCd).equalToWhenPresent(row::getItemCd)
                .set(lotNo).equalToWhenPresent(row::getLotNo)
                .set(subLotNo).equalToWhenPresent(row::getSubLotNo)
                .set(serialNo).equalToWhenPresent(row::getSerialNo)
                .set(reason).equalToWhenPresent(row::getReason)
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

    default int updateByPrimaryKey(InvTransHis row) {
        return update(c ->
            c.set(deptId).equalTo(row::getDeptId)
            .set(invTransType).equalTo(row::getInvTransType)
            .set(whsCd).equalTo(row::getWhsCd)
            .set(stgBinCd).equalTo(row::getStgBinCd)
            .set(palletId).equalTo(row::getPalletId)
            .set(stdUnitQty).equalTo(row::getStdUnitQty)
            .set(pkgUnitQty).equalTo(row::getPkgUnitQty)
            .set(transOrderNo).equalTo(row::getTransOrderNo)
            .set(transOrderDetlNo).equalTo(row::getTransOrderDetlNo)
            .set(operator).equalTo(row::getOperator)
            .set(businessCls).equalTo(row::getBusinessCls)
            .set(itemCd).equalTo(row::getItemCd)
            .set(lotNo).equalTo(row::getLotNo)
            .set(subLotNo).equalTo(row::getSubLotNo)
            .set(serialNo).equalTo(row::getSerialNo)
            .set(reason).equalTo(row::getReason)
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
            .where(invTransNo, isEqualTo(row::getInvTransNo))
        );
    }

    default int updateByPrimaryKeySelective(InvTransHis row) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return update(c ->
            c.set(deptId).equalToWhenPresent(row::getDeptId)
            .set(invTransType).equalToWhenPresent(row::getInvTransType)
            .set(whsCd).equalToWhenPresent(row::getWhsCd)
            .set(stgBinCd).equalToWhenPresent(row::getStgBinCd)
            .set(palletId).equalToWhenPresent(row::getPalletId)
            .set(stdUnitQty).equalToWhenPresent(row::getStdUnitQty)
            .set(pkgUnitQty).equalToWhenPresent(row::getPkgUnitQty)
            .set(transOrderNo).equalToWhenPresent(row::getTransOrderNo)
            .set(transOrderDetlNo).equalToWhenPresent(row::getTransOrderDetlNo)
            .set(operator).equalToWhenPresent(row::getOperator)
            .set(businessCls).equalToWhenPresent(row::getBusinessCls)
            .set(itemCd).equalToWhenPresent(row::getItemCd)
            .set(lotNo).equalToWhenPresent(row::getLotNo)
            .set(subLotNo).equalToWhenPresent(row::getSubLotNo)
            .set(serialNo).equalToWhenPresent(row::getSerialNo)
            .set(reason).equalToWhenPresent(row::getReason)
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
            .where(invTransNo, isEqualTo(row::getInvTransNo))
        );
    }
}