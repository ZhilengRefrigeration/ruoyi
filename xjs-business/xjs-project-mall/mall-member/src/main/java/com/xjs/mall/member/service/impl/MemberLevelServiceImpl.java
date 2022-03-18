package com.xjs.mall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.StringUtils;
import com.xjs.mall.member.dao.MemberLevelDao;
import com.xjs.mall.member.entity.MemberLevelEntity;
import com.xjs.mall.member.service.MemberLevelService;
import com.xjs.utils.PageUtils;
import com.xjs.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelDao, MemberLevelEntity> implements MemberLevelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get(Query.KEY_NAME);
        LambdaQueryWrapper<MemberLevelEntity> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(key)) {
            wrapper.like(MemberLevelEntity::getName, key).or().like(MemberLevelEntity::getNote, key);
        }
        IPage<MemberLevelEntity> page = this.page(new Query<MemberLevelEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

}
