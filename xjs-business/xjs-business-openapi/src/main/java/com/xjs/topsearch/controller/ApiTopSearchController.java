package com.xjs.topsearch.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.topsearch.service.TopSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private TopSearchService topSearchService;

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
        Map<String, List> listHashMap = topSearchService.getAllTopSearch();
        //把数据存入redis，十分钟过期
        redisService.setCacheObject(HOT,listHashMap,HOT_EXPIRE, TimeUnit.MINUTES);
        return AjaxResult.success(listHashMap);
    }


    @GetMapping("getHistoryTopSearch")
    @ApiOperation("历史热搜榜接口")
    @Log(title = "获取历史热搜榜")
    @RequiresPermissions("openapi:topsearch:list")
    public AjaxResult getHistoryTopSearchByDate(@RequestParam("date") String date) {
        Map<String, List> data = topSearchService.getHistoryTopSearchByDate(date);
        return AjaxResult.success(data);
    }


    //-----------------------内部远程调用rpc-------------------------------------
    @GetMapping("getTopsearchForRPC")
    @ApiOperation("内部远程调用热搜榜接口")
    public R<Map<String, List>> topSearchForRPC() {
        Map<String, List> map = topSearchService.getAllTopSearch();
        return R.ok(map);
    }


}
