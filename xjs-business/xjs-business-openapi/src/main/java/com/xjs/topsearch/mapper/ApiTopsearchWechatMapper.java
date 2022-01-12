package com.xjs.topsearch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.topsearch.domain.ApiTopsearchWechat;

/**
 * @author xiejs
 * @since 2022-01-11
 */
public interface ApiTopsearchWechatMapper extends BaseMapper<ApiTopsearchWechat> {
    /**
     * 删除微信热搜重复数据
     * @return Integer
     */
    Integer deleteRepeatData();
}
