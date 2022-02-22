package com.xjs.weixin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.weixin.mapper.WeiXinSouGouMapper;
import com.xjs.weixin.pojo.WeiXinSouGou;
import com.xjs.weixin.service.WeiXinSouGouService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 微信搜狗service实现
 * @author xiejs
 * @since 2022-02-22
 */
@Service
public class WeiXinSouGouServiceImpl extends ServiceImpl<WeiXinSouGouMapper, WeiXinSouGou> implements WeiXinSouGouService {

    @Resource
    private WeiXinSouGouMapper weiXinSouGouMapper;

    @Override
    public int deleteRepeatData() {
        return weiXinSouGouMapper.deleteRepeatData();
    }
}
