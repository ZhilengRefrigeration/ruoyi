package com.xjs.sina.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.sina.mapper.SinaNewsMapper;
import com.xjs.sina.pojo.SinaNews;
import com.xjs.sina.service.SinaNewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 新浪新闻爬虫Service接口实现
 *
 * @author xiejs
 * @since 2022-02-15
 */
@Service
public class SinaNewsServiceImpl extends ServiceImpl<SinaNewsMapper, SinaNews> implements SinaNewsService {
    @Resource
    private SinaNewsMapper sinaNewsMapper;

    @Override
    public int deleteRepeatData() {
        return sinaNewsMapper.deleteRepeatData();
    }

    @Override
    public List<Object> getType() {
        QueryWrapper<SinaNews> wrapper = new QueryWrapper<>();
        wrapper.groupBy("category");
        wrapper.select("category");

        return this.listObjs(wrapper);
    }

    //-------------------------代码生成----------------------------

    /**
     * 查询新浪新闻列表
     *
     * @param sinaNews 新浪新闻
     * @return 新浪新闻
     */
    @Override
    public List<SinaNews> selectSinaNewsList(SinaNews sinaNews) {
        return sinaNewsMapper.selectSinaNewsList(sinaNews);
    }

    /**
     * 批量删除新浪新闻
     *
     * @param ids 需要删除的新浪新闻主键
     * @return 结果
     */
    @Override
    public int deleteSinaNewsByIds(Long[] ids) {
        return sinaNewsMapper.deleteSinaNewsByIds(ids);
    }

    /**
     * 删除新浪新闻信息
     *
     * @param id 新浪新闻主键
     * @return 结果
     */
    @Override
    public int deleteSinaNewsById(Long id) {
        return sinaNewsMapper.deleteSinaNewsById(id);
    }

    @Override
    public Map<Object, Object> getNews() {
        LambdaQueryWrapper<SinaNews> internalWrapper = new LambdaQueryWrapper<>();
        internalWrapper.select(SinaNews::getTitle, SinaNews::getUrl);
        internalWrapper.eq(SinaNews::getCategory, "国内");
        internalWrapper.orderByDesc(SinaNews::getCreateTime);
        internalWrapper.last("limit 5");
        List<SinaNews> internalList = super.list(internalWrapper);

        LambdaQueryWrapper<SinaNews> internationalWrapper = new LambdaQueryWrapper<>();
        internationalWrapper.select(SinaNews::getTitle, SinaNews::getUrl);
        internationalWrapper.eq(SinaNews::getCategory, "国际");
        internationalWrapper.orderByDesc(SinaNews::getCreateTime);
        internationalWrapper.last("limit 5");
        List<SinaNews> internationalList = super.list(internationalWrapper);

        return MapUtil.builder()
                .put("internal", internalList)
                .put("international", internationalList)
                .build();
    }


}
