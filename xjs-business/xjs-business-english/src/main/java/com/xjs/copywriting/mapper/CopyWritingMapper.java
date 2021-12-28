package com.xjs.copywriting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.copywriting.domain.CopyWriting;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-27
 */
public interface CopyWritingMapper extends BaseMapper<CopyWriting> {
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
