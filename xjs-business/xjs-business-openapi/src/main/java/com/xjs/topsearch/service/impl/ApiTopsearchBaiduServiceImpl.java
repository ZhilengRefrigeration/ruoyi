package com.xjs.topsearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.topsearch.domain.ApiTopsearchBaidu;
import com.xjs.topsearch.mapper.ApiTopsearchBaiduMapper;
import com.xjs.topsearch.service.ApiTopsearchBaiduService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @since 2022-01-11
 */
@Service
public class ApiTopsearchBaiduServiceImpl extends ServiceImpl<ApiTopsearchBaiduMapper, ApiTopsearchBaidu> implements ApiTopsearchBaiduService {

    @Resource
    private ApiTopsearchBaiduMapper apiTopsearchBaiduMapper;

    @Override
    public Integer deleteRepeatData() {
        return apiTopsearchBaiduMapper.deleteRepeatData();
    }
}
