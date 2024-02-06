package com.ruoyi.common.core.exception.auth;

import java.io.Serial;

/**
 * 登录过期异常
 *
 * @author Alan Scipio
 * created on 2024/2/6
 */
public class LoginExpiredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public LoginExpiredException(String message) {
        super(message);
    }
}
