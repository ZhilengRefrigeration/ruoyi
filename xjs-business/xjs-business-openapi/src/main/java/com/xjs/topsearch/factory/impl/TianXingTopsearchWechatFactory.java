package com.xjs.topsearch.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.constant.HttpStatus;
import com.xjs.common.client.api.tianxing.TianXingWXRSFeignClient;
import com.xjs.properties.TianXingProperties;
import com.xjs.topsearch.domain.ApiTopsearchWechat;
import com.xjs.topsearch.factory.TopserachFactory;
import com.xjs.topsearch.service.ApiTopsearchWechatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 微信热搜api工厂实现
 *
 * @author xiejs
 * @since 2022-01-11
 */
@Component
@Log4j2
public class TianXingTopsearchWechatFactory implements TopserachFactory<ApiTopsearchWechat> {

    @Autowired
    private TianXingProperties tianXingProperties;
    @Autowired
    private TianXingWXRSFeignClient tianXingWXRSFeignClient;
    @Autowired
    private ApiTopsearchWechatService apiTopsearchWechatService;


    @Override
    @Transactional
    public List<ApiTopsearchWechat> topSearchApi() {
        JSONObject jsonObject = tianXingWXRSFeignClient.topSearchApi(tianXingProperties.getKey());
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            if (jsonObject.getInteger("code") == HttpStatus.SUCCESS) {
                JSONArray newslist = jsonObject.getJSONArray("newslist");
                List<ApiTopsearchWechat> collect = newslist.stream().map(arrayJson -> {
                    ApiTopsearchWechat apiTopsearchWechat = new ApiTopsearchWechat();
                    JSONObject json = (JSONObject) arrayJson;
                    apiTopsearchWechat.setWord(json.getString("word"));
                    return apiTopsearchWechat;
                }).collect(Collectors.toList());
                log.info("微信热搜批量插入成功了嘛---" + apiTopsearchWechatService.saveBatch(collect));
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
