package com.xjs.aword.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * api每日一句控制器
 * @author xiejs
 * @since 2022-01-08
 */
@RequestMapping("aword")
@RestController
@Api(tags = "业务模块-每日一句")
public class ApiAWordController extends BaseController {
}
