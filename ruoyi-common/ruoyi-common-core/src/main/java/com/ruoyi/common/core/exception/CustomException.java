package com.ruoyi.common.core.exception;

/**
 * 自定义异常
 * 
 * @author ruoyi
 */
public class CustomException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private Integer code;

    public CustomException(String message)
    {
        super(message);
    }

    public CustomException(Integer code, String message)
    {
        super(message);
        this.code = code;
    }

    public CustomException(Integer code, String message, Throwable e)
    {
        super(message, e);
        this.code = code;
    }

    public CustomException(String message, Throwable e)
    {
        super(message, e);
    }

    public Integer getCode()
    {
        return code;
    }
}
