package com.xjs.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.area.domain.Area;

import java.util.List;

/**
 * 区域编码service接口
 *
 * @author xiejs
 * @since 2022-03-22
 */
public interface AreaService extends IService<Area> {

    /**
     * 保存区域编码
     */
    void saveArea();

    /**
     * 清空表数据
     */
    void truncateArea();

    /**
     * 获取所有省级区域
     */
    List<Area> getProvinceArea();

    /**
     * 根据父ID获取区域
     * @param pid 父id
     * @return areaList
     */
    List<Area> getAreaByParentId(Long pid);

}
