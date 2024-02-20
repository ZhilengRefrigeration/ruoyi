package com.ruoyi.file.mapper;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import java.sql.JDBCType;
import java.util.Date;

public final class SysFileDynamicSqlSupport {
    public static final SysFile sysFile = new SysFile();

    /**
     *   文件ID
     */
    public static final SqlColumn<String> fileId = sysFile.fileId;

    /**
     *   保存的文件名称
     */
    public static final SqlColumn<String> savedName = sysFile.savedName;

    /**
     *   原始文件名称
     */
    public static final SqlColumn<String> originalName = sysFile.originalName;

    /**
     *   文件路径
     */
    public static final SqlColumn<String> filePath = sysFile.filePath;

    /**
     *   文件后缀
     */
    public static final SqlColumn<String> extension = sysFile.extension;

    /**
     *   存储方式
     */
    public static final SqlColumn<String> storageType = sysFile.storageType;

    /**
     *   获取文件的URL
     */
    public static final SqlColumn<String> requestUrl = sysFile.requestUrl;

    /**
     *   文件大小(Byte)
     */
    public static final SqlColumn<Long> fileSize = sysFile.fileSize;

    /**
     *   创建者
     */
    public static final SqlColumn<String> createBy = sysFile.createBy;

    /**
     *   创建时间
     */
    public static final SqlColumn<Date> createTime = sysFile.createTime;

    /**
     *   更新者
     */
    public static final SqlColumn<String> updateBy = sysFile.updateBy;

    /**
     *   更新时间
     */
    public static final SqlColumn<Date> updateTime = sysFile.updateTime;

    /**
     *   备注
     */
    public static final SqlColumn<String> remark = sysFile.remark;

    public static final class SysFile extends AliasableSqlTable<SysFile> {
        public final SqlColumn<String> fileId = column("file_id", JDBCType.VARCHAR);

        public final SqlColumn<String> savedName = column("saved_name", JDBCType.VARCHAR);

        public final SqlColumn<String> originalName = column("original_name", JDBCType.VARCHAR);

        public final SqlColumn<String> filePath = column("file_path", JDBCType.VARCHAR);

        public final SqlColumn<String> extension = column("extension", JDBCType.VARCHAR);

        public final SqlColumn<String> storageType = column("storage_type", JDBCType.VARCHAR);

        public final SqlColumn<String> requestUrl = column("request_url", JDBCType.VARCHAR);

        public final SqlColumn<Long> fileSize = column("file_size", JDBCType.BIGINT);

        public final SqlColumn<String> createBy = column("create_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> updateBy = column("update_by", JDBCType.VARCHAR);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public SysFile() {
            super("sys_file", SysFile::new);
        }
    }
}