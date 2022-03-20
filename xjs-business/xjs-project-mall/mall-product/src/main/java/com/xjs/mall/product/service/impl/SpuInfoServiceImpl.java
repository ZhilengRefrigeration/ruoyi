package com.xjs.mall.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.mall.RemoteCouponFeign;
import com.xjs.mall.product.dao.SpuInfoDao;
import com.xjs.mall.product.entity.*;
import com.xjs.mall.product.service.*;
import com.xjs.mall.product.vo.spu.*;
import com.xjs.mall.to.SkuReductionTo;
import com.xjs.mall.to.SpuBoundTo;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("spuInfoService")
@Transactional
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuInfoDescService spuInfoDescService;
    @Autowired
    private SpuImagesService spuImagesService;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Resource
    private RemoteCouponFeign remoteCouponFeign;


    @Override
    public void saveSpuInfo(SpuSaveVo spuSaveVo) {
        //1、保存spu基本信息 pms_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuSaveVo, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(spuInfoEntity);
        //---------------------------------------------------------------------------------------------------------

        //2、保存spu的描述图片 pms_spu_info_desc
        List<String> decript = spuSaveVo.getDecript();
        SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
        descEntity.setSpuId(spuInfoEntity.getId());
        descEntity.setDecript(String.join(",", decript));    //把list集合用 , 分割成字符串
        spuInfoDescService.saveSpuInfoDesc(descEntity);
        //---------------------------------------------------------------------------------------------------------

        //3、保存spu的图片集 pms_spu_images
        List<String> images = spuSaveVo.getImages();
        spuImagesService.saveImages(spuInfoEntity.getId(), images);
        //---------------------------------------------------------------------------------------------------------

        //4、保存spu的规格参数 pms_product_attr_value
        List<BaseAttrs> baseAttrs = spuSaveVo.getBaseAttrs();
        productAttrValueService.saveProductAttr(baseAttrs, spuInfoEntity.getId());
        //---------------------------------------------------------------------------------------------------------

        // 保存spu的积分信息
        Bounds bounds = spuSaveVo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();   //服务之间调用传输对象To
        BeanUtils.copyProperties(bounds, spuBoundTo);
        spuBoundTo.setSpuId(spuInfoEntity.getId());
        remoteCouponFeign.saveSpuBounds(spuBoundTo);

        //5、保存当前spu对应的sku信息
        //---------------------------------------------------------------------------------------------------------
        //5.1、保存sku的基本信息 pms_sku_info
        List<Skus> skus = spuSaveVo.getSkus();
        if (CollUtil.isNotEmpty(skus)) {
            skus.forEach(item -> {

                String defaultImg = "";
                for (Images image : item.getImages()) {
                    if (image.getDefaultImg() == 1) {   //判断是默认图片
                        defaultImg = image.getImgUrl();
                    }
                }

                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item, skuInfoEntity);
                skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);     //销量默认0
                skuInfoEntity.setSpuId(spuInfoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                skuInfoService.saveSkuInfo(skuInfoEntity);
                //---------------------------------------------------------------------------------------------------------

                Long skuId = skuInfoEntity.getSkuId();

                //5.2、保存sku的图片信息
                List<SkuImagesEntity> imagesEntities = item.getImages().stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                    return skuImagesEntity;
                }).filter(entity ->
                        StringUtils.isNotEmpty(entity.getImgUrl())
                ).collect(Collectors.toList());
                skuImagesService.saveBatch(imagesEntities);
                //---------------------------------------------------------------------------------------------------------

                //5.3、保存sku的销售属性信息
                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(a -> {
                    SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(a, skuSaleAttrValueEntity);
                    skuSaleAttrValueEntity.setSkuId(skuId);
                    return skuSaleAttrValueEntity;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);
                //---------------------------------------------------------------------------------------------------------

                //5.4、保存sku的优惠满减等信息
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(item, skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                if (skuReductionTo.getFullCount() > 0 &&
                        skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) == 1) {
                    remoteCouponFeign.saveSkuReduction(skuReductionTo);
                }


            });
        }
    }

    @Override
    public void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity) {
        super.baseMapper.insert(spuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        LambdaQueryWrapper<SpuInfoEntity> wrapper = new LambdaQueryWrapper<>();

        String key = (String) params.get(Query.KEY_NAME);
        String status = (String) params.get("status");
        String brandId = (String) params.get("brandId");
        String catelogId = (String) params.get("catelogId");

        wrapper.and(StringUtils.isNotEmpty(key), wr -> {
                    wr.like(SpuInfoEntity::getSpuName, key).or()
                            .like(SpuInfoEntity::getSpuDescription, key);
                })
                .eq(StringUtils.isNotEmpty(status), SpuInfoEntity::getPublishStatus, status)
                .eq(StringUtils.isNotEmpty(brandId), SpuInfoEntity::getBrandId, brandId)
                .eq(StringUtils.isNotEmpty(catelogId), SpuInfoEntity::getCatalogId, catelogId);
        wrapper.orderByDesc(SpuInfoEntity::getCreateTime);

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }


}
