package com.xjs.topsearch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.topsearch.domain.ApiTopsearchDouyin;

/**
 * @author xiejs
 * @since 2022-01-12
 */
public interface ApiTopsearchDouyinMapper extends BaseMapper<ApiTopsearchDouyin> {
    /**
     * 删除抖音热搜重复数据
     * @return Integer
     */
    Integer deleteRepeatData();
}
