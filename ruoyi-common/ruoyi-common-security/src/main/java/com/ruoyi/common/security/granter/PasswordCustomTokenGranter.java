package com.ruoyi.common.security.granter;

import com.ruoyi.common.security.service.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;

import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

public class PasswordCustomTokenGranter extends AbstractCustomTokenGranter  {

    protected CustomUserDetailsService userDetailsService;

    public PasswordCustomTokenGranter(CustomUserDetailsService userDetailsService, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, "password");
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected UserDetails getCustomUser(Map<String, String> parameters) {
        String username = parameters.get("username");
        String password = parameters.get("password");
        return userDetailsService.loadUserByUsernameAndPassword(username, password);
    }
}
