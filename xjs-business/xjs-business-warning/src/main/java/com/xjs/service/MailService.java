package com.xjs.service;

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

}
