package com.xjs.weather.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实时天气实体
 *
 * @author xiejs
 * @since 2022-01-16
 */
@Data
@TableName("api_now_weather")
public class NowWeather implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 省份名
     */
    @Excel(name = "省份名")
    private String province;

    /**
     * 城市名
     */
    @Excel(name = "城市名")
    private String city;

    /**
     * 城市编码
     */
    @Excel(name = "城市编码")
    private String adcode;

    /**
     * 天气现象（汉字描述）
     */
    @Excel(name = "天气现象")
    private String weather;

    /**
     * 实时气温，单位：摄氏度
     */
    @Excel(name = "实时气温，单位：摄氏度")
    private String temperature;

    /**
     * 风向描述
     */
    @Excel(name = "风向描述")
    private String winddirection;

    /**
     * 风力级别，单位：级
     */
    @Excel(name = "风力级别，单位：级")
    private String windpower;

    /**
     * 空气湿度
     */
    @Excel(name = "空气湿度")
    private String humidity;

    /**
     * 数据发布的时间
     */
    @Excel(name = "数据发布的时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reporttime;

    @Excel(name = "创建时间" ,dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;



}
