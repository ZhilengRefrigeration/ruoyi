package com.xjs.zol.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.zol.mapper.ZolPhoneMapper;
import com.xjs.zol.pojo.ZolPhone;
import com.xjs.zol.service.ZolPhoneService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 爬虫数据中关村手机service实现
 *
 * @author xiejs
 * @since 2022-04-18
 */
@Service
public class ZolPhoneServiceImpl extends ServiceImpl<ZolPhoneMapper, ZolPhone> implements ZolPhoneService {

    @Override
    public IPage<ZolPhone> selectZolPhoneByPage(Page<ZolPhone> startPageMP, ZolPhone zolPhone) {
        String condition = zolPhone.getCondition();

        LambdaQueryWrapper<ZolPhone> wr = new LambdaQueryWrapper<>();

        //根据时间查询
        boolean b = Objects.nonNull(zolPhone.getCreateTime()) && Objects.nonNull(zolPhone.getEndCreateTime());
        wr.between(b, ZolPhone::getCreateTime, zolPhone.getCreateTime(), zolPhone.getEndCreateTime());

        //通用查询/组合查询
        wr.and(StringUtils.isNotEmpty(condition), obj -> {
            obj.like(ZolPhone::getPhoneName, condition)
                    .or()
                    .like(ZolPhone::getDescription, condition);
        });

        //排序规则
        wr.orderByDesc(ZolPhone::getId);

        return this.page(startPageMP,wr);
    }
}
