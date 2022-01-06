package com.xjs.oneenglish.service.impl;

import com.xjs.oneenglish.domain.ApiEnglish;
import com.xjs.oneenglish.mapper.ApiEnglishMapper;
import com.xjs.oneenglish.service.IApiEnglishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 英语一言Service业务层处理
 *
 * @author xjs
 * @date 2021-12-31
 */
@Service
public class ApiEnglishServiceImpl implements IApiEnglishService {
    @Autowired
    private ApiEnglishMapper apiEnglishMapper;

    //------------------------代码自动生成-----------------------------------

    /**
     * 查询英语一言
     *
     * @param id 英语一言主键
     * @return 英语一言
     */
    @Override
    public ApiEnglish selectApiEnglishById(Long id) {
        return apiEnglishMapper.selectApiEnglishById(id);
    }

    /**
     * 查询英语一言列表
     *
     * @param apiEnglish 英语一言
     * @return 英语一言
     */
    @Override
    public List<ApiEnglish> selectApiEnglishList(ApiEnglish apiEnglish) {
        return apiEnglishMapper.selectApiEnglishList(apiEnglish);
    }

    /**
     * 批量删除英语一言
     *
     * @param ids 需要删除的英语一言主键
     * @return 结果
     */
    @Override
    public int deleteApiEnglishByIds(Long[] ids) {
        return apiEnglishMapper.deleteApiEnglishByIds(ids);
    }

    /**
     * 删除英语一言信息
     *
     * @param id 英语一言主键
     * @return 结果
     */
    @Override
    public int deleteApiEnglishById(Long id) {
        return apiEnglishMapper.deleteApiEnglishById(id);
    }
}
