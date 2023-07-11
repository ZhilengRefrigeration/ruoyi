package com.ruoyi.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.system.domain.Sms;
import com.ruoyi.system.domain.vo.SmsResponse;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.SmsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴一博
 * @date 2023年07月07日 15:39
 * @Description 短信发送接口服务
 */
@Log4j2
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${spring.profiles.active}")
    private String springProfilesActive;
    @Resource
    private ISysConfigService configService;
    @Override
    public SmsResponse sendSms(Sms sms) {
        sms.setAccount(Constants.SMS_PAOPAO_ACCOUNT);
        sms.setPassword(Constants.SMS_PAOPAO_PASSWORD);
        sms.setExtno(Constants.SMS_PAOPAO_EXTNO);
        sms.setMobile(sms.getMb());
        sms.setContent(sms.getMs());
        //发送短信
        if(restTemplate==null){
            restTemplate = new RestTemplate();
        }
        //2. 模拟表单参数 请求体携带参数
        //String url = Constant.SMS_PAOPAO_URL+"?phone={phone}&msg={phone}";
        String url = Constants.SMS_PAOPAO_URL+"?action=send&account={account}&password={password}&mobile={mobile}&content={content}&extno={extno}";
        Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("account", sms.getAccount());
        uriVariables.put("password",sms.getPassword());
        if(StrUtil.isNotEmpty(springProfilesActive) && springProfilesActive.equals("prod")) {
            uriVariables.put("mobile",sms.getMobile());
        }else {
            String adminTelephone = configService.selectConfigByKey("sys.admin.telephone");
            uriVariables.put("mobile",adminTelephone);
        }
        uriVariables.put("content",sms.getContent());
        uriVariables.put("extno",sms.getExtno());
        String responseEntity = restTemplate.getForObject(url, String.class, uriVariables);
        log.info("短信发送url={} 请求参数{}", url, JSON.toJSONString(uriVariables));
        log.info("短信发送结果={}", responseEntity);
     /*   String[] smsres = res.split(",");
        int status = Integer.parseInt(smsres[0]);
        String batchNo = smsres[1];

      */
        SmsResponse smsResponse = new  SmsResponse();
        //smsResponse.setMsg(SmsResponseTypeEnum.getSmsResponseTypeByCode(1).getMsg());
        smsResponse.setBatchNo("batchNo");
        smsResponse.setStatus(0);
        return smsResponse;
    }
}
