package com.xjs.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.ware.entity.PurchaseDetailEntity;
import com.xjs.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 09:56:19
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 按采购 ID 列出详细信息
     * @param id 采购id
     * @return 采购list
     */
    List<PurchaseDetailEntity> listDetailByPurchaseId(Long id);
}

