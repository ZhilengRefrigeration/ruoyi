package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import com.ruoyi.system.service.WxApplesCodeService;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author 吴一博
 * @date 2022年11月17日 17:10
 * @Description 微信小程序代码控制
 */
@RestController
@RequestMapping("/wxApplesCode")
public class WxApplesCodeController extends BaseController {
    @Resource
    private WxApplesCodeService wxApplesCodeService;

    @RequiresPermissions("system:wxApplesCode:getWxApplesAccessToken")
    @GetMapping("/getWxApplesAccessToken")
    public AjaxResult getWxApplesAccessToken()
    {
        return AjaxResult.success(wxApplesCodeService.getWxApplesAccessToken());
    }
    @ApiOperation(ApiTerminal.wxMiniProgram+"生成微信小程序二维码")
    @RequiresPermissions("system:wxApplesCode:genWxApplesAqrCode")
    @Log(title = "生成微信小程序二维码", businessType = BusinessType.OTHER)
    @PostMapping("/genWxApplesAqrCode")
    public AjaxResult genWxApplesAqrCode(@RequestBody WxAppletsCodeVo wxAppletsCodeVo)
    {
        return AjaxResult.success(wxApplesCodeService.genWxApplesAqrCode(wxAppletsCodeVo));
    }

    @ApiOperation(ApiTerminal.pc+"生成微信扫码登录二维码")
    @Log(title = ApiTerminal.pc+"生成微信扫码登录二维码", businessType = BusinessType.OTHER)
    @PostMapping("/genWxApplesAqrCodeForPc")
    public AjaxResult genWxApplesAqrCodeForPc(@RequestBody WxAppletsCodeVo wxAppletsCodeVo)
    {
        return AjaxResult.success(wxApplesCodeService.genWxApplesAqrCodeForPc(wxAppletsCodeVo));
    }
}