package com.xjs.topsearch.service;

import java.util.List;
import java.util.Map;

/**
 * 热搜榜服务接口
 * @author xiejs
 * @since 2022-01-22
 */
public interface TopSearchService {

    /**
     * 获取所有热搜
     * @return
     */
    Map<String, List> getAllTopSearch();


    /**
     * 删除重复数据
     * @return
     */
    Integer deleteRepeat();


}
