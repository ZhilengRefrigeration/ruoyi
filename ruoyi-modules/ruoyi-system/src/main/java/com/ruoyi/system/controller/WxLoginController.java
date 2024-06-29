package com.ruoyi.system.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.InnerAuth;
import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.api.model.WxLoginUser;
import com.ruoyi.system.domain.vo.WxLoginRequest;
import com.ruoyi.system.domain.vo.WxRegisterRequest;
import com.ruoyi.system.service.WxLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 微信小程序登录Controller
 *
 * @author wyb
 * @date 2023-07-06
 */
@RestController
@RequestMapping(value="/wxLogin")
@Api(description = ApiTerminal.wxMiniProgram+"登录接口")
public class WxLoginController {

    @Resource
    private WxLoginService wxLoginService;

    @ApiOperation(ApiTerminal.wxMiniProgram+"wx用户登录")
    @PostMapping("/loginInFromWx")
    @ResponseBody
    public AjaxResult loginInFromWx(@RequestBody WxLoginRequest entity) throws Exception {
        WxLoginUser loginUser=wxLoginService.loginInFromWx(entity);
        return AjaxResult.success(loginUser);
    }

    @ApiOperation(ApiTerminal.wxMiniProgram+"微信扫码登录")
    @PostMapping("/wxScanLogin")
    @ResponseBody
    public AjaxResult wxScanLogin(@RequestBody WxLoginRequest entity) throws Exception {
//        WxLoginUser loginUser = wxLoginService.loginInFromWx(entity);
        WxLoginUser loginUser = new WxLoginUser();
        loginUser.setUserId(1L);
        loginUser.setLoginName("test");
        loginUser.setAccessToken("sfsdfsdf");
        return AjaxResult.success(loginUser);
    }

    @PostMapping("/user/register")
    @ApiOperation(value = ApiTerminal.wxMiniProgram+"用户注册接口")
    public AjaxResult register(@RequestBody WxRegisterRequest entity){
        //loginFeign.register(entity);
        return AjaxResult.success("注册成功");
    }
    
    @ApiOperation("用户登出")
    @GetMapping("/loginOut")
    @ResponseBody
    public AjaxResult loginOut(@PathVariable("id") Long id) throws Exception {
        return AjaxResult.success("登出成功");
    }

    /**
     * 获取当前用户信息
     */
    @InnerAuth
    @PostMapping("/getWxUserInfo")
    public R<LoginUser> getWxUserInfo(@RequestBody LoginUser loginUser)
    {
        LoginUser loginUser1=wxLoginService.loginFromWx(loginUser);
        System.out.println(JSON.toJSONString(loginUser1));
        return R.ok(loginUser1);
    }
    
}
