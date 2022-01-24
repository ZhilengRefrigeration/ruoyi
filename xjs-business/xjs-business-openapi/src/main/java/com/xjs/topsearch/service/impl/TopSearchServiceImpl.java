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
    public Integer deleteRepeatData() {
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
