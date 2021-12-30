package com.xjs.word.service.impl;

import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.xjs.business.api.RemoteTranDIctFeign;
import com.xjs.business.api.RemoteTranslationFeign;
import com.xjs.business.api.domain.TranslationVo;
import com.xjs.exception.BusinessException;
import com.xjs.utils.ChineseUtil;
import com.xjs.word.domain.EnglishWord;
import com.xjs.word.mapper.EnglishWordMapper;
import com.xjs.word.service.IEnglishWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    @Autowired
    private RemoteTranslationFeign remoteTranslationFeign;
    @Autowired
    private RemoteTranDIctFeign remoteTranDIctFeign;


    /**
     * 查询英语单词、远程调用获取翻译字典
     *
     * @param id 英语单词主键
     * @return 英语单词
     */
    @Override
    public EnglishWord selectEnglishWordById(Long id) {
        //todo 查看单个单词详细信息
        EnglishWord englishWord = englishWordMapper.selectById(id);
        R<TranslationVo> r = remoteTranDIctFeign.tranDict(englishWord.getEnglishWord());
        if (r.getCode() != R.FAIL) {
            if (Objects.isNull(r.getData().getErrorCode())) {
                //指定to为翻译字典转换的内容
                englishWord.setContent(r.getData().getTo());
            }
        }
        //每次调用查看次数+1
        Long count = englishWord.getLookCount() + 1;
        englishWord.setLookCount(count);
        return englishWord;
    }

    /**
     * 新增英语单词
     *
     * @param englishWord 英语单词
     * @return 结果
     */
    @Override
    public int insertEnglishWord(EnglishWord englishWord) {
        //校验前端传入的是否英文或中文
        boolean alpha = ChineseUtil.isAlpha(englishWord.getContent());
        boolean chinese = ChineseUtil.checkNameChese(englishWord.getContent());
        if (!alpha && !chinese) {
            throw new BusinessException("不能包含其他符号！！！");
        }
        //代表用户输入中文
        if (chinese) {
            englishWord.setChineseWord(englishWord.getContent());
            R<TranslationVo> translationResult = remoteTranslationFeign.translation(englishWord.getContent());
            if(translationResult.getCode()!= Constants.FAIL){
                String dst = translationResult.getData().getTransResult().get(0).get("dst");
                englishWord.setEnglishWord(dst);
            }
        }
        if (alpha) {
            englishWord.setEnglishWord(englishWord.getContent());
            R<TranslationVo> translationResult = remoteTranslationFeign.translation(englishWord.getContent());
            if(translationResult.getCode()!= Constants.FAIL){
                String dst = translationResult.getData().getTransResult().get(0).get("dst");
                englishWord.setChineseWord(dst);
            }
        }
        englishWord.setLookCount(0L);
        //如果排序字段没有值默认=0
        Integer integer = Optional.ofNullable(englishWord.getSort()).orElseGet(() -> 0);
        englishWord.setSort(integer);
        return englishWordMapper.insert(englishWord);
    }





    //------------------------代码自动生成-----------------------------------

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
