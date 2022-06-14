package com.xjs.topsearch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.topsearch.domain.ApiTopsearchWeibo;

import java.util.List;

/**
 * @author xiejs
 * @since 2022-01-12
 */
public interface ApiTopsearchWeiboService extends IService<ApiTopsearchWeibo> {
    /**
     * 删除微博热搜重复数据
     * @return Integer
     */
    Integer deleteRepeatData();

    /**
     * 展示最新的微博热搜
     * @return ApiTopsearchWeibo
     */
    List<ApiTopsearchWeibo> showWbSearch();

}
