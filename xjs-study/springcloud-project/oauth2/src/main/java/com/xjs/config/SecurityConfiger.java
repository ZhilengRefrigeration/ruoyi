package com.xjs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * 该配置类主要处理用户名和密码的校验等
 *
 * @author xiejs
 * @since 2022-05-30
 */
@Configuration
public class SecurityConfiger extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 注册一个认证管理器到容器
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 处理用户名和密码验证
     * <li>
     *     <ul>客户端传递username和password参数到认证服务器</ul>
     *     <ul>一般来说，username和password会存储在数据库中的用户表中</ul>
     *     <ul>根据用户表中数据，验证当前传递过来的用户信息的合法性</ul>
     * </li>
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);

        //把用户信息配置在内存中，实例化一个用户对象
        UserDetails user = new User("admin", "123456", new ArrayList<>());

        auth.inMemoryAuthentication()
                .withUser(user)
                .passwordEncoder(passwordEncoder)
        ;

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
