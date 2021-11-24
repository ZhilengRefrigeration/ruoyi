package com.ruoyi.file.config;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qcloud.cos.COSClient;
import com.ruoyi.file.service.*;
import io.minio.MinioClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义webmvc config
 *
 * @author yabo
 * @date 2021-11-18
 * <p>
 * private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
 * ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter.getWebBindingInitializer();
 * if (initializer.getConversionService() != null) {
 * GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
 * genericConversionService.addConverter(new CustomConverter());
 * }
 * @see ISysFileService
 * // 具体实现config
 * @see AliyunOssConfig 阿里云oss
 * @see CephConfig ceph文件服务
 * @see FastDfsConfig fastdfs
 * @see FtpConfig ftp
 * @see MinioConfig minio
 * @see QiniuKodoConfig 七牛kodo
 * @see ResourcesConfig tomcat映射目录
 * @see TencentCosConfig 腾讯云cos文件服务器
 */
@RefreshScope
@ConfigurationProperties(prefix = WebMvcConfig.PREFIX)
@Configuration
public class WebMvcConfig implements WebMvcConfigurer, ApplicationContextAware {
    public static final String PREFIX = "file";
    /***
     * 指定文件服务器类型
     */
    protected ISysFileService.DfsTypeEnum fileServerType = ISysFileService.DfsTypeEnum.DEFAULT;

    @Primary
    @Description("dfs bean.....")
    @ConditionalOnMissingBean
    @Bean(name = "ISysFileService")
    public ISysFileService registFileBean(FastFileStorageClient fastFileStorageClient, FastDfsConfig fastDfsConfig,
                                          FtpConfig ftpConfig,
                                          AliyunOssConfig aliyunOssConfig,
                                          CephConfig cephConfig,
                                          MinioConfig minioConfig, MinioClient minioClient,
                                          COSClient cosClient, TencentCosConfig tencentCosConfig,
                                          QiniuKodoConfig qiniuKodoConfig,
                                          ResourcesConfig resourcesConfig) {
        if (ISysFileService.DfsTypeEnum.FASTDFS.equals(fileServerType)) {
            return new FastDfsSysFileServiceImpl(fastFileStorageClient, fastDfsConfig);
        } else if (ISysFileService.DfsTypeEnum.FTP.equals(fileServerType)) {
            return new FtpFileServiceImpl(ftpConfig);
        } else if (ISysFileService.DfsTypeEnum.ALIYUN_OSS.equals(fileServerType)) {
            return new AliyunOssDsfServiceImpl(aliyunOssConfig);
        } else if (ISysFileService.DfsTypeEnum.CEPH.equals(fileServerType)) {
            return new CephSysFileServiceImpl(cephConfig);
        } else if (ISysFileService.DfsTypeEnum.MINIO.equals(fileServerType)) {
            return new MinioSysFileServiceImpl(minioConfig, minioClient);
        } else if (ISysFileService.DfsTypeEnum.TENCENT_COS.equals(fileServerType)) {
            return new TencentCosServiceImpl(cosClient, tencentCosConfig);
        } else if (ISysFileService.DfsTypeEnum.QINIU_KODO.equals(fileServerType)) {
            return new QiniuSysFileServiceImpl(qiniuKodoConfig);
        }
        return new LocalSysFileServiceImpl(resourcesConfig);
    }

    public void setFileServerType(ISysFileService.DfsTypeEnum fileServerType) {
        this.fileServerType = fileServerType;
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
