package com.xjs.area.factory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xjs.area.domain.Area;
import com.xjs.common.client.api.gaode.GaodeAreaFeignClient;
import com.xjs.properties.GaodeProperties;
import com.xjs.weather.domain.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.xjs.consts.ApiConst.*;

/**
 * 区域编码工厂接口 高德实现
 *
 * @author xiejs
 * @since 2022-03-22
 */
@Component
public class GaodeAreaFactoryImpl implements AreaFactory<List<Area>, RequestBody> {

    @Autowired
    private GaodeProperties gaodeProperties;
    @Autowired
    private GaodeAreaFeignClient gaodeAreaFeignClient;

    @Override
    public List<Area> getArea(RequestBody requestBody) {
        requestBody.setSubdistrict(4);  //设置显示下级行政区级数
        requestBody.setKey(gaodeProperties.getKey());

        JSONObject jsonObject = gaodeAreaFeignClient.AreaApi(requestBody);
        if (!jsonObject.containsKey(DEMOTE_ERROR)) {
            if (INFOCODE_VALUE.equals(jsonObject.getString(INFOCODE))) {
                JSONObject districts_1 = jsonObject.getJSONArray("districts").getJSONObject(0);
                JSONArray districts_2 = districts_1.getJSONArray("districts");


                return districts_2.toJavaList(Area.class);
            }
        }

        return new ArrayList<>();
    }
}
