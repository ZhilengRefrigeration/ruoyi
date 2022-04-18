package com.xjs.zol.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.zol.pojo.ZolPhone;

/**
 * 爬虫数据中关村手机service接口
 * @author xiejs
 * @since 2022-04-18
 */
public interface ZolPhoneService extends IService<ZolPhone> {
    /**
     * 分页查询中关村手机数据
     * @param startPageMP mp封装的前端通用参数
     * @param zolPhone 实体
     * @return page对象
     */
    IPage<ZolPhone> selectZolPhoneByPage(Page<ZolPhone> startPageMP, ZolPhone zolPhone);
}
