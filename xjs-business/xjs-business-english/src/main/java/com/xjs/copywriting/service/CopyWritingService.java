package com.xjs.copywriting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.copywriting.domain.CopyWriting;

/**
 * @author xiejs
 * @desc  文案服务接口
 * @create 2021-12-28
 */
public interface CopyWritingService extends IService<CopyWriting> {

    /**
     * 获取数据库最新一条数据（根据时间分组）
     * @return CopyWriting
     */
    CopyWriting getOneToNew();

    /**
     * 从数据库随机获取一条数据
     * @return CopyWriting
     */
    CopyWriting getOneToRandom();
}
