package com.ruoyi.auth.controller;

import com.ruoyi.auth.form.LoginBody;
import com.ruoyi.auth.form.RegisterBody;
import com.ruoyi.auth.service.SysLoginService;
import com.ruoyi.common.core.domain.Rust;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.auth.AuthUtil;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * token 控制
 *
 * @author ruoyi
 */
@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public Rust<?> login(@RequestBody LoginBody form) {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return Rust.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public Rust<?> logout(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return Rust.ok();
    }

    @PostMapping("refresh")
    public Rust<?> refresh(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return Rust.ok();
        }
        return Rust.ok();
    }

    @PostMapping("register")
    public Rust<?> register(@RequestBody RegisterBody registerBody) {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return Rust.ok();
    }
}
