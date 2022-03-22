package com.xjs.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.area.domain.Area;

/**
 * 区域编码mapper
 *
 * @author xiejs
 * @since 2022-03-22
 */
public interface AreaMapper extends BaseMapper<Area> {

    /**
     * 清空表数据
     *
     */
    void truncateArea();

}
