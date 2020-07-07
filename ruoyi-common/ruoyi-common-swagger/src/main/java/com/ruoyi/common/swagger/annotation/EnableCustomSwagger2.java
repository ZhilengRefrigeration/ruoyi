package com.ruoyi.common.swagger.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import com.ruoyi.common.swagger.config.SwaggerAutoConfiguration;

/**
 * 这个注解应该说没啥用处，因为在引入这个starter以后，就已经会去加载swagger
 * 再多写一个注解根本就没用，只能通过配置文件设置是否使用swagger
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SwaggerAutoConfiguration.class })
public @interface EnableCustomSwagger2
{

}
