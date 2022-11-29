package com.ruoyi.common.security.config;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * =====================================网关鉴权使用说明=======================================
 * 场景：
 *      微服务部署在内网，确定安全，无需在每个微服务都实现一次权限控制的逻辑，可以在网关层面实现统一鉴权
 * 使用方式：
 *      1、在每个微服务的配置文件中添加参数：security.aspect.enabled: false 关闭系统默认的通过注解方式鉴权，默认开启
 *      2、在每个微服务的配置文件中添加参数：routePrefix: 值为网关中微服务匹配的路由地址，例如: /auth
 *      3、在网关配置文件中添加参数：security.gateway.enabled: true 启用网关统一鉴权，默认关闭
 *
 * 通过反射扫描所有控制器，缓存所有控制器的映射路径以及对应的权限注解，缓存到redis，方便网关鉴权
 */
@ConditionalOnProperty(prefix = "security.gateway", name = "enabled", havingValue = "true")
public class PathPermissionMappingConfig {
    @Value("${routePrefix}")
    private String routePrefix;

    @PostConstruct
    public PathPermissionMappingConfig execute() {
        RedisService redisService = SpringUtils.getBean(RedisService.class);
        RequestMappingHandlerMapping bean = SpringUtils.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        /**
         * 路径->权限字符串映射，例如 /user/list_GET->system:user:list
         */
        Map<String, String> pathPermsMap = new TreeMap<>();

        handlerMethods.forEach((k, v) -> {
            RequiresRoles requiresRoles = v.getMethodAnnotation(RequiresRoles.class);
            RequiresPermissions requiresPermissions = v.getMethodAnnotation(RequiresPermissions.class);

            Set<RequestMethod> methods = k.getMethodsCondition().getMethods();
            Set<String> patternValues = k.getPatternValues();
            /**
             * @RequestMapping注解
             */
            if(methods.isEmpty()) {
                methods = new HashSet<>();
                methods.addAll(Arrays.asList(RequestMethod.GET, RequestMethod.POST));
            }

            if(requiresPermissions == null && requiresRoles == null) {
                addPathPermsMap(SecurityConstants.ROLE_ANON, pathPermsMap, methods, patternValues);
            }
            if(requiresPermissions != null) {
                for (String perms : requiresPermissions.value()) {
                    addPathPermsMap(perms, pathPermsMap, methods, patternValues);
                }
            }
            if(requiresRoles != null) {
                for (String role : requiresRoles.value()) {
                    addPathPermsMap(SecurityConstants.ROLE_PREFIX + role, pathPermsMap, methods, patternValues);
                }
            }
        });
        System.out.println("pathPermsMap = " + pathPermsMap);
        redisService.setCacheMap(SecurityConstants.PATH_PERMISSION_MAP, pathPermsMap);
        return this;
    }

    /**
     * 一个path对应多个perms
     * @param perms
     * @param pathPermsMap
     * @param methods
     * @param patternValues
     */
    private void addPathPermsMap(String perms, Map<String, String> pathPermsMap, Set<RequestMethod> methods, Set<String> patternValues) {
        for (RequestMethod method : methods) {
            for (String patternValue : patternValues) {
                String key = routePrefix + patternValue + "_" + method.name();
                pathPermsMap.put(key, perms);
            }
        }
    }

}
