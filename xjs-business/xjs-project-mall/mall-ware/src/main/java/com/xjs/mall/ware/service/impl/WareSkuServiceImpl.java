package com.xjs.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.mall.ware.dao.WareSkuDao;
import com.xjs.mall.ware.entity.WareSkuEntity;
import com.xjs.mall.ware.service.WareSkuService;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<WareSkuEntity> wrapper = new LambdaQueryWrapper<>();

        String key = (String) params.get(Query.KEY_NAME);
        if (StringUtils.isNotEmpty(key)) {
            wrapper.eq(WareSkuEntity::getSkuName, key).or()
                    .eq(WareSkuEntity::getSkuId, key).or()
                    .eq(WareSkuEntity::getWareId, key);
        }

        IPage<WareSkuEntity> page = this.page(new Query<WareSkuEntity>().getPage(params),wrapper);

        return new PageUtils(page);
    }

}
