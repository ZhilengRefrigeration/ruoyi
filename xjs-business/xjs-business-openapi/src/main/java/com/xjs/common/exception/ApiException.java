package com.xjs.common.exception;

import lombok.extern.log4j.Log4j2;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-27
 */
@Log4j2
public class ApiException extends RuntimeException{
    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
        log.error("调用第三方API异常----{}",message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
