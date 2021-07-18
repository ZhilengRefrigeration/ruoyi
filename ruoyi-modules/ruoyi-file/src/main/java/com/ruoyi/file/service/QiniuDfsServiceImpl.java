package com.ruoyi.file.service;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.ruoyi.common.core.exception.CustomException;
import com.ruoyi.file.config.QiniuKodoConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 七牛云 kodo 文件存储封装
 *
 * @author dazer
 * @see QiniuKodoConfig
 * 参考：https://developer.qiniu.com/kodo/1239/java#upload-stream
 * 路径：【对象存储==>JAVASDK==>文件上传==>数据流上传】
 */
//@Primary
@Service
public class QiniuDfsServiceImpl implements IDfsService {
    private static final Logger log = LoggerFactory.getLogger(QiniuDfsServiceImpl.class);
    @Autowired
    private QiniuKodoConfig qiniuKodoConfig;

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        return this.uploadFile(file, null);
    }

    @Override
    public String uploadFile(MultipartFile file, String modules) throws Exception {
        //key: 这里不能以/开头
        validateModule(file, modules);
        String newName = extractFileNameSimple(file);
        //key: 这里不能以/开头
        String requestKey = "upload/" + StringUtils.defaultString(modules, "default") + "/" + newName;
        //这里增加一个前缀区分一下是测试环境还是正式环境
        boolean isProd = "prod".equalsIgnoreCase(SpringUtil.getActiveProfile());
        if (!isProd) {
            requestKey = SpringUtil.getActiveProfile() + "/" + requestKey;
        }

        Configuration cfg = new Configuration(getCurrentRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        String accessKey = qiniuKodoConfig.getAccessKey();
        String secretKey = qiniuKodoConfig.getSecretKey();
        String bucket = qiniuKodoConfig.getBucketName();
        String domain = qiniuKodoConfig.getDomain();
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = requestKey;

        InputStream byteInputStream = file.getInputStream();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            /// http://guangdong-oss.ityun.ltd/dev/upload/default/20210717-133e3b4a-6aad-418c-a040-7161fa37ee49.jpeg
            return (domain + "/" + putRet.key).replace("//", "/") ;
        } catch (QiniuException ex) {
            Response r = ex.response;
            String json = null;
            try {
                json = r.bodyString();
            } catch (QiniuException e) {
                log.error(e.getMessage());
            }
            log.error("【七牛云】-上传文件- 失败，原因1：" + r);
            log.error("【七牛云】-上传文件-失败，原因2：" + json);
        }
        return null;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        Configuration cfg = new Configuration(getCurrentRegion());

        //...其他参数参考类注释
        String accessKey = qiniuKodoConfig.getAccessKey();
        String secretKey = qiniuKodoConfig.getSecretKey();
        String bucket = qiniuKodoConfig.getBucketName();
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);

        String key = getStorePath(fileUrl);
        try {
            bucketManager.delete(bucket, key);
            return true;
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            log.error("【七牛云】-删除文件-失败 code：" + ex.code() + "=>" + ex.response.toString());
        }
        return false;
    }

    @Override
    public String objectsCapacityStr() {
        throw new CustomException("七牛云-获取文件占用空间功能，敬请期待");
    }

    /**
     * 转换url，为原始的key
     *
     * @param filePath http://guangdong-oss.ityun.ltd/upload/default/header.jpg
     * @return upload/default/header.jpg
     *
     * http://qwc2geifw.hn-bkt.clouddn.com/upload/default/header.jpg
     * ==> upload/default/header.jpg
     */
    private String getStorePath(String filePath) {
        String domain = qiniuKodoConfig.getDomain();
        String publicPath3 = domain + "/";
        filePath = filePath.replace(publicPath3, "");
        return filePath;
    }

    /**
     * 存储区域 https://developer.qiniu.com/kodo/1671/region-endpoint-fq
     * new Region.Builder()
     * https://developer.qiniu.com/kodo/1239/java#upload-stream
     * 自定义区域域名:  搜索【其中关于Region对象和机房的关系如下：】
     * @return 存储区域
     */
    private Region getCurrentRegion() {
        /// 其中关于Region对象和机房的关系如下：
        ///Region region = Region.autoRegion();
        ///Region region = Region.region0();
        ///Region region = Region.huanan();
        //构造一个带指定 Region 对象的配置类
        return Region.autoRegion();
    }
}
