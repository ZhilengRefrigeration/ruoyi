package com.xjs.weixin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.weixin.pojo.WeiXinSouGou;

/**
 * 微信搜狗mapper
 * @author xiejs
 * @since 2022-02-22
 */
public interface WeiXinSouGouMapper extends BaseMapper<WeiXinSouGou> {

    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();
}
