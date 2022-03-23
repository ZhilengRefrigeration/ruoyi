package com.xjs.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.business.api.RemoteAreaFeign;
import com.xjs.business.api.domain.Area;
import com.xjs.mall.ware.dao.WareInfoDao;
import com.xjs.mall.ware.entity.WareInfoEntity;
import com.xjs.mall.ware.service.WareInfoService;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Resource
    private RemoteAreaFeign remoteAreaFeign;

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

    @Override
    public List<Area> getProvinceArea() {
        R<List<Area>> r = remoteAreaFeign.getProvinceAreaForRPC();
        if (r.getCode() == HttpStatus.SUCCESS) {
            return r.getData();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Area> getAreaByParentId(Long pid) {
        R<List<Area>> r = remoteAreaFeign.getAreaByParentIdForRPC(pid);
        if (r.getCode() == HttpStatus.SUCCESS) {
            return r.getData();
        }
        return new ArrayList<>();
    }

}
