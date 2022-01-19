package com.xjs.apitools.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * api垃圾分类实体
 *
 * @author xiejs
 * @since 2022-01-19
 */
@Data
public class ApiGarbageSorting implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 查询出来的匹配结果
     */
    private Map<String, String> aim;


    /**
     * 物品垃圾分类类型
     */
    private Map<String, String> recommendList;




}
