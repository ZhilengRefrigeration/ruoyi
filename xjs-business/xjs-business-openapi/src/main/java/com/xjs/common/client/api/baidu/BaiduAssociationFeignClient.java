package com.xjs.common.client.api.baidu;

import com.xjs.annotation.ApiLog;
import com.xjs.common.client.factory.BaiduAssociationFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.xjs.consts.ApiConst.BAIDU_ASSOCIATION;
import static com.xjs.consts.ApiConst.BAIDU_ASSOCIATION_URL;

/**
 *
 * 百度语义联想api
 * @author xiejs
 * @since 2022-02-24
 */
@FeignClient(name = "baiduAssociation", url = BAIDU_ASSOCIATION_URL, fallbackFactory = BaiduAssociationFeignFactory.class)
public interface BaiduAssociationFeignClient {

    @GetMapping
    @ApiLog(name = BAIDU_ASSOCIATION,
            url = BAIDU_ASSOCIATION_URL,
            method = "Get")
    String associationApi(@RequestParam("wd") String wd);


}
