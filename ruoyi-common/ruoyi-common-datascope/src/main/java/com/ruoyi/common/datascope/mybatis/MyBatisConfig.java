package com.ruoyi.common.datascope.mybatis;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alan Scipio
 * created on 2024/2/6
 */
@Configuration
public class MyBatisConfig {

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void init() {
        //添加MyBatis插件
        AutoFillPlugin autoFillPlugin = new AutoFillPlugin();
        sqlSessionFactory.getConfiguration().addInterceptor(autoFillPlugin);
    }

}
