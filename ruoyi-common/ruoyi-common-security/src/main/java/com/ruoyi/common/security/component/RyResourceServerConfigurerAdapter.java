package com.ruoyi.common.security.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.client.RestTemplate;
import com.ruoyi.common.security.component.properties.PermitAllUrlProperties;

public class RyResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter
{
    @Autowired
    protected ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

    protected RemoteTokenServices remoteTokenServices = new RemoteTokenServices();

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private PermitAllUrlProperties permitAllUrl;

    @Autowired
    private RestTemplate lbRestTemplate;

    /**
     * 默认的配置，对外暴露
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception
    {
        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        httpSecurity.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
            .ExpressionInterceptUrlRegistry registry = httpSecurity
            .authorizeRequests();
        permitAllUrl.getUrls()
            .forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
            .and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        UserAuthenticationConverter userTokenConverter = new RyUserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userTokenConverter);

        remoteTokenServices.setRestTemplate(lbRestTemplate);
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint).accessDeniedHandler(accessDeniedHandler)
                .tokenServices(remoteTokenServices);
    }
}
