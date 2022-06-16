package com.xjs.copywriting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.consts.CopyWritingConst;
import com.xjs.copywriting.domain.CopyWriting;
import com.xjs.copywriting.mapper.CopyWritingMapper;
import com.xjs.copywriting.service.CopyWritingService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public int deleteRepeatData() {
        return copyWritingMapper.deleteRepeatData();
    }

    @Override
    @Cacheable(cacheNames = "bussiness:index:yun_hot_word",key = "#root.method.name")
    public List<CopyWriting> NeteaseHotWord() {
        return copyWritingMapper.NeteaseHotWord(CopyWritingConst.WYY);
    }


    //----------------------代码自动生成------------------------------------
    /**
     * 查询文案api，通过api获取文案信息
     *
     * @param id 文案api，通过api获取文案信息主键
     * @return 文案api，通过api获取文案信息
     */
    @Override
    //将该方法的查询结果存放阿紫springboot的默认缓存中   cacheNames：缓存空间唯一名称 key：缓存id
    @Cacheable(cacheNames = "bussiness:copy_writing",key = "#id")
    public CopyWriting selectCopyWritingById(Long id)
    {
        return copyWritingMapper.selectCopyWritingById(id);
    }

    /**
     * 查询文案api，通过api获取文案信息列表
     *
     * @param copyWriting 文案api，通过api获取文案信息
     * @return 文案api，通过api获取文案信息
     */
    @Override
    public List<CopyWriting> selectCopyWritingList(CopyWriting copyWriting)
    {
        return copyWritingMapper.selectCopyWritingList(copyWriting);
    }

    /**
     * 批量删除文案api，通过api获取文案信息
     *
     * @param ids 需要删除的文案api，通过api获取文案信息主键
     * @return 结果
     */
    @Override
    @CacheEvict(cacheNames = "bussiness:copy_writing",key = "#ids")
    public int deleteCopyWritingByIds(Long[] ids)
    {
        return copyWritingMapper.deleteCopyWritingByIds(ids);
    }

    /**
     * 删除文案api，通过api获取文案信息信息
     *
     * @param id 文案api，通过api获取文案信息主键
     * @return 结果
     */
    @Override
    @CacheEvict(cacheNames = "bussiness:copy_writing",key = "#id")
    public int deleteCopyWritingById(Long id)
    {
        return copyWritingMapper.deleteCopyWritingById(id);
    }


}
