package com.xjs.mall.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.consts.ProductConstant;
import com.xjs.mall.RemoteCouponFeign;
import com.xjs.mall.RemoteSearchFeign;
import com.xjs.mall.RemoteWareFeign;
import com.xjs.mall.other.R;
import com.xjs.mall.product.dao.SpuInfoDao;
import com.xjs.mall.product.entity.*;
import com.xjs.mall.product.service.*;
import com.xjs.mall.product.vo.spu.*;
import com.xjs.mall.to.SkuHasStockVo;
import com.xjs.mall.to.SkuReductionTo;
import com.xjs.mall.to.SpuBoundTo;
import com.xjs.mall.to.es.SkuEsModel;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private AttrService attrService;
    @Resource
    private RemoteWareFeign remoteWareFeign;
    @Resource
    private RemoteSearchFeign remoteSearchFeign;


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
                skuInfoEntity.setSkuDesc(spuInfoEntity.getSpuDescription());
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

        IPage<SpuInfoEntity> page = this.page(new Query<SpuInfoEntity>().getPage(params), wrapper);

        List<SpuInfoEntity> spuInfoEntities = page.getRecords();
        List<SpuInfoVo> collect = spuInfoEntities.stream().map(spuInfoEntity -> {
            SpuInfoVo spuInfoVo = new SpuInfoVo();
            BeanUtils.copyProperties(spuInfoEntity, spuInfoVo);

            //获取分类信息
            CategoryEntity categoryEntity = categoryService.getById(spuInfoEntity.getCatalogId());
            spuInfoVo.setCatalogName(categoryEntity.getName());

            //获取品牌信息
            BrandEntity brandEntity = brandService.getById(spuInfoEntity.getBrandId());
            spuInfoVo.setBrandName(brandEntity.getName());

            return spuInfoVo;
        }).collect(Collectors.toList());

        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(collect);

        return pageUtils;
    }

    @Override
    public void up(Long spuId) {
        // 1、查出当前spuId对应的所有sku信息，品牌名字
        List<SkuInfoEntity> skus = skuInfoService.getSkusBySpuId(spuId);

        // 查询当前sku的所有规格属性
        List<ProductAttrValueEntity> baseAttrs = productAttrValueService.baseAtteListForSpu(spuId);
        List<Long> attrIds = baseAttrs.stream().map(ProductAttrValueEntity::getAttrId).collect(Collectors.toList());

        List<Long> searchAttrIds = attrService.selectSearchAttrIds(attrIds);
        Set<Long> idSet = new HashSet<>(searchAttrIds);

        List<SkuEsModel.Attrs> attrList = baseAttrs.stream()
                .filter(item ->
                        idSet.contains(item.getAttrId()))
                .map(item -> {
                    SkuEsModel.Attrs attrs = new SkuEsModel.Attrs();
                    BeanUtils.copyProperties(item, attrs);
                    return attrs;
                })
                .collect(Collectors.toList());

        // 发送远程调用  库存系统查询是否有库存
        List<Long> skuIds = skus.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());

        Map<Long, Boolean> stockMap = null;
        try {
            List<SkuHasStockVo> stockVoList = remoteWareFeign.getSkuHasStock(skuIds);
            stockMap = stockVoList.stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::getHasStock));
        } catch (Exception e) {
            log.error("库存服务查询异常：原因{}", e);
        }

        // 2、封装每个sku的信息
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuEsModel> upProducts = skus.stream().map(sku -> {
            //组装需要的数据
            SkuEsModel skuEsModel = new SkuEsModel();
            BeanUtils.copyProperties(sku, skuEsModel);
            //skuPrice,skuImg,hasStock,hotScore
            skuEsModel.setSkuPrice(sku.getPrice());
            skuEsModel.setSkuImg(sku.getSkuDefaultImg());

            //热度评分 0
            skuEsModel.setHotScore(0L);

            //设置库存信息
            if (finalStockMap == null) {
                skuEsModel.setHasStock(true);
            } else {
                skuEsModel.setHasStock(finalStockMap.get(sku.getSkuId()));
            }

            //查询品牌分类的名字信息
            BrandEntity brandEntity = brandService.getById(skuEsModel.getBrandId());
            skuEsModel.setBrandName(brandEntity.getName());
            skuEsModel.setBrandImg(brandEntity.getLogo());

            CategoryEntity categoryEntity = categoryService.getById(skuEsModel.getCatalogId());
            skuEsModel.setCatalogName(categoryEntity.getName());

            //设置检索属性
            skuEsModel.setAttrs(attrList);

            return skuEsModel;
        }).collect(Collectors.toList());

        // 将数据发送给es进行保存
        R r = remoteSearchFeign.productStatusUp(upProducts);
        if (r.getCode() == 0) {
            //远程调用成功
            //修改当前spu的状态
            super.baseMapper.updateSpuStatus(spuId, ProductConstant.StatusEnum.UP_SPU.getCode());

        } else {
            //远程调用失败
            // todo 重复调用,接口幂等性,重试机制

        }

    }


}
