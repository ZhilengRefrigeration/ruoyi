package com.bwie.ruoyi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author 冰烆
 * @version 1.0
 * @description: TODO
 * @date 2022/10/21 20:15
 */

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                //分组名称
                .groupName("WebApi")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.bwie"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                //描述字段支持Markdown语法
                .title("Weekly-API")
                .contact(new Contact("苏海龙", "https://www.bwie.net/", "bwie@bwie.com"))
                .description("考试服务端API-version1.0")
                .version("1.0")
                .build();
    }
}
