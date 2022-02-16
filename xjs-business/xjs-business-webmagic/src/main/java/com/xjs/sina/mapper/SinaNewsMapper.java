package com.xjs.sina.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.sina.pojo.SinaNews;

import java.util.List;

/**
 * 新浪新闻mapper
 * @author xiejs
 * @since 2022-02-15
 */
public interface SinaNewsMapper extends BaseMapper<SinaNews> {

    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();

    //-------------------------代码生成----------------------------

    /**
     * 查询新浪新闻列表
     *
     * @param sinaNews 新浪新闻
     * @return 新浪新闻集合
     */
    public List<SinaNews> selectSinaNewsList(SinaNews sinaNews);

    /**
     * 删除新浪新闻
     *
     * @param id 新浪新闻主键
     * @return 结果
     */
    public int deleteSinaNewsById(Long id);

    /**
     * 批量删除新浪新闻
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSinaNewsByIds(Long[] ids);
}
