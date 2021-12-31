package com.xjs.oneenglish.mapper;

import com.xjs.oneenglish.domain.ApiEnglish;

import java.util.List;

/**
 * 英语一言Mapper接口
 *
 * @author xjs
 * @date 2021-12-31
 */
public interface ApiEnglishMapper {


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
     * 删除英语一言
     *
     * @param id 英语一言主键
     * @return 结果
     */
    public int deleteApiEnglishById(Long id);

    /**
     * 批量删除英语一言
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteApiEnglishByIds(Long[] ids);
}
