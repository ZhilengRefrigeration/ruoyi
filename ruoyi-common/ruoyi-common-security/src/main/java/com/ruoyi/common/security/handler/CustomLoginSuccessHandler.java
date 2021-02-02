package com.ruoyi.common.security.handler;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.api.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author alikes
 */
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication instanceof LoginUser){
            LoginUser loginUser = (LoginUser) authentication;
            R<UserInfo>  res = remoteUserService.getUserInfo(loginUser.getSysUser().getUserName());
            loginUser.setRoles(res.getData().getRoles());
            loginUser.setPermissions(res.getData().getPermissions());
        }
    }
}
