package com.ruoyi.gateway.config.properties;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * @author ruoyi
 */
public class DynamicRoutes
{
    private List<RouteDefinition> routes;
    public void setRoutes(List<RouteDefinition> routes)
    {
        this.routes = routes;
    }
    public List<RouteDefinition> getRoutes()
    {
        return this.routes;
    }

}
