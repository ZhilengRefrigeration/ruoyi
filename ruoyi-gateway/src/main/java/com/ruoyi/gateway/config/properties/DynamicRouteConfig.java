package com.ruoyi.gateway.config.properties;

import com.alibaba.cloud.nacos.NacosConfigManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ruoyi
 */
@Configuration
@ConditionalOnProperty(prefix = "gateway.dynamicRoute", name = "enabled", havingValue = "true")
public class DynamicRouteConfig
{
    @Value("${gateway.dynamicRoute.dataId}")
    private String dataId;
    @Value("${gateway.dynamicRoute.groupId:DEFAULT_GROUP}")
    private String groupId;
    @Bean
    public NacosRouteDefinitionLocator nacosRouteDefinitionLocator(ApplicationEventPublisher publisher, NacosConfigManager nacosConfigManager)
    {
        return new NacosRouteDefinitionLocator(publisher, nacosConfigManager, dataId, groupId);
    }
}
