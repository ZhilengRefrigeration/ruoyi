package com.ruoyi.system.configProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("xxl.job")
@RefreshScope
public class XxlJobProperties {

    /**
     * admin : {"addresses":"http://192.168.2.165:32530/xxl-job-admin"}
     * executor : {"ip":null,"port":9999,"logpath":"/data/applogs/xxl-job/jobhandler","appname":"xxl-job-001"}
     * accessToken : null
     */

    private AdminBean admin;
    private ExecutorBean executor;
    private String accessToken;


    @Setter
    @Getter
    public static class AdminBean {
        /**
         * addresses : http://192.168.2.165:32530/xxl-job-admin
         */

        private String addresses;


    }
    @Setter
    @Getter
    public static class ExecutorBean {
        /**
         * ip : null
         * port : 9999
         * logpath : /data/applogs/xxl-job/jobhandler
         * appname : xxl-job-001
         */

        private String ip;
        private int port;
        private String logpath;
        private String appname;
        private int logretentiondays;
    }
}
