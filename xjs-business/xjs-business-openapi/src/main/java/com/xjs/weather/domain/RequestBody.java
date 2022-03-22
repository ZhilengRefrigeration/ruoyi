package com.xjs.weather.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 高德天气预报接口请求参数
 *
 * @author xiejs
 * @since 2022-01-16
 */
@Data
//链式调用
@Accessors(chain = true)
public class RequestBody {

    /**
     * 请求服务权限标识:
     * 用户在高德地图官网申请web服务API类型KEY
     */
    private String key;

    /**
     * 城市编码:
     * 输入城市的adcode，adcode信息可参考城市编码表
     */
    private String city;

    /**
     * 气象类型:
     * 可选值：base/all
     * base:返回实况天气
     * all:返回预报天气
     * <br>
     * 返回结果控制:
     * 可选值：base/all
     * base:不返回行政区边界坐标点；all:只返回当前查询district的边界值，不返回子节点的边界值；
     */
    private String extensions;

    /**
     * 返回格式:
     * 可选值：JSON,XML
     */
    private String output = "JSON";

    /**
     * 子级行政区:
     * <br>
     * 可选值：0、1、2、3
     * <br>
     * 0：不返回下级行政区；
     * <br>
     * 1：返回下一级行政区；
     * <br>
     * 2：返回下两级行政区；
     * <br>
     * 3：返回下三级行政区；
     */
    private Integer subdistrict;


}
