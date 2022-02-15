package com.xjs.sina.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.sina.pojo.SinaNews;

/**
 * 新浪新闻爬虫Service接口
 * @author xiejs
 * @since 2022-02-15
 */
public interface SinaNewsService extends IService<SinaNews> {
    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();
}
