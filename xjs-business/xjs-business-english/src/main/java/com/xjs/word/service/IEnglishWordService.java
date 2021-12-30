package com.xjs.word.service;

import com.xjs.word.domain.EnglishWord;

import java.util.List;

/**
 * 英语单词Service接口
 *
 * @author xjs
 * @date 2021-12-29
 */
public interface IEnglishWordService {

    /**
     * 查询收藏的单词列表
     * @return
     */
    List<EnglishWord> getEnglishWordByCollect();

    /**
     * 查询英语单词
     *
     * @param id 英语单词主键
     * @return 英语单词
     */
    public EnglishWord selectEnglishWordToRPC(Long id);

    EnglishWord selectById(Long id);


    //------------------------代码自动生成-----------------------------------

    /**
     * 查询英语单词列表
     *
     * @param englishWord 英语单词
     * @return 英语单词集合
     */
    public List<EnglishWord> selectEnglishWordList(EnglishWord englishWord);

    /**
     * 新增英语单词
     *
     * @param englishWord 英语单词
     * @return 结果
     */
    public int insertEnglishWord(EnglishWord englishWord);

    /**
     * 修改英语单词
     *
     * @param englishWord 英语单词
     * @return 结果
     */
    public int updateEnglishWord(EnglishWord englishWord);

    /**
     * 批量删除英语单词
     *
     * @param ids 需要删除的英语单词主键集合
     * @return 结果
     */
    public int deleteEnglishWordByIds(Long[] ids);

    /**
     * 删除英语单词信息
     *
     * @param id 英语单词主键
     * @return 结果
     */
    public int deleteEnglishWordById(Long id);
}
