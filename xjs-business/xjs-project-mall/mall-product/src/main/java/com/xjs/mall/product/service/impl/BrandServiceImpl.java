package com.xjs.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.mall.product.dao.BrandDao;
import com.xjs.mall.product.entity.BrandEntity;
import com.xjs.mall.product.service.BrandService;
import com.xjs.mall.product.service.CategoryBrandRelationService;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service("brandService")
@Transactional
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get(Query.KEY_NAME);

        LambdaQueryWrapper<BrandEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(StringUtils.isNotEmpty(key), obj ->{
            obj.like(BrandEntity::getName, key)
                    .or().like(BrandEntity::getDescript, key)
                    .or().eq(BrandEntity::getFirstLetter, key);
        });

        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void updateDetail(BrandEntity brand) {
        //保证冗余字段的数据一致性
        super.updateById(brand);

        if (StringUtils.isNotEmpty(brand.getName())) {
            //同步更新其他表
            categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());

            // todo 更新其他关联信息
        }
    }

}
