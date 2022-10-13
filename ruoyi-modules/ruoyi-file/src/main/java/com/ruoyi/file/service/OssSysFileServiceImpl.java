package com.ruoyi.file.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ruoyi.file.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class OssSysFileServiceImpl implements ISysFileService{
    /**
     * oss公钥
     */
    @Value("${oss.access-key}")
    public String accessKey;

    /**
     * oss私钥
     */
    @Value("${oss.secret-key}")
    public String secretKey;

    /**
     * oss存储桶域名地址
     */
    @Value("${oss.endpoint}")
    public String endpoint;

    /**
     * oss存储桶名称
     */
    @Value("${oss.bucketName}")
    public String bucketName;

    /**
     * OSS文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);
        String filePath = FileUploadUtils.uploadFileByOss(ossClient, bucketName, file);
        return "https://"+bucketName+"."+endpoint+"/"+filePath;
    }
}
