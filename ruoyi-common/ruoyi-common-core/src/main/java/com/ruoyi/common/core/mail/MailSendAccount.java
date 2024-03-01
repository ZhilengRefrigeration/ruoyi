package com.ruoyi.common.core.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 邮箱账号信息（发邮件用）
 * @since 2021/6/25
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MailSendAccount {

    /**
     * SMTP服务器地址
     */
    private String host;

    /**
     * SMTP服务器端口号
     */
    private Integer port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码/授权码
     */
    private String password;

    /**
     * 是否用SSL加密
     */
    private boolean sslFlag = false;

}
