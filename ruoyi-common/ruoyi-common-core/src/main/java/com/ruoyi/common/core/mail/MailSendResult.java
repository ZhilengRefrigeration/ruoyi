package com.ruoyi.common.core.mail;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 邮件发送结果
 * 
 * @since 2022/8/18
 */
@Data
@Accessors(chain = true)
public class MailSendResult {

    /**
     * 是否成功，true代表成功
     */
    private boolean success;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 如果有抛出异常，则此为抛出的异常对象
     */
    private Throwable errObj;

    public MailSendResult setFailure(Throwable errObj) {
        this.success = false;
        if (errObj != null) {
            this.errMsg = errObj.toString();
            this.errObj = errObj;
        }
        return this;
    }

    public static MailSendResult success() {
        return new MailSendResult().setSuccess(true);
    }

    public static MailSendResult failure(String errMsg) {
        return new MailSendResult().setSuccess(false).setErrMsg(errMsg);
    }

    public static MailSendResult failure(Throwable errObj) {
        return new MailSendResult().setFailure(errObj);
    }
    
}
