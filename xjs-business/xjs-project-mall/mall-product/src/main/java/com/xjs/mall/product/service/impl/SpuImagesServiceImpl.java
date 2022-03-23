package com.xjs.mall.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.mall.product.dao.SpuImagesDao;
import com.xjs.mall.product.entity.SpuImagesEntity;
import com.xjs.mall.product.service.SpuImagesService;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {


    @Override
    public void saveImages(Long id, List<String> images) {
        if (CollUtil.isNotEmpty(images)) {
            List<SpuImagesEntity> collect = images.stream().map(img -> {
                SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
                spuImagesEntity.setSpuId(id);
                spuImagesEntity.setImgUrl(img);
                return spuImagesEntity;
            }).collect(Collectors.toList());

            super.saveBatch(collect);
        }
    }

}
