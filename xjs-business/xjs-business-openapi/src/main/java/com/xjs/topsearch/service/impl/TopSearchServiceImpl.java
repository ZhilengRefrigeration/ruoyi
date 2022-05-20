package com.xjs.topsearch.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjs.topsearch.domain.*;
import com.xjs.topsearch.factory.TopserachFactory;
import com.xjs.topsearch.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 热搜服务实现
 *
 * @author xiejs
 * @since 2022-01-22
 */
@Service
@Log4j2
public class TopSearchServiceImpl implements TopSearchService {

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
    private ApiTopsearchAllnetworkService apiTopsearchAllnetworkService;
    @Autowired
    private ApiTopsearchWechatService apiTopsearchWechatService;
    @Autowired
    private ApiTopsearchBaiduService apiTopsearchBaiduService;
    @Autowired
    private ApiTopsearchDouyinService apiTopsearchDouyinService;
    @Autowired
    private ApiTopsearchWeiboService apiTopsearchWeiboService;


    private ExecutorService executor = Executors.newFixedThreadPool(5);

    @Override
    public Map<String, List> getAllTopSearch() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        CompletableFuture<List<ApiTopsearchAllnetwork>> future1 = CompletableFuture.supplyAsync(() -> {
            //获取全网热搜
            return tianXingTopsearchAllnetworkFactory.topSearchApi();
        }, executor);

        CompletableFuture<List<ApiTopsearchWeibo>> future2 = CompletableFuture.supplyAsync(() -> {
            //获取微博热搜
            return tianXingTopsearchWeiboFactory.topSearchApi();
        }, executor);

        CompletableFuture<List<ApiTopsearchDouyin>> future3 = CompletableFuture.supplyAsync(() -> {
            //获取抖音热搜
            return tianXingTopsearchDouyinFactory.topSearchApi();
        }, executor);

        CompletableFuture<List<ApiTopsearchWechat>> future4 = CompletableFuture.supplyAsync(() -> {
            //获取微信热搜
            return tianXingTopsearchWechatFactory.topSearchApi();
        }, executor);

        CompletableFuture<List<ApiTopsearchBaidu>> future5 = CompletableFuture.supplyAsync(() -> {
            //获取百度热搜
            return tianXingTopsearchBaiduFactory.topSearchApi();
        }, executor);

        // 必须等待所有异步任务执行完成才能返回结果
        CompletableFuture.allOf(future1, future2, future3, future4, future5).get();

        long end = System.currentTimeMillis();

        long count = end - start;
        log.info("异步获取热搜榜耗费时间：{}ms",count);


        Map<String, List> listHashMap = new HashMap<>();
        listHashMap.put("allnetworkList", future1.get());
        listHashMap.put("wechatList", future2.get());
        listHashMap.put("baiduList", future3.get());
        listHashMap.put("weiboList", future4.get());
        listHashMap.put("douyinList", future5.get());
        return listHashMap;
    }

    @Override
    public Integer deleteRepeatData() {
        Integer allNetworkCount = apiTopsearchAllnetworkService.deleteRepeatData();
        log.info("thread id:{},清除全网热搜榜重复数据，重复数：{}", Thread.currentThread().getId(), allNetworkCount);
        Integer wechatCount = apiTopsearchWechatService.deleteRepeatData();
        log.info("thread id:{},清除微信热搜榜重复数据，重复数：{}", Thread.currentThread().getId(), wechatCount);
        Integer baiduCount = apiTopsearchBaiduService.deleteRepeatData();
        log.info("thread id:{},清除百度热搜榜重复数据，重复数：{}", Thread.currentThread().getId(), baiduCount);
        Integer douyinCount = apiTopsearchDouyinService.deleteRepeatData();
        log.info("thread id:{},清除抖音热搜榜重复数据，重复数：{}", Thread.currentThread().getId(), douyinCount);
        Integer weiboCount = apiTopsearchWeiboService.deleteRepeatData();
        log.info("thread id:{},清除微博热搜榜重复数据，重复数：{}", Thread.currentThread().getId(), weiboCount);
        return allNetworkCount + wechatCount + baiduCount + douyinCount + weiboCount;
    }

    @Override
    public Map<String, List> getHistoryTopSearchByDate(String date) {
        DateTime dateTime = DateUtil.parseDate(date);
        String dateStr = dateTime.toDateStr();
        String StartDate = dateStr + " 00:00:00";
        String EndDate = dateStr + " 23:59:59";
        List<ApiTopsearchAllnetwork> allnetworkList = apiTopsearchAllnetworkService.list(new QueryWrapper<ApiTopsearchAllnetwork>()
                .between("create_time", StartDate, EndDate));
        List<ApiTopsearchWechat> wechatList = apiTopsearchWechatService.list(new QueryWrapper<ApiTopsearchWechat>()
                .between("create_time", StartDate, EndDate));
        List<ApiTopsearchBaidu> baiduList = apiTopsearchBaiduService.list(new QueryWrapper<ApiTopsearchBaidu>()
                .between("create_time", StartDate, EndDate));
        List<ApiTopsearchDouyin> douyinList = apiTopsearchDouyinService.list(new QueryWrapper<ApiTopsearchDouyin>()
                .between("create_time", StartDate, EndDate));
        List<ApiTopsearchWeibo> weiboList = apiTopsearchWeiboService.list(new QueryWrapper<ApiTopsearchWeibo>()
                .between("create_time", StartDate, EndDate));


        HashMap<String, List> hashMap = new HashMap<>();
        hashMap.put("allnetworkList", allnetworkList);
        hashMap.put("wechatList", wechatList);
        hashMap.put("baiduList", baiduList);
        hashMap.put("douyinList", douyinList);
        hashMap.put("weiboList", weiboList);
        return hashMap;
    }
}
