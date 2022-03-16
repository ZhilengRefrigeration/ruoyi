package com.ruoyi.file.service;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSS;
import com.ruoyi.common.core.text.UUID;
import com.ruoyi.file.utils.FileUploadUtils;
import com.ruoyi.file.utils.OssClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static com.ruoyi.file.utils.OssClient.*;

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
    private OssClient ossClient;


    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        Assert.notNull(file, "file is null");
        try {
            String endpoint = OssClient.endpoint;
            String bucketName = OssClient.bucketName;
            OSS oss = ossClient.getOssClient();
            //获取流
            InputStream is = file.getInputStream();
            //获取文件后缀
            String extension = FileUploadUtils.getExtension(file);
            //获取文件名称
            String fileName = this.getDataTime() + DOT + extension;
            //执行文件上传         bucket名称  文件名称  文件流
            oss.putObject(bucketName, fileName, is);
            //关闭ossClient
            oss.shutdown();
            //拼接文件地址
            return HTTPS + bucketName + DOT + endpoint + SLASH + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeFile(String url) {
        String endpoint = OssClient.endpoint;
        String bucketName = OssClient.bucketName;
        String host = HTTPS + bucketName + DOT + endpoint + SLASH;

        //如果路径中不包含host
        if (!url.contains(host)) {
            return;
        }

        String objectName = url.substring(host.length());

        OSS oss = ossClient.getOssClient();

        //执行删除
        oss.deleteObject(bucketName, objectName);

        //关闭ossClient
        oss.shutdown();

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

}
