package com.ruoyi.system.service;

import com.ruoyi.system.domain.Sms;
import com.ruoyi.system.domain.vo.SmsResponse;

/**
 * @author 吴一博
 * @date 2023年07月07日 15:23
 * @Description 短信服务
 */
public interface SmsService {
    SmsResponse sendSms(Sms sms);
}
