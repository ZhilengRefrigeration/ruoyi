package com.xjs.sina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.sina.pojo.SinaNews;

/**
 * @author xiejs
 * @since 2022-02-15
 */
public interface SinaNewsMapper extends BaseMapper<SinaNews> {

    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();
}
