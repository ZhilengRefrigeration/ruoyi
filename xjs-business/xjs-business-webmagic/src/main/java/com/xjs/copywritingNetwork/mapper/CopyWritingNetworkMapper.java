package com.xjs.copywritingNetwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.copywritingNetwork.pojo.CopyWritingNetwork;

/**
 * 文案网mapper
 * @author xiejs
 * @since 2022-02-16
 */
public interface CopyWritingNetworkMapper extends BaseMapper<CopyWritingNetwork> {

    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();
}
