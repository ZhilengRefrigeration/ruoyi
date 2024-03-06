package com.ruoyi.wms.exception;

import com.ruoyi.common.core.exception.base.BaseException;

/**
 * 库存异常
 *
 * @author Alan Scipio
 * created on 2024/3/6
 */
public class StockException extends BaseException {
    public StockException(String code, Object[] args, String defaultMessage) {
        super("wms.stock", code, args, defaultMessage);
    }

    public StockException(String code, Object[] args) {
        super("wms.stock", code, args);
    }

    public StockException(String defaultMessage) {
        super("wms.stock", defaultMessage);
    }
}
