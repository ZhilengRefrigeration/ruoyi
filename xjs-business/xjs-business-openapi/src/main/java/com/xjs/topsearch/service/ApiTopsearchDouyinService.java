package com.xjs.topsearch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.topsearch.domain.ApiTopsearchDouyin;

/**
 * @author xiejs
 * @since 2022-01-12
 */
public interface ApiTopsearchDouyinService extends IService<ApiTopsearchDouyin> {

    /**
     * 删除抖音热搜重复数据
     * @return Integer
     */
    Integer deleteRepeatData();
}
