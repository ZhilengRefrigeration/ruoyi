package com.ruoyi.auth.aop;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.system.api.model.LoginUser;
import com.xjs.business.warning.RemoteMailFeign;
import com.xjs.business.warning.domain.MailBean;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录切面类
 *
 * @author xiejs
 * @since 2022-04-14
 */
@Log4j2
@Component
@Aspect
public class LoginAspect {

    @Resource
    private RemoteMailFeign remoteMailFeign;

    /**
     * 声明AOP签名
     */
    @Pointcut("@annotation(com.ruoyi.auth.annotation.LoginAspect)")
    public void pointcut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(loginAspect)", returning = "obj")
    public void doAfterReturning(JoinPoint joinPoint, com.ruoyi.auth.annotation.LoginAspect loginAspect, Object obj) {

        MailBean mailBean = new MailBean();

        LoginUser loginUser = (LoginUser) obj;
        mailBean.setSubject("上线提醒");
        mailBean.setMailType(MailBean.MailType.HTML);
        mailBean.setRecipient(loginUser.getSysUser().getEmail());
        mailBean.setContent("<h3 style=\"color:red;\">" + loginUser.getSysUser().getNickName() + "上线啦</h3> " +
                "<img src=\"" + loginUser.getSysUser().getAvatar() + "\" alt=\"头像\">" +
                " <p><strong>当前IP地址：" + loginUser.getSysUser().getLoginIp() + "</strong></p>" +
                "<p><strong>上线时间："+ DateUtil.now() +"</strong></p>");

        remoteMailFeign.sendMailForRPC(mailBean);
    }

}
