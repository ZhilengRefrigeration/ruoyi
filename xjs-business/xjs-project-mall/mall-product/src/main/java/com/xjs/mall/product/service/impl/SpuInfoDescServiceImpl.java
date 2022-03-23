package com.xjs.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.mall.product.dao.SpuInfoDescDao;
import com.xjs.mall.product.entity.SpuInfoDescEntity;
import com.xjs.mall.product.service.SpuInfoDescService;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("spuInfoDescService")
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescDao, SpuInfoDescEntity> implements SpuInfoDescService {


    @Override
    public void saveSpuInfoDesc(SpuInfoDescEntity descEntity) {
        super.baseMapper.insert(descEntity);
    }

}
