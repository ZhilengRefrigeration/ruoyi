package com.xjs.aword.service;

import com.xjs.aword.domain.ApiAWord;

import java.util.List;

/**
 * 每日一句service接口
 * @author xiejs
 * @since 2022-01-08
 */
public interface ApiAWordService {



    //------------------代码生成--------------------------------

    /**
     * 查询每日一句
     *
     * @param id 每日一句主键
     * @return 每日一句
     */
    public ApiAWord selectApiAWordById(Long id);

    /**
     * 查询每日一句列表
     *
     * @param apiAWord 每日一句
     * @return 每日一句集合
     */
    public List<ApiAWord> selectApiAWordList(ApiAWord apiAWord);

    /**
     * 批量删除每日一句
     *
     * @param ids 需要删除的每日一句主键集合
     * @return 结果
     */
    public int deleteApiAWordByIds(Long[] ids);

    /**
     * 删除每日一句信息
     *
     * @param id 每日一句主键
     * @return 结果
     */
    public int deleteApiAWordById(Long id);
}
