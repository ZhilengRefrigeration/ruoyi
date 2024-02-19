package com.ruoyi.common.core.exception;

import com.ruoyi.common.core.exception.base.BaseException;

import java.io.Serial;

/**
 * @author Alan Scipio
 * created on 2024/2/19
 */
public class NoSuchDataException extends BaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NoSuchDataException(String module, String code, Object[] args, String defaultMessage) {
        super(module, code, args, defaultMessage);
    }

    public NoSuchDataException(String module, String code, Object[] args) {
        super(module, code, args);
    }

    public NoSuchDataException(String module, String defaultMessage) {
        super(module, defaultMessage);
    }

    public NoSuchDataException(String code, Object[] args) {
        super(code, args);
    }

    public NoSuchDataException(String defaultMessage) {
        super(defaultMessage);
    }
}
