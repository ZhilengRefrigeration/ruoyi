package com.xjs.apitools.controller;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * api小工具控制器
 * @author xiejs
 * @since 2022-01-17
 */
@RestController
@RequestMapping("apitools")
@Api(tags = "业务模块-API小工具")
@Log4j2
public class ApiToolsController {
}
