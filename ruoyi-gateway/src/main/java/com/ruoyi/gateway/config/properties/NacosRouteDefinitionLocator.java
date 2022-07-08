package com.ruoyi.gateway.config.properties;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.ruoyi.common.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.yaml.snakeyaml.Yaml;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;

/**
 * @author ruoyi
 */
public class NacosRouteDefinitionLocator implements RouteDefinitionLocator
{
    private static final Logger log = LoggerFactory.getLogger(NacosRouteDefinitionLocator.class);
    private String dataId;
    private String groupId;
    private ApplicationEventPublisher publisher;
    private NacosConfigManager nacosConfigManager;
    private Boolean isReload = false;
    private List<RouteDefinition> routeDefinitions;

    public NacosRouteDefinitionLocator(ApplicationEventPublisher publisher, NacosConfigManager nacosConfigManager, String dataId, String groupId)
    {
        this.publisher = publisher;
        this.nacosConfigManager = nacosConfigManager;
        this.dataId = dataId;
        this.groupId = groupId;
        addListener();
    }

    private void addListener()
    {
        try
        {
            nacosConfigManager.getConfigService().addListener(dataId, groupId, new Listener()
            {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    isReload = true;
                    publisher.publishEvent(new RefreshRoutesEvent(this));
                }
            });
        }
        catch (NacosException e)
        {
            log.error("nacos-route-config-refresh-error", e);
        }
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        try
        {
            if(isReload || this.routeDefinitions == null)
            {
                String content = nacosConfigManager.getConfigService().getConfig(this.dataId, this.groupId, 5000);
                this.routeDefinitions = parseRoutesFromYaml(content);
                isReload = false;
            }
        }
        catch (NacosException e)
        {
            log.error("reload route error", e);
        }
        return Optional.ofNullable(routeDefinitions).map(Flux::fromIterable).orElse(Flux.fromIterable(new ArrayList<>(0)));
    }
    /**
     * yml方式
     * @param content 动态路由文本内容
     * @return 动态路由列表
     */
    private List<RouteDefinition> parseRoutesFromYaml(String content)
    {
        if (StringUtils.isNotEmpty(content))
        {
            return new Yaml().loadAs(content, DynamicRoutes.class).getRoutes();
        }
        return new ArrayList<>(0);
    }
}
