package com.xjs.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.product.entity.SpuInfoEntity;
import com.xjs.mall.product.vo.spu.SpuSaveVo;
import com.xjs.utils.PageUtils;

import java.util.Map;

/**
 * spu信息
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 10:16:53
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    /**
     * 保存spu关联信息
     * @param spuInfo spu信息
     */
    void saveSpuInfo(SpuSaveVo spuInfo);

    /**
     * 保存基本spu信息
     * @param spuInfoEntity spu实体类
     */
    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);

    /**
     * 根据条件分页查询
     * @param params 条件
     * @return page
     */
    PageUtils queryPageByCondition(Map<String, Object> params);
}

