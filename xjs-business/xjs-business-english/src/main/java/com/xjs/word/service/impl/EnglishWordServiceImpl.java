package com.xjs.word.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.redis.service.RedisService;
import com.xjs.business.api.RemoteTranDIctFeign;
import com.xjs.business.api.RemoteTranslationFeign;
import com.xjs.business.api.domain.TranslationVo;
import com.xjs.exception.BusinessException;
import com.xjs.utils.ChineseUtils;
import com.xjs.word.domain.EnglishWord;
import com.xjs.word.mapper.EnglishWordMapper;
import com.xjs.word.service.IEnglishWordService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.xjs.consts.EnglishWordConst.COLLECT;
import static com.xjs.consts.RedisConst.TRAN_DICT;
import static com.xjs.consts.RedisConst.TRAN_DICT_EXPIRE;

/**
 * 英语单词Service业务层处理
 *
 * @author xjs
 * @since  2021-12-29
 */
@Service
@Log4j2
public class EnglishWordServiceImpl implements IEnglishWordService {
    @Resource
    private EnglishWordMapper englishWordMapper;
    @Autowired
    private RemoteTranslationFeign remoteTranslationFeign;
    @Autowired
    private RemoteTranDIctFeign remoteTranDIctFeign;
    @Autowired
    private RedisService redisService;


    @Override
    public IPage<EnglishWord> getEnglishWordByCollect(Page<EnglishWord> page) {
        QueryWrapper<EnglishWord> wr = new QueryWrapper<EnglishWord>()
                .eq("is_collect", COLLECT)
                .orderByDesc("top")
                .orderByDesc("create_time");
        return englishWordMapper.selectPage(page,wr);
    }

    /**
     * 查询英语单词、远程调用获取翻译字典
     *
     * @param id 英语单词主键
     * @return 英语单词
     */
    @Override
    public EnglishWord selectEnglishWordToRPC(Long id) {
        EnglishWord englishWord = englishWordMapper.selectById(id);
        Optional.ofNullable(englishWord).orElseThrow(() -> new BusinessException("数据丢失了~~~~"));
        //每次调用查看次数+1
        Long count = englishWord.getLookCount() + 1;
        englishWord.setLookCount(count);
        englishWordMapper.updateById(englishWord);
        //redis中的hsah键
        String hkey = englishWord.getEnglishWord() + ":" + id;
        Object value = redisService.getCacheMapValue(TRAN_DICT, hkey);
        if (Objects.nonNull(value)) {
            return (EnglishWord)value;
        }
        R<TranslationVo> r = remoteTranDIctFeign.tranDict(englishWord.getEnglishWord());
        if (r.getCode() != R.FAIL) {
            if (Objects.isNull(r.getData().getErrorCode())) {
                //指定to为翻译字典转换的内容
                englishWord.setContent(r.getData().getTo());

                //添加缓存到redis并设置1小时有效时间
                Map<String, Object> build = new HashMap<>();
                build.put(hkey, englishWord);
                redisService.setCacheMap(TRAN_DICT, build);
                redisService.expire(TRAN_DICT, TRAN_DICT_EXPIRE, TimeUnit.HOURS);
            }
        }

        return englishWord;
    }

    @Override
    public EnglishWord selectById(Long id) {
        return englishWordMapper.selectEnglishWordById(id);
    }

    /**
     * 新增英语单词
     *
     * @param englishWord 英语单词
     * @return 结果
     */
    @Override
    public int insertEnglishWord(EnglishWord englishWord) {
        //校验数据库是否存在该单词
        List<EnglishWord> englishWordList = englishWordMapper.selectList(new QueryWrapper<EnglishWord>()
                .eq("english_word", englishWord.getContent()));
        List<EnglishWord> chineseWordList = englishWordMapper.selectList(new QueryWrapper<EnglishWord>()
                .eq("chinese_word", englishWord.getContent()));
        if (CollUtil.isNotEmpty(englishWordList)|| CollUtil.isNotEmpty(chineseWordList)) {
            throw new BusinessException("该词汇已存在！！！!");
        }

        //校验前端传入的是否英文或中文
        boolean alpha = ChineseUtils.isAlpha(englishWord.getContent());
        boolean chinese = ChineseUtils.checkNameChese(englishWord.getContent());
        if (!alpha && !chinese) {
            throw new BusinessException("不能包含其他符号！！！");
        }
        //代表用户输入中文
        if (chinese) {
            englishWord.setChineseWord(englishWord.getContent());
            R<TranslationVo> translationResult = remoteTranslationFeign.translation(englishWord.getContent());
            if (translationResult.getCode() != Constants.FAIL) {
                String dst = translationResult.getData().getTransResult().get(0).get("dst");
                englishWord.setEnglishWord(dst);
            }
        }
        if (alpha) {
            englishWord.setEnglishWord(englishWord.getContent());
            R<TranslationVo> translationResult = remoteTranslationFeign.translation(englishWord.getContent());
            if (translationResult.getCode() != Constants.FAIL) {
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

    /**
     * 修改英语单词 (修改需要清除redis)
     *
     * @param englishWord 英语单词
     * @return 结果
     */
    @Override
    public int updateEnglishWord(EnglishWord englishWord) {
        String hkey = englishWord.getEnglishWord() + ":" + englishWord.getId();
        redisService.dHashByKey(TRAN_DICT, hkey);
        return englishWordMapper.updateById(englishWord);
    }

    /**
     * 批量删除英语单词 (清除redis缓存)
     *
     * @param ids 需要删除的英语单词主键
     * @return 结果
     */
    @Override
    public int deleteEnglishWordByIds(Long[] ids) {
        for (Long id : ids) {
            //需要查出对象才能删除缓存，后续看需求添加
        }
        return englishWordMapper.deleteEnglishWordByIds(ids);
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
