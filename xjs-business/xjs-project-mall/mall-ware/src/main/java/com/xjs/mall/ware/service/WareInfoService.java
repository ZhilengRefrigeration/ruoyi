package com.xjs.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.business.api.domain.Area;
import com.xjs.mall.ware.entity.WareInfoEntity;
import com.xjs.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 仓库信息
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 09:56:19
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取所有省级区域
     * @return list
     */
    List<Area> getProvinceArea();

    /**
     * 根据父ID获取区域
     * @param pid 父id
     * @return list
     */
    List<Area> getAreaByParentId(Long pid);
}

