package com.xjs.sina.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.sina.mapper.SinaNewsMapper;
import com.xjs.sina.pojo.SinaNews;
import com.xjs.sina.service.SinaNewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 新浪新闻爬虫Service接口实现
 * @author xiejs
 * @since 2022-02-15
 */
@Service
public class SinaNewsServiceImpl extends ServiceImpl<SinaNewsMapper, SinaNews> implements SinaNewsService {
    @Resource
    private SinaNewsMapper sinaNewsMapper;

    @Override
    public int deleteRepeatData() {
        return sinaNewsMapper.deleteRepeatData();
    }
}
