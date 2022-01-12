package com.xjs.topsearch.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.constant.HttpStatus;
import com.xjs.common.client.api.tianxing.TianXingBDRSFeignClient;
import com.xjs.config.TianXingProperties;
import com.xjs.topsearch.domain.ApiTopsearchBaidu;
import com.xjs.topsearch.domain.ApiTopsearchWechat;
import com.xjs.topsearch.factory.TopserachFactory;
import com.xjs.topsearch.service.ApiTopsearchBaiduService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 天行百度热搜工厂实现
 *
 * @author xiejs
 * @since 2022-01-11
 */
@Component
@Log4j2
public class TianXingTopsearchBaiduFactory implements TopserachFactory<ApiTopsearchBaidu> {
    @Autowired
    private TianXingProperties tianXingProperties;
    @Autowired
    private ApiTopsearchBaiduService apiTopsearchBaiduService;
    @Autowired
    private TianXingBDRSFeignClient tianXingBDRSFeignClient;


    @Override
    public List<ApiTopsearchBaidu> topSearchApi() {
        JSONObject jsonObject = tianXingBDRSFeignClient.topSearchApi(tianXingProperties.getKey());
        if (!jsonObject.containsKey("error")) {
            if (jsonObject.getInteger("code") == HttpStatus.SUCCESS) {
                JSONArray newslist = jsonObject.getJSONArray("newslist");
                List<ApiTopsearchBaidu> collect = newslist.stream().map(arrayJson -> {
                    ApiTopsearchBaidu apiTopsearchBaidu = new ApiTopsearchBaidu();
                    JSONObject json = (JSONObject) arrayJson;
                    apiTopsearchBaidu.setTitle(json.getString("keyword"));

                    String brief = json.getString("brief");
                    //截取无用字符
                    if (brief.contains("查看更多&gt;")) {
                        brief = brief.substring(0, brief.length() - 8);
                    }
                    apiTopsearchBaidu.setDigest(brief);

                    apiTopsearchBaidu.setHotnum(json.getLong("index"));
                    apiTopsearchBaidu.setTrend(json.getString("trend"));
                    return apiTopsearchBaidu;
                }).collect(Collectors.toList());
                log.info("百度热搜批量插入成功了嘛---" + apiTopsearchBaiduService.saveBatch(collect));
                return collect;
            } else {
                log.error("天行微信热搜服务调用成功，但返回异常");
                return new ArrayList<>();
            }
        } else {
            log.error("天行微信热搜服务调用失败，被降级！！");
            return new ArrayList<>();
        }
    }






}
