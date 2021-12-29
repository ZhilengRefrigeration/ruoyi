package com.xjs.word.service.impl;

import com.xjs.word.domain.EnglishWord;
import com.xjs.word.mapper.EnglishWordMapper;
import com.xjs.word.service.IEnglishWordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 英语单词Service业务层处理
 *
 * @author xjs
 * @date 2021-12-29
 */
@Service
public class EnglishWordServiceImpl implements IEnglishWordService {
    @Resource
    private EnglishWordMapper englishWordMapper;


    //------------------------代码自动生成-----------------------------------

    /**
     * 查询英语单词
     *
     * @param id 英语单词主键
     * @return 英语单词
     */
    @Override
    public EnglishWord selectEnglishWordById(Long id) {
        return englishWordMapper.selectEnglishWordById(id);
    }

    /**
     * 查询英语单词列表
     *
     * @param englishWord 英语单词
     * @return 英语单词
     */
    @Override
    public List<EnglishWord> selectEnglishWordList(EnglishWord englishWord) {
        return englishWordMapper.selectEnglishWordList(englishWord);
    }

    /**
     * 新增英语单词
     *
     * @param englishWord 英语单词
     * @return 结果
     */
    @Override
    public int insertEnglishWord(EnglishWord englishWord) {
        return englishWordMapper.insert(englishWord);
    }

    /**
     * 修改英语单词
     *
     * @param englishWord 英语单词
     * @return 结果
     */
    @Override
    public int updateEnglishWord(EnglishWord englishWord) {
        return englishWordMapper.updateById(englishWord);
    }

    /**
     * 批量删除英语单词
     *
     * @param ids 需要删除的英语单词主键
     * @return 结果
     */
    @Override
    public int deleteEnglishWordByIds(Long[] ids) {
        return englishWordMapper.deleteEnglishWordByIds(ids);
    }

    /**
     * 删除英语单词信息
     *
     * @param id 英语单词主键
     * @return 结果
     */
    @Override
    public int deleteEnglishWordById(Long id) {
        return englishWordMapper.deleteEnglishWordById(id);
    }
}
