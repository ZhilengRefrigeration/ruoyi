package com.xjs.oneenglish.service;

import com.xjs.oneenglish.domain.ApiEnglish;

import java.util.List;

/**
 * 英语一言Service接口
 *
 * @author xjs
 * @date 2021-12-31
 */
public interface IApiEnglishService {


    //------------------------代码自动生成-----------------------------------

    /**
     * 查询英语一言
     *
     * @param id 英语一言主键
     * @return 英语一言
     */
    public ApiEnglish selectApiEnglishById(Long id);

    /**
     * 查询英语一言列表
     *
     * @param apiEnglish 英语一言
     * @return 英语一言集合
     */
    public List<ApiEnglish> selectApiEnglishList(ApiEnglish apiEnglish);

    /**
     * 批量删除英语一言
     *
     * @param ids 需要删除的英语一言主键集合
     * @return 结果
     */
    public int deleteApiEnglishByIds(Long[] ids);

    /**
     * 删除英语一言信息
     *
     * @param id 英语一言主键
     * @return 结果
     */
    public int deleteApiEnglishById(Long id);
}
