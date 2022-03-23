package com.xjs.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.xjs.mall.ware.dao.WareSkuDao;
import com.xjs.mall.ware.entity.WareInfoEntity;
import com.xjs.mall.ware.entity.WareSkuEntity;
import com.xjs.mall.ware.service.WareInfoService;
import com.xjs.mall.ware.service.WareSkuService;
import com.xjs.mall.ware.vo.WareSkuVo;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Autowired
    private WareInfoService wareInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<WareSkuEntity> wrapper = new LambdaQueryWrapper<>();

        String key = (String) params.get(Query.KEY_NAME);
        String skuId = (String) params.get("skuId");
        String wareId = (String) params.get("wareId");
        wrapper.like(StringUtils.isNotEmpty(key), WareSkuEntity::getSkuName, key).or()
                .eq(StringUtils.isNotEmpty(skuId), WareSkuEntity::getSkuId, skuId).or()
                .eq(StringUtils.isNotEmpty(wareId), WareSkuEntity::getWareId, wareId);

        IPage<WareSkuEntity> page = this.page(new Query<WareSkuEntity>().getPage(params), wrapper);

        List<Object> collect = page.getRecords().stream().map(wareSkuEntity -> {
            WareSkuVo wareSkuVo = new WareSkuVo();
            BeanUtils.copyProperties(wareSkuEntity,wareSkuVo);
            //获取仓库信息
            WareInfoEntity wareInfoEntity = wareInfoService.getById(wareSkuVo.getWareId());
            wareSkuVo.setWareName(wareInfoEntity.getName());
            return wareSkuVo;
        }).collect(Collectors.toList());

        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(collect);

        return pageUtils;
    }

}
