package com.xjs.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.ware.entity.WareSkuEntity;
import com.xjs.utils.PageUtils;

import java.util.Map;

/**
 * 商品库存
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 09:56:19
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

