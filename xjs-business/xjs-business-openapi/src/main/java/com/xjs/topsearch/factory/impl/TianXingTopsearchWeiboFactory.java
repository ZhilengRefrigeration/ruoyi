package com.xjs.topsearch.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.constant.HttpStatus;
import com.xjs.common.client.api.tianxing.TianXingWBRSFeignClient;
import com.xjs.properties.TianXingProperties;
import com.xjs.topsearch.domain.ApiTopsearchWeibo;
import com.xjs.topsearch.factory.TopserachFactory;
import com.xjs.topsearch.service.ApiTopsearchWeiboService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 微博热搜api工厂实现
 *
 * @author xiejs
 * @since 2022-01-12
 */
@Component
@Log4j2
public class TianXingTopsearchWeiboFactory implements TopserachFactory<ApiTopsearchWeibo> {

    @Autowired
    private TianXingProperties tianXingProperties;

    @Autowired
    private TianXingWBRSFeignClient tianXingWBRSFeignClient;

    @Autowired
    private ApiTopsearchWeiboService apiTopsearchWeiboService;


    @Override
    @Transactional
    public List<ApiTopsearchWeibo> topSearchApi() {
        JSONObject jsonObject = tianXingWBRSFeignClient.topSearchApi(tianXingProperties.getKey());
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            if (jsonObject.getInteger("code") == HttpStatus.SUCCESS) {
                JSONArray newslist = jsonObject.getJSONArray("newslist");
                List<ApiTopsearchWeibo> collect = newslist.stream().map(arrayJson -> {
                    ApiTopsearchWeibo apiTopsearchWeibo = new ApiTopsearchWeibo();
                    JSONObject json = (JSONObject) arrayJson;
                    apiTopsearchWeibo.setHotword(json.getString("hotword"));

                    //提取热度中的数字
                    String hotwordnum = json.getString("hotwordnum");
                    String regEx="[^0-9]";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(hotwordnum);
                    apiTopsearchWeibo.setHotnum(m.replaceAll("").trim());
                    apiTopsearchWeibo.setHottag(json.getString("hottag"));
                    return apiTopsearchWeibo;
                }).collect(Collectors.toList());
                log.info("微博热搜批量插入成功了嘛---" + apiTopsearchWeiboService.saveBatch(collect));
                return collect;
            } else {
                log.error("天行微博热搜服务调用成功，但返回异常");
                return new ArrayList<>();
            }
        } else {
            log.error("天行微博热搜服务调用失败，被降级！！");
            return new ArrayList<>();
        }
    }






}
