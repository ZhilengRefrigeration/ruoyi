package com.xjs.apitools.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xjs.weather.domain.Casts;
import lombok.Data;

/**
 * roll预报天气实体
 * @author xiejs
 * @since 2022-01-18
 */
@Data
public class Forecasts extends Casts {

    /**
     * 星期
     */
    private String dayOfWeek;

}
