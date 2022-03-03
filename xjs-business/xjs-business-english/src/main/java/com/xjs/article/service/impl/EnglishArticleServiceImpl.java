package com.xjs.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteFileService;
import com.xjs.article.domain.EnglishArticle;
import com.xjs.article.mapper.EnglishArticleMapper;
import com.xjs.article.service.EnglishArticleService;
import com.xjs.business.api.RemoteTranslationFeign;
import com.xjs.business.api.domain.TranslationVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 英语文章service接口实现
 *
 * @author xiejs
 * @since 2022-03-03
 */
@Service
@Log4j2
public class EnglishArticleServiceImpl extends ServiceImpl<EnglishArticleMapper, EnglishArticle> implements EnglishArticleService {

    /**
     * 管理员角色权限标识
     */
    private static final String SUPER_ADMIN = "admin";


    @Autowired
    private RemoteTranslationFeign remoteTranslationFeign;
    @Autowired
    private RemoteFileService remoteFileService;


    @Override
    public EnglishArticle selectEnglishArticleById(Long id) {
        return super.getById(id);
    }

    @Override
    public IPage<EnglishArticle> selectEnglishArticleList(Page<EnglishArticle> page, EnglishArticle englishArticle) {
        //封装查询条件
        LambdaQueryWrapper<EnglishArticle> wrapper = new LambdaQueryWrapper<>();
        //获取当前登录用户
        String username = SecurityUtils.getUsername();
        //admin管理员可以查看所有文章、其他用户只能查看自己的文章
        if (!SUPER_ADMIN.equals(username)) {
            wrapper.eq(EnglishArticle::getCreateUser, username);
        }
        wrapper.orderByDesc(EnglishArticle::getCreateTime);

        boolean b = Objects.nonNull(englishArticle.getCreateTime()) && Objects.nonNull(englishArticle.getEndCreateTime());

        wrapper.between(b, EnglishArticle::getCreateTime, englishArticle.getCreateTime(), englishArticle.getEndCreateTime());

        String condition = englishArticle.getCondition();

        if (StringUtils.isNotEmpty(condition)) {
            wrapper.like(EnglishArticle::getContentChinese, condition).or().like(EnglishArticle::getTitleChinese, condition);
        }

        return super.page(page, wrapper);
    }

    @Override
    public boolean insertEnglishArticle(EnglishArticle englishArticle) {
        //封装添加参数 远程调用翻译
        EnglishArticle newArticle = this.translation(englishArticle);
        String username = SecurityUtils.getUsername();
        newArticle.setCreateUser(username);
        return super.save(newArticle);
    }


    @Override
    public boolean updateEnglishArticle(EnglishArticle englishArticle) {
        //判断是否有权限修改
        boolean b = this.checkOperatePermission(englishArticle);
        if (!b) {
            return false;
        }

        EnglishArticle newArticle = this.translation(englishArticle);

        return super.updateById(newArticle);
    }

    @Override
    public boolean deleteEnglishArticleByIds(Long[] ids) {
        //删除oss中的文件
        for (Long id : ids) {
            EnglishArticle byId = super.getById(id);

            boolean b = this.checkOperatePermission(byId);
            if (!b) {
                return false;
            }

            R r = remoteFileService.removeFile(byId.getPictureUrl());
            if (r.getCode() == HttpStatus.SUCCESS) {
                log.info("图片删除成功");
            }
        }
        return super.removeByIds(Arrays.asList(ids));
    }

    @Override
    public boolean deleteEnglishArticleById(Long id) {
        return super.removeById(id);
    }


    /**
     * 检查是否有操作权限
     *
     * @return 布尔
     */
    private boolean checkOperatePermission(EnglishArticle englishArticle) {
        //判断是否有权限修改
        String username = SecurityUtils.getUsername();
        if (!SUPER_ADMIN.equals(username)) {
            //判断是否当前账号
            if (!username.equals(englishArticle.getCreateUser())) {
                return false;
            }
        }
        return true;
    }


    /**
     * 翻译中文内容
     *
     * @param englishArticle 文章实体类
     * @return 文章实体类
     */
    private EnglishArticle translation(EnglishArticle englishArticle) {
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
