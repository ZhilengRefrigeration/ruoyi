package com.ruoyi.file.service;

import cn.hutool.extra.spring.SpringUtil;
import com.ruoyi.common.core.exception.CustomException;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.file.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Minio 文件存储
 * 
 * @author ruoyi
 */
@Service
public class MinioDfsServiceImpl implements IDfsService
{
    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient client;

    /**
     * 本地文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception
    {
        return this.uploadFile(file, null);
    }

    @Override
    public String uploadFile(MultipartFile file, String modules) throws Exception {
        String fileName = validateModule(file ,modules);
        boolean isProd = "prod".equalsIgnoreCase(SpringUtil.getActiveProfile());
        if (!isProd) {
            fileName = SpringUtil.getActiveProfile() + "/" + fileName;
        }

        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        client.putObject(args);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        RemoveObjectArgs args = RemoveObjectArgs.builder().
                bucket(minioConfig.getBucketName()).
                object(fileUrl).
                build();
        try {
            client.removeObject(args);
            return true;
        } catch (ErrorResponseException |
                InsufficientDataException |
                InternalException |
                InvalidKeyException |
                InvalidResponseException |
                IOException |
                NoSuchAlgorithmException |
                ServerException |
                XmlParserException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String objectsCapacityStr() {
        throw new CustomException("minio存储-获取文件占用空间功能，敬请期待");
    }
}
