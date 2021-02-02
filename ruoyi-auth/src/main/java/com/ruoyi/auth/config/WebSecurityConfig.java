package com.ruoyi.auth.config;

import com.ruoyi.common.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * Security 安全认证相关配置
 * Oauth2依赖于Security 默认情况下WebSecurityConfig执行比ResourceServerConfig优先
 * 
 * @author ruoyi
 */
@Order(99)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
//    @Resource(name= "defaultUserDetailsService")
//    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(getUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
        .authorizeRequests()
        .antMatchers(
            "/actuator/**",
            "/oauth/*",
            "/token/**",
            "/logout",
            "/login"
        ).permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable();
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }


}
