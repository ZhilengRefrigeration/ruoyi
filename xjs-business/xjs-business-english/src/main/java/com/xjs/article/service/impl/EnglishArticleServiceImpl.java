package com.xjs.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.xjs.article.domain.EnglishArticle;
import com.xjs.article.mapper.EnglishArticleMapper;
import com.xjs.article.service.EnglishArticleService;
import com.xjs.business.api.RemoteTranslationFeign;
import com.xjs.business.api.domain.TranslationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 英语文章service接口实现
 *
 * @author xiejs
 * @since 2022-03-03
 */
@Service
public class EnglishArticleServiceImpl extends ServiceImpl<EnglishArticleMapper, EnglishArticle> implements EnglishArticleService {


    @Autowired
    private RemoteTranslationFeign remoteTranslationFeign;


    @Override
    public EnglishArticle selectEnglishArticleById(Long id) {
        return super.getById(id);
    }

    @Override
    public IPage<EnglishArticle> selectEnglishArticleList(Page<EnglishArticle> page, EnglishArticle englishArticle) {
        //封装查询条件
        LambdaQueryWrapper<EnglishArticle> wrapper = new LambdaQueryWrapper<>();
        // todo 封装查询条件

        return super.page(page, wrapper);
    }

    @Override
    public boolean insertEnglishArticle(EnglishArticle englishArticle) {
        //封装添加参数 远程调用翻译
        englishArticle = this.translation(englishArticle);

        return super.save(englishArticle);
    }


    @Override
    public boolean updateEnglishArticle(EnglishArticle englishArticle) {
        return super.updateById(englishArticle);
    }

    @Override
    public boolean deleteEnglishArticleByIds(Long[] ids) {
        return super.removeByIds(Arrays.asList(ids));
    }

    @Override
    public boolean deleteEnglishArticleById(Long id) {
        return super.removeById(id);
    }


    /**
     * 翻译中文内容
     *
     * @param englishArticle 文章实体类
     * @return 文章实体类
     */
    public EnglishArticle translation(EnglishArticle englishArticle) {
        // 使用线程安全的Vector
        Vector<Thread> threads = new Vector<Thread>();

        Thread thread1 = new Thread(() -> {
            R<TranslationVo> titleR = remoteTranslationFeign.translation(englishArticle.getTitleChinese());
            if (titleR.getCode() == HttpStatus.SUCCESS) {
                List<Map<String, String>> transResult = titleR.getData().getTransResult();
                StringBuilder sbEnglish = new StringBuilder();
                for (Map<String, String> map : transResult) {
                    sbEnglish.append(map.get("dst"));
                }
                englishArticle.setTitleEnglish(sbEnglish.toString());
            }
        });
        threads.add(thread1);
        thread1.start();

        Thread thread2 = new Thread(() -> {
            R<TranslationVo> titleR = remoteTranslationFeign.translation(englishArticle.getContentChinese());
            if (titleR.getCode() == HttpStatus.SUCCESS) {
                List<Map<String, String>> transResult = titleR.getData().getTransResult();
                StringBuilder sbEnglish = new StringBuilder();
                for (Map<String, String> map : transResult) {
                    sbEnglish.append(map.get("dst"));
                }
                englishArticle.setContentEnglish(sbEnglish.toString());
            }
        });
        threads.add(thread2);
        thread2.start();

        for (Thread thread : threads) {
            // 等待所有线程执行完毕
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return englishArticle;
    }


}
