package com.xjs.mall.ware.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.xjs.mall.RemoteProductFeign;
import com.xjs.mall.other.R;
import com.xjs.mall.ware.dao.WareSkuDao;
import com.xjs.mall.ware.entity.WareInfoEntity;
import com.xjs.mall.ware.entity.WareSkuEntity;
import com.xjs.mall.ware.service.WareInfoService;
import com.xjs.mall.ware.service.WareSkuService;
import com.xjs.mall.ware.vo.WareSkuVo;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("wareSkuService")
@Transactional
@Log4j2
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Autowired
    private WareInfoService wareInfoService;
    @Resource
    private RemoteProductFeign remoteProductFeign;

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
            BeanUtils.copyProperties(wareSkuEntity, wareSkuVo);
            //获取仓库信息
            WareInfoEntity wareInfoEntity = wareInfoService.getById(wareSkuVo.getWareId());
            wareSkuVo.setWareName(wareInfoEntity.getName());
            return wareSkuVo;
        }).collect(Collectors.toList());

        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(collect);

        return pageUtils;
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //判断是否有库存记录，有--新增，无--更新
        List<WareSkuEntity> wareSkuEntities = super.baseMapper.selectList(new LambdaQueryWrapper<WareSkuEntity>()
                .eq(WareSkuEntity::getSkuId, skuId)
                .eq(WareSkuEntity::getWareId, wareId));

        if (CollUtil.isEmpty(wareSkuEntities)) {
            WareSkuEntity wareSkuEntity = new WareSkuEntity();
            wareSkuEntity.setSkuId(skuId);
            wareSkuEntity.setStock(skuNum);
            wareSkuEntity.setWareId(wareId);
            wareSkuEntity.setStockLocked(0);

            //远程查询sku的名字   没写降级，所以需要try catch
            try {
                R r = remoteProductFeign.getSkuNameById(skuId);
                if (r.getCode() == 0) {
                    wareSkuEntity.setSkuName((String) r.get("msg"));
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            super.baseMapper.insert(wareSkuEntity);
        } else {
            super.baseMapper.addStock(skuId, wareId, skuNum);
        }

    }

}
