package com.ruoyi.common.services.mapper;

import com.ruoyi.common.security.utils.SecurityUtilsExt;
import com.ruoyi.common.services.domain.SysFile;
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

import static com.ruoyi.common.services.mapper.SysFileDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface SysFileMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<SysFile>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(fileId, savedName, originalName, filePath, extension, storageType, requestUrl, fileSize, createBy, createTime, updateBy, updateTime, remark);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysFileResult", value = {
        @Result(column="file_id", property="fileId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="saved_name", property="savedName", jdbcType=JdbcType.VARCHAR),
        @Result(column="original_name", property="originalName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="extension", property="extension", jdbcType=JdbcType.VARCHAR),
        @Result(column="storage_type", property="storageType", jdbcType=JdbcType.VARCHAR),
        @Result(column="request_url", property="requestUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_size", property="fileSize", jdbcType=JdbcType.BIGINT),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<SysFile> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysFileResult")
    Optional<SysFile> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysFile, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysFile, completer);
    }

    default int deleteByPrimaryKey(String fileId_) {
        return delete(c -> 
            c.where(fileId, isEqualTo(fileId_))
        );
    }

    default int insert(SysFile row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, sysFile, c ->
            c.map(fileId).toProperty("fileId")
            .map(savedName).toProperty("savedName")
            .map(originalName).toProperty("originalName")
            .map(filePath).toProperty("filePath")
            .map(extension).toProperty("extension")
            .map(storageType).toProperty("storageType")
            .map(requestUrl).toProperty("requestUrl")
            .map(fileSize).toProperty("fileSize")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(remark).toProperty("remark")
        );
    }

    default int insertMultiple(Collection<SysFile> records) {
        for (SysFile row : records) {
            row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        }
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, sysFile, c ->
            c.map(fileId).toProperty("fileId")
            .map(savedName).toProperty("savedName")
            .map(originalName).toProperty("originalName")
            .map(filePath).toProperty("filePath")
            .map(extension).toProperty("extension")
            .map(storageType).toProperty("storageType")
            .map(requestUrl).toProperty("requestUrl")
            .map(fileSize).toProperty("fileSize")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(remark).toProperty("remark")
        );
    }

    default int insertSelective(SysFile row) {
        row.setCommonForInsert(SecurityUtilsExt.getUserIdStr());
        return MyBatis3Utils.insert(this::insert, row, sysFile, c ->
            c.map(fileId).toPropertyWhenPresent("fileId", row::getFileId)
            .map(savedName).toPropertyWhenPresent("savedName", row::getSavedName)
            .map(originalName).toPropertyWhenPresent("originalName", row::getOriginalName)
            .map(filePath).toPropertyWhenPresent("filePath", row::getFilePath)
            .map(extension).toPropertyWhenPresent("extension", row::getExtension)
            .map(storageType).toPropertyWhenPresent("storageType", row::getStorageType)
            .map(requestUrl).toPropertyWhenPresent("requestUrl", row::getRequestUrl)
            .map(fileSize).toPropertyWhenPresent("fileSize", row::getFileSize)
            .map(createBy).toPropertyWhenPresent("createBy", row::getCreateBy)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(updateBy).toPropertyWhenPresent("updateBy", row::getUpdateBy)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
            .map(remark).toPropertyWhenPresent("remark", row::getRemark)
        );
    }

    default Optional<SysFile> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysFile, completer);
    }

    default List<SysFile> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysFile, completer);
    }

    default List<SysFile> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysFile, completer);
    }

    default Optional<SysFile> selectByPrimaryKey(String fileId_) {
        return selectOne(c ->
            c.where(fileId, isEqualTo(fileId_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysFile, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(SysFile row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(fileId).equalTo(row::getFileId)
                .set(savedName).equalTo(row::getSavedName)
                .set(originalName).equalTo(row::getOriginalName)
                .set(filePath).equalTo(row::getFilePath)
                .set(extension).equalTo(row::getExtension)
                .set(storageType).equalTo(row::getStorageType)
                .set(requestUrl).equalTo(row::getRequestUrl)
                .set(fileSize).equalTo(row::getFileSize)
                .set(createBy).equalTo(row::getCreateBy)
                .set(createTime).equalTo(row::getCreateTime)
                .set(updateBy).equalTo(row::getUpdateBy)
                .set(updateTime).equalTo(row::getUpdateTime)
                .set(remark).equalTo(row::getRemark);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysFile row, UpdateDSL<UpdateModel> dsl) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return dsl.set(fileId).equalToWhenPresent(row::getFileId)
                .set(savedName).equalToWhenPresent(row::getSavedName)
                .set(originalName).equalToWhenPresent(row::getOriginalName)
                .set(filePath).equalToWhenPresent(row::getFilePath)
                .set(extension).equalToWhenPresent(row::getExtension)
                .set(storageType).equalToWhenPresent(row::getStorageType)
                .set(requestUrl).equalToWhenPresent(row::getRequestUrl)
                .set(fileSize).equalToWhenPresent(row::getFileSize)
                .set(createBy).equalToWhenPresent(row::getCreateBy)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(updateBy).equalToWhenPresent(row::getUpdateBy)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime)
                .set(remark).equalToWhenPresent(row::getRemark);
    }

    default int updateByPrimaryKey(SysFile row) {
        return update(c ->
            c.set(savedName).equalTo(row::getSavedName)
            .set(originalName).equalTo(row::getOriginalName)
            .set(filePath).equalTo(row::getFilePath)
            .set(extension).equalTo(row::getExtension)
            .set(storageType).equalTo(row::getStorageType)
            .set(requestUrl).equalTo(row::getRequestUrl)
            .set(fileSize).equalTo(row::getFileSize)
            .set(createBy).equalTo(row::getCreateBy)
            .set(createTime).equalTo(row::getCreateTime)
            .set(updateBy).equalTo(row::getUpdateBy)
            .set(updateTime).equalTo(row::getUpdateTime)
            .set(remark).equalTo(row::getRemark)
            .where(fileId, isEqualTo(row::getFileId))
        );
    }

    default int updateByPrimaryKeySelective(SysFile row) {
        row.setCommonForUpdate(SecurityUtilsExt.getUserIdStr());
        return update(c ->
            c.set(savedName).equalToWhenPresent(row::getSavedName)
            .set(originalName).equalToWhenPresent(row::getOriginalName)
            .set(filePath).equalToWhenPresent(row::getFilePath)
            .set(extension).equalToWhenPresent(row::getExtension)
            .set(storageType).equalToWhenPresent(row::getStorageType)
            .set(requestUrl).equalToWhenPresent(row::getRequestUrl)
            .set(fileSize).equalToWhenPresent(row::getFileSize)
            .set(createBy).equalToWhenPresent(row::getCreateBy)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(updateBy).equalToWhenPresent(row::getUpdateBy)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .set(remark).equalToWhenPresent(row::getRemark)
            .where(fileId, isEqualTo(row::getFileId))
        );
    }
}