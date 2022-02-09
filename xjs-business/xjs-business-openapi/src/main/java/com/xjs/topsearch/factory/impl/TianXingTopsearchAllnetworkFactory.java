package com.xjs.topsearch.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.constant.HttpStatus;
import com.xjs.common.client.api.tianxing.TianXingQWRSFeignClient;
import com.xjs.properties.TianXingProperties;
import com.xjs.topsearch.domain.ApiTopsearchAllnetwork;
import com.xjs.topsearch.factory.TopserachFactory;
import com.xjs.topsearch.service.ApiTopsearchAllnetworkService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 天行全网热搜工厂实现
 *
 * @author xiejs
 * @since 2022-01-10
 */
@Component
@Log4j2
public class TianXingTopsearchAllnetworkFactory implements TopserachFactory<ApiTopsearchAllnetwork> {

    @Autowired
    private TianXingProperties tianXingProperties;
    @Autowired
    private TianXingQWRSFeignClient tianXingQWRSFeignClient;
    @Autowired
    private ApiTopsearchAllnetworkService apiTopsearchAllnetworkService;


    @Override
    @Transactional
    public List<ApiTopsearchAllnetwork> topSearchApi() {
        JSONObject jsonObject = tianXingQWRSFeignClient.topSearchApi(tianXingProperties.getKey());
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            if (jsonObject.getInteger("code") == HttpStatus.SUCCESS) {
                JSONArray newslist = jsonObject.getJSONArray("newslist");
                List<ApiTopsearchAllnetwork> collect = newslist.stream().map(arrayJson -> {
                    ApiTopsearchAllnetwork apiTopsearchAllnetwork = new ApiTopsearchAllnetwork();
                    JSONObject json = (JSONObject) arrayJson;
                    apiTopsearchAllnetwork.setDigest(json.getString("digest"));
                    apiTopsearchAllnetwork.setHotnum(json.getLong("hotnum"));
                    apiTopsearchAllnetwork.setTitle(json.getString("title"));
                    return apiTopsearchAllnetwork;
                }).collect(Collectors.toList());
                log.info("全网热搜批量插入成功了嘛---" + apiTopsearchAllnetworkService.saveBatch(collect));
                return collect;
            } else {
                log.error("天行全网热搜服务调用成功，但返回异常");
                return new ArrayList<>();
            }
        } else {
            log.error("天行全网热搜服务调用失败，被降级！！");
            return new ArrayList<>();
        }
    }
}
