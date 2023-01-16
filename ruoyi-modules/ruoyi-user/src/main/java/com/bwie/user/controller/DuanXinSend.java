package com.bwie.user.controller;

import com.bwie.user.service.TbUserService;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.system.api.model.PhoneVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 苏海龙
 * @version 1.0
 * @description: TODO
 * @date 2023/1/14 7:49
 */

@RestController
@CrossOrigin
@Api(tags = "aaa-api")
@RequestMapping("duanxin")
public class DuanXinSend {
    @Resource
    private TbUserService tbUserService;
    //1.发送验证码
    @PostMapping("phoneSend")
    public AjaxResult phoneSend(@RequestBody(required=false) PhoneVo phoneVo){
        return tbUserService.phoneSend(phoneVo);
    }
}
