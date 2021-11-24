package com.ruoyi.file.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.*;
import com.ruoyi.common.core.exception.CustomException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.file.config.TencentCosConfig;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * 腾讯云cos 文件服务器实现
 *
 * @author yabo
 * @see TencentCosConfig
 */
public class TencentCosServiceImpl implements ISysFileService {
    private final COSClient cosClient;
    private final TencentCosConfig config;

    public TencentCosServiceImpl(COSClient cosClient, TencentCosConfig config) {
        this.cosClient = cosClient;
        this.config = config;
    }

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        return this.uploadFile(file, null);
    }

    /**
     * 腾讯云文档中心==》对象存储==》JAVA SDK===> 上传对象
     * https://cloud.tencent.com/document/product/436/10199#.E4.B8.8A.E4.BC.A0.E5.AF.B9.E8.B1.A1
     * 上传成功示例：https://cos.ityun.ltd/dev/upload/default/20210729-2be9b217-22ef-45a2-bfda-e793ffe33f55.jpeg
     */
    @Override
    public String uploadFile(MultipartFile file, String modules) throws Exception {
        //key: 这里不能以/开头
        validateModule(file, null);
        String newName = extractFileNameSimple(file);
        //key: 这里不能以/开头
        String requestKey = "upload/" + StringUtils.defaultString(modules, "default") + "/" + newName;
        //这里增加一个前缀区分一下是测试环境还是正式环境
        boolean isProd = "prod".equalsIgnoreCase(SpringUtil.getActiveProfile());
        if (!isProd) {
            requestKey = SpringUtil.getActiveProfile() + "/" + requestKey;
        }

        if (StringUtils.isBlank(config.getBucketName())) {
            throw new CustomException("tencent cos bucket name 不能为空，请检查！");
        }
        if (StringUtils.isBlank(config.getEndpoint())) {
            throw new CustomException("tencent cos endpoint(Region) 不能为空，请检查！");
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();

        // 指定文件将要存放的存储桶
        String bucketName = config.getBucketName();
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = requestKey;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file.getInputStream(), objectMetadata);
        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return (config.getDomain() + "/" + key);
        } catch (CosClientException e) {
            e.printStackTrace();
            throw new CustomException("tencent cos upload 失败;错误代码：" + e.getErrorCode() + "；错误原因：" + e.getMessage());
        }
    }

    /**
     * 腾讯云文档中心==》对象存储==》JAVA SDK===> 删除对象
     * https://cloud.tencent.com/document/product/436/10199#.E5.88.A0.E9.99.A4.E5.AF.B9.E8.B1.A1
     */
    @Override
    public boolean deleteFile(String fileUrl) {
        if (StringUtils.isBlank(config.getBucketName())) {
            throw new CustomException("tencent cos bucket name 不能为空，请检查！");
        }

        // 指定被删除的文件在 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示删除位于 folder 路径下的文件 picture.jpg
        String key = this.getStorePath(fileUrl);
        try {
            cosClient.deleteObject(config.getBucketName(), key);
        } catch (CosClientException e) {
            e.printStackTrace();
            throw new CustomException("tencent cos 删除重新异常，请排查...");
        }
        return true;
    }

    /**
     * 腾讯云文档中心==》对象存储==》JAVA SDK===> 查询对象列表
     * 无法直接获取所有文件大小
     * https://cloud.tencent.com/document/product/436/10199#.E6.9F.A5.E8.AF.A2.E5.AF.B9.E8.B1.A1.E5.88.97.E8.A1.A8
     */
    @Override
    public String objectsCapacityStr() {
        if (StringUtils.isBlank(config.getBucketName())) {
            throw new CustomException("tencent cos bucket name 不能为空，请检查！");
        }

        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
        String bucketName = config.getBucketName();
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        // 设置bucket名称
        listObjectsRequest.setBucketName(bucketName);
        /// prefix表示列出的object的key以prefix开始
        ///listObjectsRequest.setPrefix("images/");
        /// deliter表示分隔符, 设置为/表示列出当前目录下的object, 设置为空表示列出所有的object
        ///listObjectsRequest.setDelimiter("/");
        // 设置最大遍历出多少个对象, 一次listobject最大支持1000
        listObjectsRequest.setMaxKeys(1000);
        ObjectListing objectListing = null;
        long size = 0;
        do
        {
            try {
                objectListing = cosClient.listObjects(listObjectsRequest);
            } catch (CosClientException e) {
                e.printStackTrace();
                return "";
            }
            // common prefix表示表示被delimiter截断的路径, 如delimter设置为/, common prefix则表示所有子目录的路径
            List<String> commonPrefixs = objectListing.getCommonPrefixes();
            // object summary表示所有列出的object列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                // 文件的路径key
                String key = cosObjectSummary.getKey();
                // 文件的etag
                String etag = cosObjectSummary.getETag();
                // 文件的长度
                long fileSize = cosObjectSummary.getSize();
                // 文件的存储类型
                String storageClasses = cosObjectSummary.getStorageClass();
                size += fileSize;
            }
            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        } while (objectListing.isTruncated());
        return FileUtil.readableFileSize(size);
    }

    /**
     * 获取请求预签名 URL
     * 腾讯云文档中心==》对象存储==》JAVA SDK===> 预签名URL ===> 获取请求预签名 URL
     * https://cloud.tencent.com/document/product/436/35217#.E8.8E.B7.E5.8F.96.E8.AF.B7.E6.B1.82.E9.A2.84.E7.AD.BE.E5.90.8D-url
     * @return 返回示例：https://cos.ityun.ltd/dev/upload/default/20210729-2be9b217-22ef-45a2-bfda-e793ffe33f55.jpeg?4304-bdfc-e2964b8721bb.jpeg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDX9hNAzpdUI0XyRpASj098xa7uYzOekmh%26q-sign-time%3D1627542548%3B1627574935%26q-key-time%3D1627542548%3B1627574935%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D872e77dbca69d6b67d7a0027ab41c374703994f8
     * https://tencent-cloud-cos-dazer-1253883700.cos.ap-chengdu.myqcloud.com/dev/upload/default/20210729-2be9b217-22ef-45a2-bfda-e793ffe33f55.jpeg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDX9hNAzpdUI0XyRpASj098xa7uYzOekmh%26q-sign-time%3D1627542872%3B1627575269%26q-key-time%3D1627542872%3B1627575269%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D21508af6284564532deb935e03c2226d93bc965a
     */
    @Override
    public String presignedUrl(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) {
            return fileUrl;
        }
        if (config.getExpiryDuration() == -1) {
            return fileUrl;
        }
        String signKey = "?sign=";
        if (fileUrl.contains(signKey)) {
            return fileUrl;
        }
        // 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = config.getBucketName();
        if (StringUtils.isBlank(config.getBucketName())) {
            throw new CustomException("tencent cos bucket name 不能为空，请检查！");
        }

        // 此处的key为对象键，对象键是对象在存储桶内的唯一标识
        String key = this.getStorePath(fileUrl);
        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + (config.getExpiryDuration() * 1000));
        req.setExpiration(expirationDate);
        URL url = cosClient.generatePresignedUrl(req);
        return url.toString();
    }

    /**
     * 转换url，为原始的key
     *
     * @param filePath http://guangdong-oss.ityun.ltd/upload/default/header.jpg
     * @return upload/default/header.jpg
     * <p>
     * http://qwc2geifw.hn-bkt.clouddn.com/upload/default/header.jpg
     * ==> upload/default/header.jpg
     */
    private String getStorePath(String filePath) {
        String domain = config.getDomain();
        String publicPath3 = domain + "/";
        filePath = filePath.replace(publicPath3, "");
        return filePath;
    }
}
