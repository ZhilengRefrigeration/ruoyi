package com.xjs.topsearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.topsearch.domain.ApiTopsearchAllnetwork;
import com.xjs.topsearch.mapper.ApiTopsearchAllnetworkMapper;
import com.xjs.topsearch.service.ApiTopsearchAllnetworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @since 2022-01-10
 */
@Service
public class ApiTopsearchAllnetworkServiceImpl extends ServiceImpl<ApiTopsearchAllnetworkMapper, ApiTopsearchAllnetwork> implements ApiTopsearchAllnetworkService {
    @Resource
    private ApiTopsearchAllnetworkMapper apiTopsearchAllnetworkMapper;

    @Override
    public Integer deleteRepeatData() {
        return apiTopsearchAllnetworkMapper.deleteRepeatData();
    }
}
