package com.ruoyi.wms.mapper;

import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.wms.domain.WarehouseInfo;
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

import static com.ruoyi.wms.mapper.WarehouseInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface WarehouseInfoMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<WarehouseInfo>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(whsCd, deptId, whsName, whsTypeCd, abbr, addr1, addr2, addr3, phoneNo, zipCd, email, faxNo, respPerson, area, rentalFee, storingFee, remark1, remark2, remark3, remark4, remark5, updateCount, deleteFlag, createBy, createTime, updateBy, updateTime, remark);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="WarehouseInfoResult", value = {
        @Result(column="WHS_CD", property="whsCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEPT_ID", property="deptId", jdbcType=JdbcType.INTEGER),
        @Result(column="WHS_NAME", property="whsName", jdbcType=JdbcType.VARCHAR),
        @Result(column="WHS_TYPE_CD", property="whsTypeCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ABBR", property="abbr", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADDR_1", property="addr1", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADDR_2", property="addr2", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADDR_3", property="addr3", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE_NO", property="phoneNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ZIP_CD", property="zipCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="FAX_NO", property="faxNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="RESP_PERSON", property="respPerson", jdbcType=JdbcType.VARCHAR),
        @Result(column="AREA", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="RENTAL_FEE", property="rentalFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="STORING_FEE", property="storingFee", jdbcType=JdbcType.DECIMAL),
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
    List<WarehouseInfo> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("WarehouseInfoResult")
    Optional<WarehouseInfo> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, warehouseInfo, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, warehouseInfo, completer);
    }

    default int deleteByPrimaryKey(String whsCd_) {
        return delete(c -> 
            c.where(whsCd, isEqualTo(whsCd_))
        );
    }

    default int insert(WarehouseInfo row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, warehouseInfo, c ->
            c.map(whsCd).toProperty("whsCd")
            .map(deptId).toProperty("deptId")
            .map(whsName).toProperty("whsName")
            .map(whsTypeCd).toProperty("whsTypeCd")
            .map(abbr).toProperty("abbr")
            .map(addr1).toProperty("addr1")
            .map(addr2).toProperty("addr2")
            .map(addr3).toProperty("addr3")
            .map(phoneNo).toProperty("phoneNo")
            .map(zipCd).toProperty("zipCd")
            .map(email).toProperty("email")
            .map(faxNo).toProperty("faxNo")
            .map(respPerson).toProperty("respPerson")
            .map(area).toProperty("area")
            .map(rentalFee).toProperty("rentalFee")
            .map(storingFee).toProperty("storingFee")
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

    default int insertMultiple(Collection<WarehouseInfo> records) {
        for (WarehouseInfo row : records) {
            row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        }
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, warehouseInfo, c ->
            c.map(whsCd).toProperty("whsCd")
            .map(deptId).toProperty("deptId")
            .map(whsName).toProperty("whsName")
            .map(whsTypeCd).toProperty("whsTypeCd")
            .map(abbr).toProperty("abbr")
            .map(addr1).toProperty("addr1")
            .map(addr2).toProperty("addr2")
            .map(addr3).toProperty("addr3")
            .map(phoneNo).toProperty("phoneNo")
            .map(zipCd).toProperty("zipCd")
            .map(email).toProperty("email")
            .map(faxNo).toProperty("faxNo")
            .map(respPerson).toProperty("respPerson")
            .map(area).toProperty("area")
            .map(rentalFee).toProperty("rentalFee")
            .map(storingFee).toProperty("storingFee")
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

    default int insertSelective(WarehouseInfo row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, warehouseInfo, c ->
            c.map(whsCd).toPropertyWhenPresent("whsCd", row::getWhsCd)
            .map(deptId).toPropertyWhenPresent("deptId", row::getDeptId)
            .map(whsName).toPropertyWhenPresent("whsName", row::getWhsName)
            .map(whsTypeCd).toPropertyWhenPresent("whsTypeCd", row::getWhsTypeCd)
            .map(abbr).toPropertyWhenPresent("abbr", row::getAbbr)
            .map(addr1).toPropertyWhenPresent("addr1", row::getAddr1)
            .map(addr2).toPropertyWhenPresent("addr2", row::getAddr2)
            .map(addr3).toPropertyWhenPresent("addr3", row::getAddr3)
            .map(phoneNo).toPropertyWhenPresent("phoneNo", row::getPhoneNo)
            .map(zipCd).toPropertyWhenPresent("zipCd", row::getZipCd)
            .map(email).toPropertyWhenPresent("email", row::getEmail)
            .map(faxNo).toPropertyWhenPresent("faxNo", row::getFaxNo)
            .map(respPerson).toPropertyWhenPresent("respPerson", row::getRespPerson)
            .map(area).toPropertyWhenPresent("area", row::getArea)
            .map(rentalFee).toPropertyWhenPresent("rentalFee", row::getRentalFee)
            .map(storingFee).toPropertyWhenPresent("storingFee", row::getStoringFee)
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

    default Optional<WarehouseInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, warehouseInfo, completer);
    }

    default List<WarehouseInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, warehouseInfo, completer);
    }

    default List<WarehouseInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, warehouseInfo, completer);
    }

    default Optional<WarehouseInfo> selectByPrimaryKey(String whsCd_) {
        return selectOne(c ->
            c.where(whsCd, isEqualTo(whsCd_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, warehouseInfo, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(WarehouseInfo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(whsCd).equalTo(row::getWhsCd)
                .set(deptId).equalTo(row::getDeptId)
                .set(whsName).equalTo(row::getWhsName)
                .set(whsTypeCd).equalTo(row::getWhsTypeCd)
                .set(abbr).equalTo(row::getAbbr)
                .set(addr1).equalTo(row::getAddr1)
                .set(addr2).equalTo(row::getAddr2)
                .set(addr3).equalTo(row::getAddr3)
                .set(phoneNo).equalTo(row::getPhoneNo)
                .set(zipCd).equalTo(row::getZipCd)
                .set(email).equalTo(row::getEmail)
                .set(faxNo).equalTo(row::getFaxNo)
                .set(respPerson).equalTo(row::getRespPerson)
                .set(area).equalTo(row::getArea)
                .set(rentalFee).equalTo(row::getRentalFee)
                .set(storingFee).equalTo(row::getStoringFee)
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

    static UpdateDSL<UpdateModel> updateSelectiveColumns(WarehouseInfo row, UpdateDSL<UpdateModel> dsl) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return dsl.set(whsCd).equalToWhenPresent(row::getWhsCd)
                .set(deptId).equalToWhenPresent(row::getDeptId)
                .set(whsName).equalToWhenPresent(row::getWhsName)
                .set(whsTypeCd).equalToWhenPresent(row::getWhsTypeCd)
                .set(abbr).equalToWhenPresent(row::getAbbr)
                .set(addr1).equalToWhenPresent(row::getAddr1)
                .set(addr2).equalToWhenPresent(row::getAddr2)
                .set(addr3).equalToWhenPresent(row::getAddr3)
                .set(phoneNo).equalToWhenPresent(row::getPhoneNo)
                .set(zipCd).equalToWhenPresent(row::getZipCd)
                .set(email).equalToWhenPresent(row::getEmail)
                .set(faxNo).equalToWhenPresent(row::getFaxNo)
                .set(respPerson).equalToWhenPresent(row::getRespPerson)
                .set(area).equalToWhenPresent(row::getArea)
                .set(rentalFee).equalToWhenPresent(row::getRentalFee)
                .set(storingFee).equalToWhenPresent(row::getStoringFee)
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

    default int updateByPrimaryKey(WarehouseInfo row) {
        return update(c ->
            c.set(deptId).equalTo(row::getDeptId)
            .set(whsName).equalTo(row::getWhsName)
            .set(whsTypeCd).equalTo(row::getWhsTypeCd)
            .set(abbr).equalTo(row::getAbbr)
            .set(addr1).equalTo(row::getAddr1)
            .set(addr2).equalTo(row::getAddr2)
            .set(addr3).equalTo(row::getAddr3)
            .set(phoneNo).equalTo(row::getPhoneNo)
            .set(zipCd).equalTo(row::getZipCd)
            .set(email).equalTo(row::getEmail)
            .set(faxNo).equalTo(row::getFaxNo)
            .set(respPerson).equalTo(row::getRespPerson)
            .set(area).equalTo(row::getArea)
            .set(rentalFee).equalTo(row::getRentalFee)
            .set(storingFee).equalTo(row::getStoringFee)
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
        );
    }

    default int updateByPrimaryKeySelective(WarehouseInfo row) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return update(c ->
            c.set(deptId).equalToWhenPresent(row::getDeptId)
            .set(whsName).equalToWhenPresent(row::getWhsName)
            .set(whsTypeCd).equalToWhenPresent(row::getWhsTypeCd)
            .set(abbr).equalToWhenPresent(row::getAbbr)
            .set(addr1).equalToWhenPresent(row::getAddr1)
            .set(addr2).equalToWhenPresent(row::getAddr2)
            .set(addr3).equalToWhenPresent(row::getAddr3)
            .set(phoneNo).equalToWhenPresent(row::getPhoneNo)
            .set(zipCd).equalToWhenPresent(row::getZipCd)
            .set(email).equalToWhenPresent(row::getEmail)
            .set(faxNo).equalToWhenPresent(row::getFaxNo)
            .set(respPerson).equalToWhenPresent(row::getRespPerson)
            .set(area).equalToWhenPresent(row::getArea)
            .set(rentalFee).equalToWhenPresent(row::getRentalFee)
            .set(storingFee).equalToWhenPresent(row::getStoringFee)
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
        );
    }
}