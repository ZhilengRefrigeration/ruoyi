package com.ruoyi.web.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.web.admin.service.ValidateCodeService;

/**
 * 验证码操作处理
 *
 * @author ruoyi
 */
@RestController
public class CaptchaController {

    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 生成验证码
     */
    @GetMapping("/code")
    public AjaxResult getCode(HttpServletResponse response) throws IOException {
        return validateCodeService.createCaptcha();
    }
}
