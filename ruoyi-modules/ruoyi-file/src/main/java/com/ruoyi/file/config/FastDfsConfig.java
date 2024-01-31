package com.ruoyi.file.config;

import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alan Scipio
 * created on 2024/1/31
 */
//@Configuration
public class FastDfsConfig {

    //TODO 未完全对接，待完善
//    @Bean
    public FastFileStorageClient fastFileStorageClient() {
        return new DefaultFastFileStorageClient();
    }

}
