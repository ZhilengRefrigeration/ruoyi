package com.ruoyi.common.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import com.ruoyi.common.security.component.RyResourceServerAutoConfiguration;
import com.ruoyi.common.security.component.RySecurityBeanDefinitionRegistrar;

/**
 * 自定义资源服务注解
 * 
 * @author ruoyi
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ RyResourceServerAutoConfiguration.class, RySecurityBeanDefinitionRegistrar.class })
public @interface EnableRyResourceServer
{

}
