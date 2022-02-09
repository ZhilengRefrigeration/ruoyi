package com.xjs.topsearch.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.constant.HttpStatus;
import com.xjs.common.client.api.tianxing.TianXingDYRSFeignClient;
import com.xjs.properties.TianXingProperties;
import com.xjs.topsearch.domain.ApiTopsearchDouyin;
import com.xjs.topsearch.factory.TopserachFactory;
import com.xjs.topsearch.service.ApiTopsearchDouyinService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.xjs.consts.ApiConst.DEMOTE_ERROR;

/**
 * 天行抖音热搜工厂实现
 *
 * @author xiejs
 * @since 2022-01-12
 */
@Component
@Log4j2
public class TianXingTopsearchDouyinFactory implements TopserachFactory<ApiTopsearchDouyin> {


    @Autowired
    private TianXingProperties tianXingProperties;

    @Autowired
    private TianXingDYRSFeignClient tianXingDYRSFeignClient;

    @Autowired
    private ApiTopsearchDouyinService apiTopsearchDouyinService;

    @Override
    public List<ApiTopsearchDouyin> topSearchApi() {
        JSONObject jsonObject = tianXingDYRSFeignClient.topSearchApi(tianXingProperties.getKey());
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            if (jsonObject.getInteger("code") == HttpStatus.SUCCESS) {
                JSONArray newslist = jsonObject.getJSONArray("newslist");
                List<ApiTopsearchDouyin> collect = newslist.stream().map(arrayJson -> {
                    ApiTopsearchDouyin apiTopsearchDouyin = new ApiTopsearchDouyin();
                    JSONObject json = (JSONObject) arrayJson;
                    apiTopsearchDouyin.setHotindex(json.getLong("hotindex"));
                    Integer hotwordnum = json.getInteger("label");
                    switch (hotwordnum) {
                        case 1:
                            apiTopsearchDouyin.setLabel("新");
                            break;
                        case 2:
                            apiTopsearchDouyin.setLabel("荐");
                            break;
                        case 3:
                            apiTopsearchDouyin.setLabel("热");
                            break;
                        default:
                            apiTopsearchDouyin.setLabel("-");
                    }
                    apiTopsearchDouyin.setWord(json.getString("word"));
                    return apiTopsearchDouyin;
                }).collect(Collectors.toList());
                log.info("抖音热搜批量插入成功了嘛---" + apiTopsearchDouyinService.saveBatch(collect));
                return collect;
            } else {
                log.error("天行抖音热搜服务调用成功，但返回异常");
                return new ArrayList<>();
            }
        } else {
            log.error("天行抖音热搜服务调用失败，被降级！！");
            return new ArrayList<>();
        }
    }
}
