package com.xjs.topsearch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.topsearch.domain.ApiTopsearchAllnetwork;

/**
 * @author xiejs
 * @since 2022-01-10
 */
public interface ApiTopsearchAllnetworkMapper extends BaseMapper<ApiTopsearchAllnetwork> {

    /**
     * 删除全网热搜重复数据
     * @return Integer
     */
    Integer deleteRepeatData();


}