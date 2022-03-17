package com.xjs.exception;

import lombok.extern.log4j.Log4j2;

/**
 * 自定义商城业务异常
 * @author xiejs
 * @since  2022-03-17
 */
@Log4j2
public class MallException extends RuntimeException{
    public MallException() {
        super();
    }

    public MallException(String message) {
        super(message);
        log.error("商城业务异常----{}",message);
    }

    public MallException(String message, Throwable cause) {
        super(message, cause);
    }
}
