package com.xjs.copywritingNetwork.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.copywritingNetwork.mapper.CopyWritingNetworkMapper;
import com.xjs.copywritingNetwork.pojo.CopyWritingNetwork;
import com.xjs.copywritingNetwork.service.CopyWritingNetworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @since 2022-02-16
 */
@Service
public class CopyWritingNetworkServiceImpl extends ServiceImpl<CopyWritingNetworkMapper, CopyWritingNetwork> implements CopyWritingNetworkService {

    @Resource
    private CopyWritingNetworkMapper copyWritingNetworkMapper;

    @Override
    public int deleteRepeatData() {
        return copyWritingNetworkMapper.deleteRepeatData();
    }
}
