package com.ruoyi.common.swagger.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2
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
                .apis(RequestHandlerSelectors.basePackage("com.ruoyi"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                //描述字段支持Markdown语法
                .title("Weekly-API")
                .contact(new Contact("木子", "https://www.muzi.net/", "muzi@muzi.com"))
                .description("实训项目端")
                .version("1.0")
                .build();
    }
}
