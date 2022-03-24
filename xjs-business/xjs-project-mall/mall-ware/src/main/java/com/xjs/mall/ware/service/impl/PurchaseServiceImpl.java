package com.xjs.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.consts.WareConstant;
import com.xjs.exception.MallException;
import com.xjs.mall.ware.dao.PurchaseDao;
import com.xjs.mall.ware.entity.PurchaseDetailEntity;
import com.xjs.mall.ware.entity.PurchaseEntity;
import com.xjs.mall.ware.entity.WareInfoEntity;
import com.xjs.mall.ware.service.PurchaseDetailService;
import com.xjs.mall.ware.service.PurchaseService;
import com.xjs.mall.ware.service.WareInfoService;
import com.xjs.mall.ware.vo.MergeVo;
import com.xjs.mall.ware.vo.PurchaseVo;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.xjs.consts.WareConstant.PurchaseStatusEnum.*;


@Service("purchaseService")
@Transactional
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Autowired
    private PurchaseDetailService purchaseDetailService;
    @Autowired
    private WareInfoService wareInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<PurchaseEntity> wrapper = new LambdaQueryWrapper<>();

        String key = (String) params.get(Query.KEY_NAME);
        wrapper.and(StringUtils.isNotEmpty(key), wr -> {
            wr.like(PurchaseEntity::getAssigneeName, key).or()
                    .eq(PurchaseEntity::getPhone, key);
        });

        IPage<PurchaseEntity> page = this.page(new Query<PurchaseEntity>().getPage(params), wrapper);
        List<Object> collect = page.getRecords().stream().map(purchaseEntity -> {
            PurchaseVo purchaseVo = new PurchaseVo();
            BeanUtils.copyProperties(purchaseEntity,purchaseVo);

            if (purchaseEntity.getWareId() != null) {
                //获取仓库信息
                WareInfoEntity wareInfoEntity = wareInfoService.getById(purchaseVo.getWareId());
                purchaseVo.setWareName(wareInfoEntity.getName());
            }

            return purchaseVo;
        }).collect(Collectors.toList());

        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(collect);
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageUnreceive(Map<String, Object> params) {
        LambdaQueryWrapper<PurchaseEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PurchaseEntity::getStatus, 0).or().eq(PurchaseEntity::getStatus, 1);

        IPage<PurchaseEntity> page = this.page(new Query<PurchaseEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

    @Override
    public void mergePurchase(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        //新建采购单
        if (purchaseId == null) {
            PurchaseEntity purchaseEntity = new PurchaseEntity();
            purchaseEntity.setCreateTime(new Date());
            purchaseEntity.setUpdateTime(new Date());
            purchaseEntity.setPriority(1);      //设置优先级默认1
            purchaseEntity.setStatus(CREATED.getCode());     //默认采购单状态

            super.save(purchaseEntity);

            purchaseId = purchaseEntity.getId();
        }

        // 确认采购单状态是 0 或 1 才可以合并
        List<Long> items = mergeVo.getItems();

        for (Long item : items) {
            PurchaseDetailEntity entity = purchaseDetailService.getById(item);
            if (!(entity.getStatus() == CREATED.getCode() || entity.getStatus() == ASSIGNED.getCode())) {
                throw new MallException("采购单只有在新建或分配状态才能合并");
            }
        }

        //合并采购单
        Long finalPurchaseId = purchaseId;

        List<PurchaseDetailEntity> collect = items.stream().map(i -> {
            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
            detailEntity.setId(i);
            detailEntity.setPurchaseId(finalPurchaseId);
            detailEntity.setStatus(ASSIGNED.getCode());
            return detailEntity;
        }).collect(Collectors.toList());

        purchaseDetailService.updateBatchById(collect);

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(purchaseId);
        purchaseEntity.setUpdateTime(new Date());
        super.updateById(purchaseEntity);

    }

    @Override
    public void received(List<Long> ids) {
        //确认当前采购单是新建或者已分配状态
        List<PurchaseEntity> collect = ids.stream().map(super::getById)
                .filter(item ->
                        item.getStatus() == CREATED.getCode() || item.getStatus() == ASSIGNED.getCode()
                )
                .peek(item -> {
                    item.setStatus(RECEIVE.getCode());
                    item.setUpdateTime(new Date());
                })
                .collect(Collectors.toList());

        //改变采购单的状态
        super.updateBatchById(collect);

        //改变采购项的状态
        collect.forEach(item -> {
            List<PurchaseDetailEntity> entities = purchaseDetailService.listDetailByPurchaseId(item.getId());
            List<PurchaseDetailEntity> PurchaseDetailEntityList = entities.stream().map(entity -> {
                PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
                purchaseDetailEntity.setId(entity.getId());
                purchaseDetailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode());
                return purchaseDetailEntity;
            }).collect(Collectors.toList());
            purchaseDetailService.updateBatchById(PurchaseDetailEntityList);
        });

    }

}
