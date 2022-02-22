package com.xjs.weixin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.weixin.pojo.WeiXinSouGou;

/**
 * 微信搜狗service接口
 * @author xiejs
 * @since 2022-02-22
 */
public interface WeiXinSouGouService extends IService<WeiXinSouGou> {

    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();
}
