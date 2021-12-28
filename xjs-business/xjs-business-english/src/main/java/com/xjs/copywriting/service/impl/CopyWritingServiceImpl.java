package com.xjs.copywriting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.mapper.CopyWritingMapper;
import com.xjs.copywriting.service.CopyWritingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-28
 */
@Service
public class CopyWritingServiceImpl extends ServiceImpl<CopyWritingMapper, CopyWriting> implements CopyWritingService{
    @Resource
    private CopyWritingMapper copyWritingMapper;

    @Override
    public CopyWriting getOneToNew() {
        return copyWritingMapper.getOneToNew();
    }

    @Override
    public CopyWriting getOneToRandom() {
        return copyWritingMapper.getOneToRandom();
    }
}
