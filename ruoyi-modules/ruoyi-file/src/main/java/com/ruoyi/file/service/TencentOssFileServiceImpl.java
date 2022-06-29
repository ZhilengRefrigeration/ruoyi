package com.ruoyi.file.service;

import cn.hutool.core.date.DateUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.ruoyi.common.core.text.UUID;
import com.ruoyi.common.core.utils.file.FileUploadUtils;
import com.ruoyi.file.config.TencentCosProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static com.ruoyi.file.utils.OssClient.*;

/**
 * 腾讯云cos服务实现
 *
 * @author xiejs
 * @since 2022-06-29
 */
@Service
@Primary
public class TencentOssFileServiceImpl implements ISysFileService {

    @Autowired
    private TencentCosProperties tencentCosProperties;

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        try {
            COSClient cosClient = this.createCOSClient();
            String bucketName = tencentCosProperties.getBucketName();
            String endpoint = tencentCosProperties.getEndpoint();

            //获取流
            InputStream is = file.getInputStream();
            //获取文件后缀
            String extension = FileUploadUtils.getExtension(file);
            //获取文件名称
            String fileName = this.getDataTime() + DOT + extension;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName, fileName, is, objectMetadata
            );

            cosClient.putObject(putObjectRequest);

            cosClient.shutdown();

            //拼接文件地址
            return HTTPS + bucketName + DOT + endpoint + SLASH + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeFile(String url) {
        COSClient cosClient = this.createCOSClient();

        String endpoint = tencentCosProperties.getEndpoint();
        String bucketName = tencentCosProperties.getBucketName();

        String host = HTTPS + bucketName + DOT + endpoint + SLASH;

        //如果路径中不包含host
        if (!url.contains(host)) {
            return;
        }

        String objectName = url.substring(host.length());

        cosClient.deleteObject(bucketName, objectName);
        cosClient.shutdown();

    }


    /**
     * 生成一个当前日期文件名
     *
     * @return 文件名
     */
    private String getDataTime() {
        String today = DateUtil.format(new Date(), "yyyy-MM");
        return today + SLASH + UUID.randomUUID();
    }


    /**
     * 创建客户端
     *
     * @return cosClient
     */
    private COSClient createCOSClient() {
        //初始化用户身份信息
        String secretId = tencentCosProperties.getSecretId();
        String secretKey = tencentCosProperties.getSecretKey();
        String regionValue = tencentCosProperties.getRegion();
        BasicCOSCredentials credentials = new BasicCOSCredentials(secretId, secretKey);

        //设置bucket的地域
        Region region = new Region(regionValue);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);

        //生成cos客户端
        return new COSClient(credentials, clientConfig);
    }
}
