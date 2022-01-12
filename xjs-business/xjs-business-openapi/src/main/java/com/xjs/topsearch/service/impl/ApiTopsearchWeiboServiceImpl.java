package com.xjs.topsearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.topsearch.domain.ApiTopsearchWeibo;
import com.xjs.topsearch.mapper.ApiTopsearchWeiboMapper;
import com.xjs.topsearch.service.ApiTopsearchWeiboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @since 2022-01-12
 */
@Service
public class ApiTopsearchWeiboServiceImpl extends ServiceImpl<ApiTopsearchWeiboMapper, ApiTopsearchWeibo> implements ApiTopsearchWeiboService {
    @Resource
    private ApiTopsearchWeiboMapper apiTopsearchWeiboMapper;

    @Override
    public Integer deleteRepeatData() {
        return apiTopsearchWeiboMapper.deleteRepeatData();
    }
}
