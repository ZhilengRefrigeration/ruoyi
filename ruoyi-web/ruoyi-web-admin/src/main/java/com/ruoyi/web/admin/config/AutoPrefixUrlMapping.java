package com.ruoyi.web.admin.config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 按照目录结构/包名添加前缀
 *
 * @author 1763113879@qq.com
 * @version V2.1
 * @since 2.1.0 2023/11/15 14:20
 */
public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {

    // 路由映射
    static Map<String, String> routesMap = new HashMap<String, String>();

    static {
        // # 认证中心 uri: lb://ruoyi-auth
        routesMap.put("com.ruoyi.auth.controller", "/auth");
        // # 代码生成 uri: lb://ruoyi-gen
        routesMap.put("com.ruoyi.gen.controller", "/code");
        // # 定时任务 uri: lb://ruoyi-job
        routesMap.put("com.ruoyi.job.controller", "/schedule");
        // # 系统模块 uri: lb://ruoyi-system
        routesMap.put("com.ruoyi.system.controller", "/system");
        // # 文件服务 uri: lb://ruoyi-file
        routesMap.put("com.ruoyi.file.controller", "/file");

    }

    /**
     * 重写方法路由获取
     *
     * @param method
     * @param handlerType
     * @return
     */
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);

        if (Objects.nonNull(mappingInfo)) {
            String prefix = this.getPrefix(handlerType);

            if (prefix != null) {
                String[] paths = mappingInfo.getPatternValues()
                    .stream()
                    .map(path -> prefix + path)
                    .toArray(String[]::new);

                return mappingInfo.mutate()
                    .paths(paths)
                    .build();
            }
        }

        return mappingInfo;
    }

    /**
     * 获取方法路由前缀
     *
     * @param handleType
     * @return
     */
    private String getPrefix(Class<?> handleType) {
        String packageName = handleType.getPackage()
            .getName();
        // 使用foreach循环遍历HashMap 符合路由规则的添加前缀
        for (String key : routesMap.keySet()) {
            if (packageName.startsWith(key)) {
                return routesMap.get(key);
            }
        }
        return null;
    }

}
