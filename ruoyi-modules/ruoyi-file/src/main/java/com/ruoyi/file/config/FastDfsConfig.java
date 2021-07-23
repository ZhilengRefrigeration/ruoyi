package com.ruoyi.file.config;

import com.github.tobato.fastdfs.FdfsClientConstants;
import com.github.tobato.fastdfs.domain.conn.PooledConnectionFactory;
import com.ruoyi.file.service.FastDfsServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里 fastdfs 配置
 * @author dazer
 * @see FastDfsServiceImpl
 *  FastDFS配置 其他参数见：{@link PooledConnectionFactory}
 *
 *  使用: Docker部署FastDFS（附示例代码） https://www.cnblogs.com/cao-lei/p/13470695.html
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
    public String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
