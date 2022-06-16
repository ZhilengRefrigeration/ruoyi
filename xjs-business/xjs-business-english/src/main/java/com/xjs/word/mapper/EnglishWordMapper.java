package com.xjs.word.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.word.domain.EnglishWord;

import java.util.List;

/**
 * 英语单词Mapper接口
 *
 * @author xjs
 * @date 2021-12-29
 */
public interface EnglishWordMapper extends BaseMapper<EnglishWord> {

    //------------------------代码自动生成-----------------------------------

    /**
     * 查询英语单词
     *
     * @param id 英语单词主键
     * @return 英语单词
     */
    EnglishWord selectEnglishWordById(Long id);

    /**
     * 查询英语单词列表
     *
     * @param englishWord 英语单词
     * @return 英语单词集合
     */
    List<EnglishWord> selectEnglishWordList(EnglishWord englishWord);

    /**
     * 删除英语单词
     *
     * @param id 英语单词主键
     * @return 结果
     */
    int deleteEnglishWordById(Long id);

    /**
     * 批量删除英语单词
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteEnglishWordByIds(Long[] ids);

    /**
     * 随机获取5条英语单词数据
     * @return list
     */
    List<EnglishWord> getEnglishWordByRandom();

}
