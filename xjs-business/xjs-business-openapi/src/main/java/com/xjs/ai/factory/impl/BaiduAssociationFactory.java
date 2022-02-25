package com.xjs.ai.factory.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.ai.factory.AssociationFactory;
import com.xjs.common.client.api.baidu.BaiduAssociationFeignClient;
import com.xjs.exception.ApiException;
import com.xjs.exception.BusinessException;
import com.xjs.utils.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 百度联想工厂实现
 *
 * @author xiejs
 * @since 2022-02-24
 */
@Component
@Log4j2
public class BaiduAssociationFactory implements AssociationFactory<List<String>> {

    public static final String filter = "window.baidu.sug";

    @Autowired
    private BaiduAssociationFeignClient baiduAssociationFeignClient;

    @Override
    public List<String> getData(String content) {

        String data = baiduAssociationFeignClient.associationApi(content);
        if (StringUtils.isEmpty(data)) {
            throw new ApiException("百度联想api接口调用异常");
        }

        if (data.contains(filter)) {
            //String substring = data.substring(filter.length() + 1, data.length() - 2);

            try {
                JSONObject jsonObject = JsonUtils.parseJsonp(data);
                JSONArray jsonArray = jsonObject.getJSONArray("s");

                if (jsonArray.size() == 0) {
                    return new ArrayList<>();
                }
                return jsonArray.toJavaList(String.class);

            } catch (Exception e) {
                log.error("json格式转换异常!!!"+e.getMessage());
                throw new BusinessException("json格式转换异常");
            }

        }


        return null;
    }
}
