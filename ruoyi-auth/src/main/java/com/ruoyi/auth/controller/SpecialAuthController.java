package com.ruoyi.auth.controller;

import com.ruoyi.auth.config.SpecialAuthProperties;
import com.ruoyi.auth.form.LoginBody;
import com.ruoyi.common.core.domain.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 特殊登录控制
 *
 * @author Alan Scipio
 * created on 2024/2/1
 */
@RequestMapping("/specialAuth")
@RestController
public class SpecialAuthController {

    @Resource
    private TokenController tokenController;
    @Resource
    private SpecialAuthProperties specialAuthProperties;

    /**
     * 不需要验证码的登录（哪怕开启了验证码功能）
     */
    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form) {
        if (specialAuthProperties.getLoginEnabled() == null || !specialAuthProperties.getLoginEnabled()) {
            return R.fail("Special login is not enabled.");
        } else {
            return tokenController.login(form);
        }
    }

}
