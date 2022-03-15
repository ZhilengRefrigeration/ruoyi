package com.xjs.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.utils.PageUtils;
import com.xjs.mall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author xiejs
 * @email 1294405880@qq.com
 * @date 2022-03-15 10:30:19
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

