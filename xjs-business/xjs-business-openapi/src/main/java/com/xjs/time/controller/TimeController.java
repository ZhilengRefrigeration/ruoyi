package com.xjs.time.controller;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.xjs.time.factory.TimeFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取时间控制器
 * @author xiejs
 * @since 2022-02-26
 */
@RestController
@RequestMapping("time")
@Api(tags = "业务模块-时间管理")
@Log4j2
public class TimeController {

    @Autowired
    private TimeFactory timeFactoryImpl;

    @GetMapping("networkTime")
    @ApiOperation("获取网络时间")
    public AjaxResult getNetworkTime() {
        String time = timeFactoryImpl.getTime();
        return AjaxResult.success("操作成功",time);
    }

    @GetMapping("serviceTime")
    @ApiOperation("获取服务器时间")
    public AjaxResult getServiceTime() {
        return AjaxResult.success("操作成功",DateUtil.now());
    }


}
