package com.xjs.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.utils.PageUtils;
import com.xjs.mall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:37:13
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

