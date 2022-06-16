package com.xjs.word.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.word.domain.EnglishWord;

import java.util.List;

/**
 * 英语单词Service接口
 *
 * @author xjs
 * @date 2021-12-29
 */
public interface IEnglishWordService extends IService<EnglishWord> {

    /**
     * 查询收藏的单词列表
     * @return
     */
    IPage<EnglishWord> getEnglishWordByCollect(Page<EnglishWord> page);

    /**
     * 查询英语单词
     *
     * @param id 英语单词主键
     * @return 英语单词
     */
    public EnglishWord selectEnglishWordToRPC(Long id);

    EnglishWord selectById(Long id);

    /**
     * 查询英语单词列表(mybatis分页)
     *
     * @param englishWord 英语单词
     * @return 英语单词集合
     */
    List<EnglishWord> selectEnglishWordList(EnglishWord englishWord);

    /**
     * 查询英语单词列表(mybatis-plus分页)
     *
     * @param page 分页条件
     * @param englishWord 查询条件
     * @return 英语单词集合
     */
    IPage<EnglishWord> selectEnglishWordList(Page<EnglishWord> page,EnglishWord englishWord);

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
     * 随机获取5条英语单词数据
     * @return list
     */
    List<EnglishWord> getEnglishWordByRandom();


    //------------------------代码自动生成-----------------------------------



    /**
     * 删除英语单词信息
     *
     * @param id 英语单词主键
     * @return 结果
     */
    public int deleteEnglishWordById(Long id);



}
