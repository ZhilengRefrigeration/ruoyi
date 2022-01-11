package com.xjs.topsearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.topsearch.domain.ApiTopsearchWechat;
import com.xjs.topsearch.mapper.ApiTopsearchWechatMapper;
import com.xjs.topsearch.service.ApiTopsearchWechatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @since 2022-01-11
 */
@Service
public class ApiTopsearchWechatServiceImpl extends ServiceImpl<ApiTopsearchWechatMapper, ApiTopsearchWechat> implements ApiTopsearchWechatService {
    @Resource
    private ApiTopsearchWechatMapper apiTopsearchWechatMapper;

    @Override
    public Integer deleteRepeatData() {
        return apiTopsearchWechatMapper.deleteRepeatData();
    }
}
