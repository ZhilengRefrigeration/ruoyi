package com.xjs.exception;

import lombok.extern.log4j.Log4j2;

/**
 * 自定义业务异常
 * @author xiejs
 * @since  2021-12-25
 */
@Log4j2
public class BusinessException extends RuntimeException{
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        log.error("业务异常----{}",message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
