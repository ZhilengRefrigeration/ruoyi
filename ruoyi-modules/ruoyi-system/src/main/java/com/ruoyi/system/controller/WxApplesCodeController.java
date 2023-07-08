package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.swagger.apiConstants.ApiTerminal;
import com.ruoyi.system.api.domain.vo.WxAppletsCodeVo;
import com.ruoyi.system.domain.UserWxAqrCode;
import com.ruoyi.system.domain.vo.UserWxAqrCodeVo;
import com.ruoyi.system.service.IWxUserService;
import com.ruoyi.system.service.WxApplesCodeService;
import io.seata.core.model.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @RequiresPermissions("system:wxApplesCode:genWxApplesAqrCode")
    @Log(title = "生成微信小程序二维码", businessType = BusinessType.OTHER)
    @PostMapping("/genWxApplesAqrCode")
    public AjaxResult genWxApplesAqrCode(@RequestBody WxAppletsCodeVo wxAppletsCodeVo)
    {
        return AjaxResult.success(wxApplesCodeService.genWxApplesAqrCode(wxAppletsCodeVo));
    }
}
