package com.ruoyi.gateway.service;

import java.io.IOException;
import com.ruoyi.common.core.exception.CaptchaException;
import com.ruoyi.common.core.web.domain.AjaxResult;

/**
 * 验证码处理
 *
 * @author ruoyi
 */
public interface ValidateCodeService
{
    /**
     * 生成验证码
     * @return AjaxResult
     * @throws IOException /
     * @throws CaptchaException /
     */
    public AjaxResult createCaptcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     * @param key  键
     * @param value 值
     * @throws CaptchaException /
     */
    public void checkCaptcha(String key, String value) throws CaptchaException;
}
