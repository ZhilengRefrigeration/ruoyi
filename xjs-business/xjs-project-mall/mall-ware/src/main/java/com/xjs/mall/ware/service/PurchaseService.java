package com.xjs.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.mall.ware.entity.PurchaseEntity;
import com.xjs.mall.ware.vo.MergeVo;
import com.xjs.mall.ware.vo.PurchaseDoneVo;
import com.xjs.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @since  2022-03-15 09:56:19
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 分页查询未领取的采购单
     * @param params 分页条件
     * @return page
     */
    PageUtils queryPageUnreceive(Map<String, Object> params);

    /**
     * 合并采购单
     * @param mergeVo 合并的参数
     */
    void mergePurchase(MergeVo mergeVo);

    /**
     * 领取采购单
     * @param ids 采购单ids
     */
    void received(List<Long> ids);

    /**
     * 完成采购单
     * @param doneVo 采购完成vo
     */
    void done(PurchaseDoneVo doneVo);
}

