package com.ruoyi.file.service;

import com.ruoyi.common.core.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.core.exception.file.InvalidExtensionException;
import com.ruoyi.common.core.utils.file.MimeTypeUtils;
import com.ruoyi.file.utils.FileUploadUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ruoyi.file.utils.FileUploadUtils.assertAllowed;
import static com.ruoyi.file.utils.FileUploadUtils.extractFilename;

/**
 * 【DFS】 = Distributed file system 比 【Sys File】 名称要容易理解
 * 文件上传接口
 * 1: default: 最原始的java文件上传
 * 2: ftp 使用ftp模拟文件服务器； 如：iis、linux ftp、vsftpd、FileZilla Server，需要自己搭建服务
 * 3: FastDfs 是淘宝开源的分布式文件系统； 淘宝 开发的分布式 dfs, 需要自己搭建服务 (FastDFS)
 * 4: minio 轻量级分布式文件系统； 类似一个阿里云oss、腾讯COS的一个开源、轻量级别的对象存储付;
 * 5: aliyun oss；  aliyun oss  https://help.aliyun.com/learn/learningpath/oss.html ,需要购买
 * 6: CEPH 分布式大数据文件存储系统 http://docs.ceph.org.cn/
 * @author ruoyi
 */
public interface IDfsService
{
    /**
     * 允许上传文件存放的目录
     * 不同项目，这里可能做不同的修改；不过不想区分，就default;
     * 项目稍微大一些，如果不区分目录，后期要做删除 or 迁移就很麻烦；
     */
     String[] DEFAULT_MODULES_NAME = {
            // 图片
            "default", "banner", "product", "images", "music",
            // pdf
            "pdf" };

    /**
     * 文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
     String uploadFile(MultipartFile file) throws Exception;

    String uploadFile(MultipartFile file, String modules) throws Exception;

    /**
     * 删除文件
     *
     * @param fileUrl 文件访问地址,全路径或者不是全路径都可以
     * @return
     */
    boolean deleteFile(String fileUrl);

    /**
     * 获取文件占用空间
     * 别名：objectsCapacity
     * @return 文件大小字符串，eg: 100MB、2G
     */
    String objectsCapacityStr();

    /**
     * 校验文件名称长度 & 校验文件大小 & 校验上传的目录是否是项目中注册了的 & 返回新的文件名称
     *
     * @param file    文件
     * @param modules 模块，这里作为上传的文件夹使用;eg: 项目中有banner、video、music、txt、product、default 多个模块，不同模块存放到不同文件夹中；
     * @return 新的系统生成的文件名称
     * @throws InvalidExtensionException
     */
    default String validateModule(MultipartFile file, String modules) throws InvalidExtensionException {
        Objects.requireNonNull(file, "文件不能为空！");
        modules = StringUtils.defaultString(modules, "default");

        //1、这里校验上传文件的模块，如果没有注册，直接报错
        if (!Arrays.stream(DEFAULT_MODULES_NAME).collect(Collectors.toList()).contains(modules)) {
            throw new RuntimeException("上传模块" + modules + "不存在，请现在 'FolderPath.UploadModules'中注册. 枚举值，请参见Home接口:api/getFolderPath");
        }

        // 2、校验文件名称长度
        /// String ext = (String.valueOf(file.getOriginalFilename()).substring(String.valueOf(file.getOriginalFilename()).lastIndexOf("."))).toLowerCase();
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        // 3、文件大小校验
        assertAllowed(file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        return modules + "/" + extractFilename(file);
    }
}
