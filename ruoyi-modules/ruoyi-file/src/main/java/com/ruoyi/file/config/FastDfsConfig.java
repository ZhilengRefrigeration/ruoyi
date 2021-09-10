package com.ruoyi.file.config;

import com.github.tobato.fastdfs.FdfsClientConstants;
import com.github.tobato.fastdfs.domain.conn.PooledConnectionFactory;
import com.ruoyi.file.service.FastDfsSysFileServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里 fastdfs 配置
 * @author dazer
 * @see FastDfsSysFileServiceImpl
 *  FastDFS配置 其他参数见：{@link PooledConnectionFactory}
 *
 *  使用: Docker部署FastDFS（附示例代码） https://www.cnblogs.com/cao-lei/p/13470695.html
 *  github 地址：https://github.com/tobato/FastDFS_Client
 */
@RefreshScope
@Configuration
@ConfigurationProperties(
        prefix =  FdfsClientConstants.ROOT_CONFIG_PREFIX
)
public class FastDfsConfig {
    /**
     * 文件对外访问域名or ip
     * FastDFS配置 其他参数见：{@link PooledConnectionFactory}
     * //@Value("${fdfs.domain}")
     */
    private String domain;

    /**
     * 生成防盗链token的加密key；注意保密
     * fastdfs 内置的功能，需要和 FastDFS 【etc/fdfs/http.conf】 【http.anti_steal.secret_key】保持一致
     */
    private String tokenSecretKey;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTokenSecretKey() {
        return tokenSecretKey;
    }

    public void setTokenSecretKey(String tokenSecretKey) {
        this.tokenSecretKey = tokenSecretKey;
    }
}
