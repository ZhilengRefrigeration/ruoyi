package com.xjs.topsearch.service.impl;

import com.xjs.topsearch.domain.*;
import com.xjs.topsearch.factory.TopserachFactory;
import com.xjs.topsearch.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 热搜服务实现
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



    @Override
    public Map<String, List> getAllTopSearch() {
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
        return listHashMap;
    }

    @Override
    public Integer deleteRepeat() {
        Integer allNetworkCount = apiTopsearchAllnetworkService.deleteRepeatData();
        log.info("thread id:{},清除全网热搜榜重复数据，重复数：{}", Thread.currentThread().getId(),allNetworkCount);
        Integer wechatCount = apiTopsearchWechatService.deleteRepeatData();
        log.info("thread id:{},清除微信热搜榜重复数据，重复数：{}", Thread.currentThread().getId(),wechatCount);
        Integer baiduCount = apiTopsearchBaiduService.deleteRepeatData();
        log.info("thread id:{},清除百度热搜榜重复数据，重复数：{}", Thread.currentThread().getId(),baiduCount);
        Integer douyinCount = apiTopsearchDouyinService.deleteRepeatData();
        log.info("thread id:{},清除抖音热搜榜重复数据，重复数：{}", Thread.currentThread().getId(),douyinCount);
        Integer weiboCount = apiTopsearchWeiboService.deleteRepeatData();
        log.info("thread id:{},清除微博热搜榜重复数据，重复数：{}", Thread.currentThread().getId(),weiboCount);
        return allNetworkCount+wechatCount+baiduCount+douyinCount+weiboCount;
    }
}
