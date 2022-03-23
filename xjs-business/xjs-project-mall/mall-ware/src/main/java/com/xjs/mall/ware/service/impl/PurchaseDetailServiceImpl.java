package com.xjs.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.mall.ware.dao.PurchaseDetailDao;
import com.xjs.mall.ware.entity.PurchaseDetailEntity;
import com.xjs.mall.ware.service.PurchaseDetailService;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<PurchaseDetailEntity> wrapper = new LambdaQueryWrapper<>();

        String key = (String) params.get(Query.KEY_NAME);
        String status = (String) params.get("status");
        String wareId = (String) params.get("wareId");

        wrapper.and(StringUtils.isNotEmpty(key), w -> {
                    w.eq(PurchaseDetailEntity::getPurchaseId, key)
                            .or()
                            .eq(PurchaseDetailEntity::getSkuId, key);
                })
                .eq(StringUtils.isNotEmpty(status), PurchaseDetailEntity::getStatus, status)
                .eq(StringUtils.isNotEmpty(wareId), PurchaseDetailEntity::getWareId, wareId);

        IPage<PurchaseDetailEntity> page = this.page(new Query<PurchaseDetailEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

}
