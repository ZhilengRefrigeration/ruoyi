package com.xjs.topsearch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.topsearch.domain.ApiTopsearchBaidu;

/**
 * @author xiejs
 * @since 2022-01-11
 */
public interface ApiTopsearchBaiduMapper extends BaseMapper<ApiTopsearchBaidu> {
    /**
     * 删除百度热搜重复数据
     * @return Integer
     */
    Integer deleteRepeatData();
}
