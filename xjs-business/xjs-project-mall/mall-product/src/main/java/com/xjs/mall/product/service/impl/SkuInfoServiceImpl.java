package com.xjs.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.mall.product.dao.SkuInfoDao;
import com.xjs.mall.product.entity.BrandEntity;
import com.xjs.mall.product.entity.CategoryEntity;
import com.xjs.mall.product.entity.SkuInfoEntity;
import com.xjs.mall.product.entity.SpuInfoEntity;
import com.xjs.mall.product.service.BrandService;
import com.xjs.mall.product.service.CategoryService;
import com.xjs.mall.product.service.SkuInfoService;
import com.xjs.mall.product.service.SpuInfoService;
import com.xjs.mall.product.vo.spu.SkuInfoVo;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Autowired
    private SpuInfoService spuInfoService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    @Override
    public void saveSkuInfo(SkuInfoEntity skuInfoEntity) {
        super.baseMapper.insert(skuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        LambdaQueryWrapper<SkuInfoEntity> wrapper = new LambdaQueryWrapper<>();

        String key = (String) params.get(Query.KEY_NAME);
        String brandId = (String) params.get("brandId");
        String catelogId = (String) params.get("catelogId");
        String min = (String) params.get("min");
        String max = (String) params.get("max");

        wrapper.and(StringUtils.isNotEmpty(key), wr -> {
                    wr.like(SkuInfoEntity::getSkuName, key).or()
                            .like(SkuInfoEntity::getSkuDesc, key).or()
                            .like(SkuInfoEntity::getSkuTitle, key).or()
                            .like(SkuInfoEntity::getSkuSubtitle, key);
                })
                .eq(StringUtils.isNotEmpty(brandId) && !"0".equalsIgnoreCase(brandId), SkuInfoEntity::getBrandId, brandId)
                .eq(StringUtils.isNotEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId), SkuInfoEntity::getCatalogId, catelogId)
                .ge(StringUtils.isNotEmpty(min) && !"0".equalsIgnoreCase(min), SkuInfoEntity::getPrice, min)
                .le(StringUtils.isNotEmpty(max) && !"0".equalsIgnoreCase(max), SkuInfoEntity::getPrice, max)
                .orderByDesc(SkuInfoEntity::getSkuId);

        IPage<SkuInfoEntity> page = this.page(new Query<SkuInfoEntity>().getPage(params), wrapper);

        List<SkuInfoEntity> records = page.getRecords();
        List<SkuInfoVo> collect = records.stream().map(skuInfoEntity -> {
            SkuInfoVo skuInfoVo = new SkuInfoVo();
            BeanUtils.copyProperties(skuInfoEntity, skuInfoVo);

            //获取spu信息
            SpuInfoEntity spuInfoEntity = spuInfoService.getById(skuInfoVo.getSpuId());
            skuInfoVo.setSpuName(spuInfoEntity.getSpuName());

            //获取三级分类信息
            CategoryEntity categoryEntity = categoryService.getById(skuInfoVo.getCatalogId());
            skuInfoVo.setCatalogName(categoryEntity.getName());

            //获取品牌信息
            BrandEntity brandEntity = brandService.getById(skuInfoVo.getBrandId());
            skuInfoVo.setBrandName(brandEntity.getName());

            return skuInfoVo;
        }).collect(Collectors.toList());


        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(collect);
        return pageUtils;
    }

}
