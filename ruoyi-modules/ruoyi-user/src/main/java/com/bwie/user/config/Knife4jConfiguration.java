package com.bwie.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description //TODO
 * @Author 王辉
 * @Date 2022/11/13 19:53
 */
@Configuration
@EnableSwagger2
public class Knife4jConfiguration {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        String groupName="3.X版本";
        Docket docket=new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("这是User API ")
                        .description("# 这里记录服务端所有的接口的入参，出参等等信息")
                        .termsOfServiceUrl("https://www.shenmazong.com")
                        .contact(new Contact("shl","http://bwie.com","1447562585@qq.com"))
                        .version("3.0")
                        .build())
                //分组名称
                .groupName(groupName)
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.bwie.user"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


}
