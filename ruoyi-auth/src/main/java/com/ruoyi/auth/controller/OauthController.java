package com.ruoyi.auth.controller;

import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义OauthController  替换默认tokenEndpoint  以便返回数据格式化同一
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

//    @Autowired
//    private CheckTokenEndpoint checkTokenEndpoint;
    /**
     *
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @GetMapping("/token")
    public R<?> getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.getAccessToken(principal, parameters).getBody());
    }

    @PostMapping("/token")
    public R<?> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

//    @RequestMapping(value = "/check_token")
//    @ResponseBody
//    public R<?> checkToken(@RequestParam("token") String value) throws HttpRequestMethodNotSupportedException {
//        return R.ok(checkTokenEndpoint.checkToken(value));
//    }

    //自定义返回格式
    private R<?> custom(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
//        Map<String, Object> data = new LinkedHashMap(token.getAdditionalInformation());
//        data.put(OAuth2AccessToken.ACCESS_TOKEN, token.getValue());
//        if (token.getRefreshToken() != null) {
//            data.put(OAuth2AccessToken.REFRESH_TOKEN, token.getRefreshToken().getValue());
//        }
        return R.ok(token);
    }

}
