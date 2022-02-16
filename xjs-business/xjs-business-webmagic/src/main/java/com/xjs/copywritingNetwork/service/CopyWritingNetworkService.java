package com.xjs.copywritingNetwork.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.copywritingNetwork.pojo.CopyWritingNetwork;

/**
 * 文案网service接口
 * @author xiejs
 * @since 2022-02-16
 */
public interface CopyWritingNetworkService extends IService<CopyWritingNetwork> {
    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();
}
