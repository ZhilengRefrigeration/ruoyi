package com.xjs.zol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.zol.mapper.ZolPhoneMapper;
import com.xjs.zol.pojo.ZolPhone;
import com.xjs.zol.service.ZolPhoneService;
import org.springframework.stereotype.Service;

/**
 * 爬虫数据中关村手机service实现
 * @author xiejs
 * @since 2022-04-18
 */
@Service
public class ZolPhoneServiceImpl extends ServiceImpl<ZolPhoneMapper, ZolPhone> implements ZolPhoneService {
}
