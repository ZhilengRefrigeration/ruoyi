package com.xjs.weather.controller;

import com.xjs.weather.service.IPService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ip信息控制器
 * @author xiejs
 * @since 2022-01-15
 */
@RestController
@RequestMapping("ipInfo")
@Api(tags = "业务模块-IP信息")
@Log4j2
public class IPController {

    @Autowired
    private IPService ipService;




}
