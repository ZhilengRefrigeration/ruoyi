package com.xjs.aword.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.xjs.aword.domain.ApiAWord;
import com.xjs.aword.domain.RequestBody;
import com.xjs.aword.factory.AWordFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * api每日一句控制器
 * @author xiejs
 * @since 2022-01-08
 *
 */
@RequestMapping("aword")
@RestController
@Api(tags = "业务模块-每日一句")
public class ApiAWordController extends BaseController {
    @Autowired
    private AWordFactory tianXingAWordFactory;

    @GetMapping
    @ApiOperation("每日一句接口")
    @Log(title = "获取每日一句")
    @RequiresLogin
    public AjaxResult getApiAWord(@Validated RequestBody requestBody) {
        requestBody = Optional.ofNullable(requestBody).orElseGet(RequestBody::new);
        ApiAWord apiAWord = tianXingAWordFactory.productApiAWord(requestBody);
        return AjaxResult.success(apiAWord);
    }
}
