package com.xjs.aword.service.impl;

import com.xjs.aword.domain.ApiAWord;
import com.xjs.aword.mapper.ApiAWordMapper;
import com.xjs.aword.service.ApiAWordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 每日一句service实现
 * @author xiejs
 * @since 2022-01-08
 */
@Service
public class ApiAWordServiceImpl implements ApiAWordService {
    @Resource
    private ApiAWordMapper apiAWordMapper;


    //---------------------------代码生成-----------------------------

    /**
     * 查询每日一句
     *
     * @param id 每日一句主键
     * @return 每日一句
     */
    @Override
    public ApiAWord selectApiAWordById(Long id)
    {
        return apiAWordMapper.selectApiAWordById(id);
    }

    /**
     * 查询每日一句列表
     *
     * @param apiAWord 每日一句
     * @return 每日一句
     */
    @Override
    public List<ApiAWord> selectApiAWordList(ApiAWord apiAWord)
    {
        return apiAWordMapper.selectApiAWordList(apiAWord);
    }


    /**
     * 批量删除每日一句
     *
     * @param ids 需要删除的每日一句主键
     * @return 结果
     */
    @Override
    public int deleteApiAWordByIds(Long[] ids)
    {
        return apiAWordMapper.deleteApiAWordByIds(ids);
    }

    /**
     * 删除每日一句信息
     *
     * @param id 每日一句主键
     * @return 结果
     */
    @Override
    public int deleteApiAWordById(Long id)
    {
        return apiAWordMapper.deleteApiAWordById(id);
    }
}
