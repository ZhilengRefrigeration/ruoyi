package com.xjs.topsearch.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.topsearch.domain.*;
import com.xjs.topsearch.factory.TopserachFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.xjs.consts.RedisConst.HOT;
import static com.xjs.consts.RedisConst.HOT_EXPIRE;

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
    @Autowired
    private TopserachFactory<ApiTopsearchWechat> tianXingTopsearchWechatFactory;
    @Autowired
    private TopserachFactory<ApiTopsearchBaidu> tianXingTopsearchBaiduFactory;
    @Autowired
    private TopserachFactory<ApiTopsearchWeibo> tianXingTopsearchWeiboFactory;
    @Autowired
    private TopserachFactory<ApiTopsearchDouyin> tianXingTopsearchDouyinFactory;
    @Autowired
    private RedisService redisService;


    @GetMapping("getTopsearch")
    @ApiOperation("热搜榜接口")
    @Log(title = "获取热搜榜")
    @RequiresPermissions("openapi:topsearch:list")
    public AjaxResult topSearch() {
        if (redisService.hasKey(HOT)) {
            Map<String, List> cacheObject = redisService.getCacheObject(HOT);
            return AjaxResult.success(cacheObject);
        }

        //获取全网热搜
        List<ApiTopsearchAllnetwork> allnetworkList = tianXingTopsearchAllnetworkFactory.topSearchApi();
        //获取微博热搜
        List<ApiTopsearchWeibo> weiboList = tianXingTopsearchWeiboFactory.topSearchApi();
        //获取抖音热搜
        List<ApiTopsearchDouyin> douyinList = tianXingTopsearchDouyinFactory.topSearchApi();
        //获取微信热搜
        List<ApiTopsearchWechat> wechatList = tianXingTopsearchWechatFactory.topSearchApi();
        //获取百度热搜
        List<ApiTopsearchBaidu> baiduList = tianXingTopsearchBaiduFactory.topSearchApi();

        Map<String, List> listHashMap = new HashMap<>();
        listHashMap.put("allnetworkList", allnetworkList);
        listHashMap.put("wechatList", wechatList);
        listHashMap.put("baiduList", baiduList);
        listHashMap.put("weiboList", weiboList);
        listHashMap.put("douyinList", douyinList);

        //把数据存入redis，十分钟过期
        redisService.setCacheObject(HOT,listHashMap,HOT_EXPIRE, TimeUnit.MINUTES);
        return AjaxResult.success(listHashMap);
    }


}
