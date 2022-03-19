package com.xjs.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.mall.product.dao.BrandDao;
import com.xjs.mall.product.dao.CategoryBrandRelationDao;
import com.xjs.mall.product.dao.CategoryDao;
import com.xjs.mall.product.entity.BrandEntity;
import com.xjs.mall.product.entity.CategoryBrandRelationEntity;
import com.xjs.mall.product.entity.CategoryEntity;
import com.xjs.mall.product.service.BrandService;
import com.xjs.mall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service("categoryBrandRelationService")
@Transactional
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Resource
    private BrandDao brandDao;

    @Resource
    private CategoryDao categoryDao;

    @Autowired
    private BrandService brandService;


    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();

        BrandEntity brandEntity = brandDao.selectById(brandId);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);

        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());

        super.save(categoryBrandRelation);
    }

    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelationEntity entity = new CategoryBrandRelationEntity();
        entity.setBrandId(brandId);
        entity.setBrandName(name);

        super.update(entity, new LambdaUpdateWrapper<CategoryBrandRelationEntity>()
                .eq(CategoryBrandRelationEntity::getBrandId, brandId));
    }

    @Override
    public void updateCategory(Long catId, String name) {
        CategoryBrandRelationEntity entity = new CategoryBrandRelationEntity();
        entity.setCatelogId(catId);
        entity.setCatelogName(name);

        super.update(entity, new LambdaUpdateWrapper<CategoryBrandRelationEntity>()
                .eq(CategoryBrandRelationEntity::getCatelogId, catId));
    }

    @Override
    public List<BrandEntity> getBrandsByCatId(Long catId) {
        List<CategoryBrandRelationEntity> categoryBrandRelationEntities = super.baseMapper.selectList(
                new LambdaQueryWrapper<CategoryBrandRelationEntity>().eq(CategoryBrandRelationEntity::getCatelogId, catId)
        );
        return categoryBrandRelationEntities.stream()
                .map(item -> brandService.getById(item.getBrandId())).collect(Collectors.toList());
    }
}
