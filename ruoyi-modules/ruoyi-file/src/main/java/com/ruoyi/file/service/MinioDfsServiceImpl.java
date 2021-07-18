package com.ruoyi.file.service;

import cn.hutool.extra.spring.SpringUtil;
import com.ruoyi.common.core.exception.CustomException;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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
 * 官网博客： http://docs.minio.org.cn/docs/master/java-client-quickstart-guidec
 * 在springboot中使用Minio8 https://springboot.io/t/topic/3109
 * 1、上传操作：putObject
 * 2、删除操作：removeObject
 * ==================
 * 3、预签(Presigned)操作：【设置访问过期时间】：【支持对url进行鉴权：【sts】【临时授权】】presignedGetObject
 * presignedGetObject(String bucketName, String objectName, Integer expires)
 * http://docs.minio.org.cn/docs/master/java-client-api-reference#presignedGetObject
 * MinIO STS快速入门指南 http://docs.minio.org.cn/docs/master/minio-sts-quickstart-guide
 */
@Primary
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
        validateModule(file ,modules);
        String fileName = StringUtils.defaultString(modules, "default") + "/" +  extractFileName(file);
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
        //return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
        //形如：https://image.bj.gov.cn/appt-file/dev/default/2021/07/18/f4243eb2-06a1-4304-bdfc-e2964b8721bb.jpeg
        return minioConfig.getDomain() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        RemoveObjectArgs args = RemoveObjectArgs.builder().
                bucket(minioConfig.getBucketName()).
                object(getStorePath(fileUrl)).
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

    /**
     * 转换url，为原始的key
     *
     * @param filePath https://image.bj.gov.cn/appt-file/dev/default/2021/07/18/f4243eb2-06a1-4304-bdfc-e2964b8721bb.jpeg
     * @return dev/default/2021/07/18/f4243eb2-06a1-4304-bdfc-e2964b8721bb.jpeg
     */
    private String getStorePath(String filePath) {
        String oldPath = filePath;
        // 处理方式1
        String publicPath3 = minioConfig.getDomain() + "/" + minioConfig.getBucketName() + "/";
        filePath = filePath.replace(publicPath3, "");

        if (oldPath.equals(filePath)) {
            // 处理方式2
            String publicPath4 = minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/";
            filePath = filePath.replace(publicPath4, "");
        }
        return filePath;
    }
}
