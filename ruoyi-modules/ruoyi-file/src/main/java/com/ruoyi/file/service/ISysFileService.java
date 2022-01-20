package com.ruoyi.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口
 *
 * @author ruoyi
 */
public interface ISysFileService
{
    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception;

    /**
     * 对访问url进行签名加密 别名 【临时安全凭证】
     * 兼容 AWS Security Token Service (STS) 的联合身份临时安全凭证 (federation token) ，更多详细信息请查阅
     *
     * 1、aliyun oss 叫做；STS服务 临时安全令牌（Security Token Service，STS） 说明：https://help.aliyun.com/document_detail/28761.html?spm=a2c4g.11186623.6.880.22bd2fe5pL1d39
     * 2、minio 叫做；resignedGetObject 临时安全令牌（Security Token Service，STS）; 【Presigned presignedGetObject 预签】
     *    http://docs.minio.org.cn/docs/master/minio-sts-quickstart-guide
     *    minio SDKS Java Client API参考文档 http://docs.minio.org.cn/docs/master/java-client-api-reference
     * 3、qiniu ；七牛云存储； 下载凭证(如果Bucket设置成私有，必须要有 下载凭证)，路径：【对象存储==》使用指南===》安全机制===》 下载凭证】 https://developer.qiniu.com/kodo/1202/download-token
     *   https://developer.qiniu.com/kodo/5914/s3-compatible-sts
     * 4、腾讯 临时密钥（临时访问凭证） GetFederationToken 临时密钥生成及使用指引 https://cloud.tencent.com/document/product/436/14048?from=10680
     * 5、fastdfs 防掉链 前提，需要在 fastdfs上面配置 https://www.cnblogs.com/xiaolinstudy/p/9341779.html
     * @param fileUrl 文件访问地址,全路径或者不是全路径都可以
     * @return 返回签名后的url
     */
    String presignedUrl(String fileUrl);
}
