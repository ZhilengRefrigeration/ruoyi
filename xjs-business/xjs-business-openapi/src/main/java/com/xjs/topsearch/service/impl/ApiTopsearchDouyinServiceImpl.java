package com.xjs.topsearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.topsearch.domain.ApiTopsearchDouyin;
import com.xjs.topsearch.mapper.ApiTopsearchDouyinMapper;
import com.xjs.topsearch.service.ApiTopsearchDouyinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @since 2022-01-12
 */
@Service
public class ApiTopsearchDouyinServiceImpl extends ServiceImpl<ApiTopsearchDouyinMapper, ApiTopsearchDouyin> implements ApiTopsearchDouyinService {
    @Resource
    private ApiTopsearchDouyinMapper apiTopsearchDouyinMapper;

    @Override
    public Integer deleteRepeatData() {
        return apiTopsearchDouyinMapper.deleteRepeatData();
    }
}
