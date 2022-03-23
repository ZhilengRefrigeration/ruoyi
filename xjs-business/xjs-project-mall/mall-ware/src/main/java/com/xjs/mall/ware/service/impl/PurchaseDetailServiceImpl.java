package com.xjs.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.mall.ware.dao.PurchaseDetailDao;
import com.xjs.mall.ware.entity.PurchaseDetailEntity;
import com.xjs.mall.ware.entity.WareInfoEntity;
import com.xjs.mall.ware.service.PurchaseDetailService;
import com.xjs.mall.ware.service.WareInfoService;
import com.xjs.mall.ware.vo.PurchaseDetailVo;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Autowired
    private WareInfoService wareInfoService;

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

        List<Object> collect = page.getRecords().stream().map(purchaseDetailEntity -> {
            PurchaseDetailVo purchaseDetailVo = new PurchaseDetailVo();
            BeanUtils.copyProperties(purchaseDetailEntity, purchaseDetailVo);
            //获取仓库信息
            WareInfoEntity wareInfoEntity = wareInfoService.getById(purchaseDetailVo.getWareId());
            purchaseDetailVo.setWareName(wareInfoEntity.getName());
            return purchaseDetailVo;
        }).collect(Collectors.toList());

        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(collect);

        return pageUtils;
    }

}
