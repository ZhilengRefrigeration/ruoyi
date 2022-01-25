package com.ruoyi.file.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ruoyi.file.config.AliyunOssProperties;
import com.ruoyi.file.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 阿里云oss文件上传实现
 *
 * @author xiejs
 * @since 2022-01-25
 */
@Service
@Primary
public class AliyunOssFileServiceImpl implements ISysFileService {


    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        Assert.notNull(file, "file is null");
        try {
            String endpoint = aliyunOssProperties.getEndpoint();
            String keyId = aliyunOssProperties.getKeyId();
            String keySecret = aliyunOssProperties.getKeySecret();
            String bucketName = aliyunOssProperties.getBucketName();
            OSS ossClient = new OSSClientBuilder().build(endpoint,
                    keyId, keySecret);
            //获取流
            InputStream is = file.getInputStream();
            //获取文件后缀
            String extension = FileUploadUtils.getExtension(file);
            //获取文件名称
            String fileName = getDataTime() + "." + extension;
            //执行文件上传         bucket名称  文件名称  文件流
            ossClient.putObject(bucketName, fileName, is);
            //关闭ossClient
            ossClient.shutdown();
            //拼接文件地址
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成一个当前日期文件名
     *
     * @return 文件名
     */
    private String getDataTime() {
        String today = DateUtil.format(new Date(), "yyyy-MM");
        String time = DateUtil.formatDateTime(new Date());
        int random = RandomUtil.randomInt(100, 10000);
        //防止同一时间生成文件名重复
        return today + "/" + time + "-" + random;
    }
}
