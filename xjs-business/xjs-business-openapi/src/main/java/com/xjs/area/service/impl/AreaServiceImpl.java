package com.xjs.area.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.area.domain.Area;
import com.xjs.area.factory.AreaFactory;
import com.xjs.area.mapper.AreaMapper;
import com.xjs.area.service.AreaService;
import com.xjs.weather.domain.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 区域编码service接口实现
 *
 * @author xiejs
 * @since 2022-03-22
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Autowired
    private AreaFactory<List<Area>, RequestBody> areaFactory;
    @Resource
    private AreaMapper areaMapper;

    @Override
    @Transactional
    public void saveArea() {
        //校验数据库是否存在值，存在退出
        long count = super.count();
        if (count > 0) {
            return;
        }

        List<Area> areas = areaFactory.getArea(new RequestBody());

        //第一层遍历  省
        for (Area area_1 : areas) {

            area_1.setParentId(0L);
            super.save(area_1);

            //第二层遍历  市
            for (Area area_2 : area_1.getDistricts()) {
                area_2.setParentId(area_1.getId());
                super.save(area_2);

                //第三层遍历  区/县
                for (Area area_3 : area_2.getDistricts()) {
                    area_3.setParentId(area_2.getId());
                    super.save(area_3);

                    //第四层遍历  乡/镇/街道
                    for (Area area_4 : area_3.getDistricts()) {
                        area_4.setParentId(area_3.getId());
                        super.save(area_4);
                    }
                }
            }
        }
    }

    @Override
    @Transactional
    public void truncateArea() {
        this.areaMapper.truncateArea();
    }

    @Override
    public List<Area> getProvinceArea() {
        return super.list(new LambdaQueryWrapper<Area>().eq(Area::getParentId, 0L));
    }

    @Override
    public List<Area> getAreaByParentId(Long pid) {
        return super.list(new LambdaQueryWrapper<Area>().eq(Area::getParentId, pid));
    }

}
