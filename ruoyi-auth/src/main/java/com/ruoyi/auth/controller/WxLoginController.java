package com.ruoyi.auth.controller;

/**
 * @author 吴一博
 * @date 2023年07月07日 17:47
 * @Description
 */

import com.alibaba.fastjson.JSON;
import com.ruoyi.auth.form.WxLoginBody;
import com.ruoyi.auth.service.SysLoginService;
import com.ruoyi.auth.service.WxLoginService;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.api.model.WxLoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 微信小程序登录Controller
 *
 * @author wyb
 * @date 2023-07-06
 */
@RestController
@RequestMapping(value="/wxLogin")
@Api(description = "微信小程序-登录接口")
public class WxLoginController {

    @Resource
    private WxLoginService wxLoginService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RedisService redisService;
    @ApiOperation("wx用户登录")
    @PostMapping("/loginInFromWx")
    @ResponseBody
    public R<?> loginInFromWx(@RequestBody LoginUser entity) {
        LoginUser loginUser = wxLoginService.loginInFromWx(entity);
        return R.ok(tokenService.createToken(loginUser));
    }
    @ApiOperation("wx扫码登录")
    @PostMapping("/loginInFromWxScan")
    @ResponseBody
    public R<?> loginInFromWxScan(@RequestBody LoginUser entity) {
        LoginUser loginUser = wxLoginService.loginInFromWxScan(entity);
        Map<String, Object> map = tokenService.createToken(loginUser);
        return R.ok(map);
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "wx用户注册接口")
    public AjaxResult register(@RequestBody WxLoginBody entity) {
        //loginFeign.register(entity);
        return AjaxResult.success("注册成功");
    }

    @ApiOperation("用户登出")
    @GetMapping("/loginOut")
    @ResponseBody
    public AjaxResult loginOut(@PathVariable("id") Long id) throws Exception {
        return AjaxResult.success("登出成功");
    }
}
