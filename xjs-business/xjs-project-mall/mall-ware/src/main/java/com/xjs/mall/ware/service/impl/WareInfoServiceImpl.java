package com.xjs.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.mall.ware.dao.WareInfoDao;
import com.xjs.mall.ware.entity.WareInfoEntity;
import com.xjs.mall.ware.service.WareInfoService;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get(Query.KEY_NAME);

        LambdaQueryWrapper<WareInfoEntity> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(key)) {
            wrapper.like(WareInfoEntity::getName, key).or()
                    .like(WareInfoEntity::getAddress, key).or()
                    .eq(WareInfoEntity::getAreacode, key);
        }
        wrapper.orderByDesc(WareInfoEntity::getId);

        IPage<WareInfoEntity> page = this.page(new Query<WareInfoEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

}
