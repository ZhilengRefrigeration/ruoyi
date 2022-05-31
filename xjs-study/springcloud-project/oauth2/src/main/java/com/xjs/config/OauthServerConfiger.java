package com.xjs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 授权服务
 *
 * @author xiejs
 * @since 2022-05-30
 */
@Configuration
@EnableAuthorizationServer
public class OauthServerConfiger extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 认证服务器最终是以api接口的方式对外提供服务（校验合法性并生成令牌，校验令牌等）<br>
     * 那么，以api接口方式对外的话，就涉及到接口的访问权限，我们需要在这里进行必要的配置
     *
     * @param security 配置
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);

        //相当于打开endpoints，访问接口的开关，这样的话后期我们能够访问该接口
        security
                //允许客户端表单认证
                .allowFormAuthenticationForClients()
                //开启端口 oauth/token_key的访问权限
                .tokenKeyAccess("permitAll()")
                //开启端口 oauth/check_token的访问权限
                .checkTokenAccess("permitAll()")
        ;
    }

    /**
     * 客户端详情配置<br>
     * 比如client_id,secret<br>
     * 当前这个服务如同QQ平台，服务作为客户端需要QQ平台进行登录授权认证等，提前需要到QQ平台注册，QQ平台会给服务颁发client_id等必要参数，表明客户端是谁
     *
     * @param clients 客户端
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);


        clients.inMemory()                         //客户端信息存储到什么地方，可以再内存中，也可以再数据库里
                .withClient("client_A")     //添加一份client配置，指定client_id
                .secret("xjs666")                  //指定客户端的密码/安全码
                .resourceIds("autodeliver")        //指定客户端所能访问的资源id清单，此处的资源id是需要在具体的资源服务
                .authorizedGrantTypes("password", "refresh_token")      //认证类型/令牌颁发模式，可以配置多个，需要客户端调用的时候传递参数
                .scopes("all")                     //客户端权限范围
        ;
    }

    /**
     * 配置token令牌管理相关
     *
     * @param endpoints 令牌
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);

        endpoints.tokenStore(this.tokenStore())              //指定token的存储方法
                .tokenServices(this.authorizationServerTokenServices())            //token服务的一个描述，可以认为是token生成细节的描述
                .authenticationManager(authenticationManager)    //指定认证管理器，随后注入一个到当前类使用即可
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
        ;
    }


    /**
     * 该方法用于创建tokenStore对象（令牌存储对象）<br>
     * token以什么形式存储
     */
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    /**
     * 该方法用户获取一个token服务对象（该对象描述了token有效期等信息）
     */
    public AuthorizationServerTokenServices authorizationServerTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        //是否开启令牌刷新
        defaultTokenServices.setSupportRefreshToken(true);

        defaultTokenServices.setTokenStore(this.tokenStore());

        //设置令牌有效时间
        defaultTokenServices.setAccessTokenValiditySeconds(10 /** 60*/);

        //设置刷新令牌的有效时间
        defaultTokenServices.setRefreshTokenValiditySeconds(3 * 60 * 60 * 24);

        return defaultTokenServices;
    }


}
