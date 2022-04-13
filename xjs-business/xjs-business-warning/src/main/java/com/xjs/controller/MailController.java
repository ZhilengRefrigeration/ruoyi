package com.xjs.controller;

import com.xjs.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邮件服务控制器
 * @author xiejs
 * @since 2022-04-13
 */
@RestController
@RequestMapping("mail")
@Api(tags = "业务模块-邮件管理")
public class MailController {

    @Autowired
    private MailService mailService;


    //-----------------------------------远程调用------------------------------------
    @GetMapping("/send-weather-mail")
    @ApiOperation("发送天气邮件ForRPC")
    public void sendWeatherMailForRPC() {
        mailService.sendWeatherMail();
    }

}
