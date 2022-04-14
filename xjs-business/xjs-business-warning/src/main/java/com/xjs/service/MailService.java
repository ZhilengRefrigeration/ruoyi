package com.xjs.service;

import com.xjs.domain.mall.MailBean;

/**
 * 邮件发送service接口
 * @author xiejs
 * @since 2022-04-13
 */
public interface MailService {

    /**
     * 定时发送天气预报邮件
     * @return true/false
     */
    Boolean sendWeatherMail();

    /**
     * 发送邮件
     * @param mailBean 邮件对象
     */
    void sendMail(MailBean mailBean);

}
