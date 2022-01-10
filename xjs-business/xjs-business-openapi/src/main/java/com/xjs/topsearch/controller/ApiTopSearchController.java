package com.xjs.topsearch.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.xjs.topsearch.domain.ApiTopsearchAllnetwork;
import com.xjs.topsearch.factory.TopserachFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 热搜榜控制器
 * @author xiejs
 * @since 2022-01-10
 */
@RestController
@RequestMapping("topsearch")
@Api(tags = "业务模块-热搜管理")
@Log4j2
public class ApiTopSearchController {

    @Autowired
    private TopserachFactory<ApiTopsearchAllnetwork> tianXingTopsearchAllnetworkFactory;


    @GetMapping
    @ApiOperation("热搜榜接口")
    @Log(title = "获取热搜榜")
    @RequiresLogin
    public AjaxResult topSearch() {
        //获取全网热搜
        List<ApiTopsearchAllnetwork> allnetworkList = tianXingTopsearchAllnetworkFactory.topSearchApi();
        //获取微博热搜

        //获取抖音热搜

        //获取微信热搜

        //获取百度热搜

        return AjaxResult.success();
    }


}
