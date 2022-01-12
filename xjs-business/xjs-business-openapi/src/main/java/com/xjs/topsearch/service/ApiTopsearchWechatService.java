package com.xjs.topsearch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.topsearch.domain.ApiTopsearchWechat;

/**
 * @author xiejs
 * @since 2022-01-11
 */
public interface ApiTopsearchWechatService extends IService<ApiTopsearchWechat> {
    /**
     * 删除微信热搜重复数据
     * @return Integer
     */
    Integer deleteRepeatData();
}
