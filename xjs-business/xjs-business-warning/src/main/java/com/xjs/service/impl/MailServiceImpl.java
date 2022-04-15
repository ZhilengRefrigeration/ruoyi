package com.xjs.service.impl;

import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.model.LoginUser;
import com.xjs.business.api.RemoteWeatherFeign;
import com.xjs.business.api.domain.NowWeather;
import com.xjs.domain.mall.MailBean;
import com.xjs.server.MailServer;
import com.xjs.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 邮件发送service接口实现
 *
 * @author xiejs
 * @since 2022-04-13
 */
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private RemoteWeatherFeign remoteWeatherFeign;
    @Autowired
    private MailServer mailServer;
    @Resource
    private RemoteUserService remoteUserService;

    public static final String EMAIL = "1294405880@qq.com";

    @Override
    public Boolean sendWeatherMail() {

        R<NowWeather> r = remoteWeatherFeign.getWeatherForRPC();
        if (r.getCode() == HttpStatus.SUCCESS) {
            NowWeather nowWeather = r.getData();

            MailBean mailBean = new MailBean();
            mailBean.setUserName("用户");
            mailBean.setSubject("天气播报");

            //获取管理员邮件
            R<LoginUser> admin = remoteUserService.getUserInfo("admin", SecurityConstants.INNER);
            if (admin.getCode() == HttpStatus.SUCCESS) {
                String email = admin.getData().getSysUser().getEmail();
                mailBean.setRecipient(email);
            } else {
                mailBean.setRecipient(EMAIL);
            }

            mailBean.setMailType(MailBean.MailType.HTML);

            mailBean.setContent("<h3>当前" + nowWeather.getCity() + "的天气：" + nowWeather.getTemperature() + "℃,天气状况：" + nowWeather.getWeather() + "</h3>");
            return mailServer.sendMail(mailBean);
        }

        return false;
    }

    @Override
    public void sendMail(MailBean mailBean) {
        if (mailBean.getMailType() == null) {
            if (mailBean.getFileList() != null && mailBean.getFileList().length > 0) {
                mailBean.setMailType(MailBean.MailType.ATTACHMENT);
            } else if (mailBean.getFileList() == null || mailBean.getFileList().length == 0) {
                mailBean.setMailType(MailBean.MailType.HTML);
            }
        }
        mailServer.sendMail(mailBean);
    }


}
