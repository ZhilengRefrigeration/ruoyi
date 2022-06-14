package com.xjs.topsearch.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.topsearch.domain.ApiTopsearchWeibo;
import com.xjs.topsearch.mapper.ApiTopsearchWeiboMapper;
import com.xjs.topsearch.service.ApiTopsearchWeiboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<ApiTopsearchWeibo> showWbSearch() {
        LambdaQueryWrapper<ApiTopsearchWeibo> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(ApiTopsearchWeibo::getHotword,ApiTopsearchWeibo::getId);
        wrapper.orderByDesc(ApiTopsearchWeibo::getCreateTime);
        wrapper.orderByDesc(ApiTopsearchWeibo::getHotnum);
        wrapper.last("limit 5");

        return apiTopsearchWeiboMapper.selectList(wrapper);
    }
}
