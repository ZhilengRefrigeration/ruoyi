package com.xjs.topsearch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.topsearch.domain.ApiTopsearchWeibo;

/**
 * @author xiejs
 * @since 2022-01-12
 */
public interface ApiTopsearchWeiboMapper extends BaseMapper<ApiTopsearchWeibo> {
    /**
     * 删除微博热搜重复数据
     * @return Integer
     */
    Integer deleteRepeatData();
}
