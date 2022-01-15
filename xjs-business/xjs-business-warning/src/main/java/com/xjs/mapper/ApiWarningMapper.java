package com.xjs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.domain.ApiWarning;

/**
 * @author xiejs
 * @desc  mp api预警mapper
 * @create 2022-01-07
 */

public interface ApiWarningMapper extends BaseMapper<ApiWarning> {


    /**
     * 清空所有已处理
     * @return Integer
     */
    Integer clearAll();


    /**
     * 一键标记已读
     * @return Integer
     */
    Integer AllHaveRead();
}
