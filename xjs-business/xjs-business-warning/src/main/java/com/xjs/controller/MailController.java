package com.xjs.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.xjs.domain.mall.MailBean;
import com.xjs.domain.mall.MailVo;
import com.xjs.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 邮件服务控制器
 *
 * @author xiejs
 * @since 2022-04-13
 */
@RestController
@RequestMapping("mail")
@Api(tags = "业务模块-邮件管理")
public class MailController {

    @Autowired
    private MailService mailService;


    @PostMapping("send-mail")
    @ApiOperation("发送邮件")
    @Log(title = "发送邮件", businessType = BusinessType.INSERT)
    public AjaxResult sendMail(@RequestBody MailVo mailVo) {
        MailBean mailBean = new MailBean();
        BeanUtils.copyProperties(mailVo, mailBean);
        mailBean.setMailType(MailBean.MailType.HTML);
        mailService.sendMail(mailBean);
        return AjaxResult.success();
    }

    //-----------------------------------远程调用------------------------------------
    @GetMapping("/send-weather-mail")
    @ApiOperation("发送天气邮件ForRPC")
    public void sendWeatherMailForRPC() {
        mailService.sendWeatherMail();
    }


    @PostMapping("/sendMailForRPC")
    @ApiOperation("发送邮件ForRPC")
    public R<?> sendMailForRPC(@RequestBody MailBean mailBean) {
        mailService.sendMail(mailBean);
        return R.ok();
    }


}
