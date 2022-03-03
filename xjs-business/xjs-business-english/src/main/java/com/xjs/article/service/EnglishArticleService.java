package com.xjs.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.article.domain.EnglishArticle;

/**
 * 英语文章service接口
 *
 * @author xiejs
 * @since 2022-03-03
 */
public interface EnglishArticleService extends IService<EnglishArticle> {

    /**
     * 查询英语文章
     *
     * @param id 英语文章主键
     * @return 英语文章
     */
    EnglishArticle selectEnglishArticleById(Long id);

    /**
     * 查询英语文章列表
     *
     * @param englishArticle 英语文章
     * @param page 分页参数
     * @return 英语文章集合
     */
    IPage<EnglishArticle> selectEnglishArticleList(Page<EnglishArticle> page, EnglishArticle englishArticle);

    /**
     * 新增英语文章
     *
     * @param englishArticle 英语文章
     * @return 结果
     */
    boolean insertEnglishArticle(EnglishArticle englishArticle);

    /**
     * 修改英语文章
     *
     * @param englishArticle 英语文章
     * @return 结果
     */
    boolean updateEnglishArticle(EnglishArticle englishArticle);

    /**
     * 批量删除英语文章
     *
     * @param ids 需要删除的英语文章主键集合
     * @return 结果
     */
    boolean deleteEnglishArticleByIds(Long[] ids);

    /**
     * 删除英语文章信息
     *
     * @param id 英语文章主键
     * @return 结果
     */
    boolean deleteEnglishArticleById(Long id);

}
