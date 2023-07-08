package com.ruoyi.system.domain;

import com.ruoyi.common.core.constant.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 此类的描述：短信的封装类型。
 */
@Getter
@Setter
public class Sms {
	/**
     * 发送内容，如果含有空格，百分数等特殊内容，请用utf8方式进行编码，最多500个文字（1个英文或数字也算1个文字）
     */
    private String content;
    /**
     * 发送用户帐号
     */
    private String account = Constants.SMS_PAOPAO_ACCOUNT;
    /**
     * 发送帐号密码
     */
    private String password = Constants.SMS_PAOPAO_PASSWORD;
    
    /**发送任务命令*/
    private String action;
    
    /**发送的目的号码*/
    private String mobile;
    /**
     * 原来内容
     */
    private String ms;
    /**
     * 原来手机号
     */
    private String mb;
    /**
     * 接入号
     */
    private String extno = Constants.SMS_PAOPAO_EXTNO;
}
