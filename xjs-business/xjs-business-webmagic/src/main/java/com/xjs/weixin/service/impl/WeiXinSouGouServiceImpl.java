package com.xjs.weixin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.ruoyi.common.core.web.page.PageDomain;
import com.xjs.weixin.mapper.WeiXinSouGouMapper;
import com.xjs.weixin.pojo.WeiXinSouGou;
import com.xjs.weixin.service.WeiXinSouGouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 微信搜狗service实现
 *
 * @author xiejs
 * @since 2022-02-22
 */
@Service
public class WeiXinSouGouServiceImpl extends ServiceImpl<WeiXinSouGouMapper, WeiXinSouGou> implements WeiXinSouGouService {

    @Resource
    private WeiXinSouGouMapper weiXinSouGouMapper;

    @Autowired
    private BeanSearcher beanSearcher;


    @Override
    public SearchResult<WeiXinSouGou> selectWeiXinSouGouList(WeiXinSouGou weiXinSouGou, PageDomain pageDomain) {

        Map<String, Object> params = MapUtils.builder()
                .page(pageDomain.getPageNum()-1, pageDomain.getPageSize())
                .field(WeiXinSouGou::getTitle,weiXinSouGou.getTitle())
                .op("ct")//构建模糊查询   ct: 包含
                .field(WeiXinSouGou::getContent,weiXinSouGou.getContent())
                .op("ct")
                .field(WeiXinSouGou::getSource,weiXinSouGou.getSource())
                .op("ct")
                .field(WeiXinSouGou::getCreateTime,weiXinSouGou.getCreateTime(),weiXinSouGou.getEndCreateTime())
                .op("bt")
                .orderBy(WeiXinSouGou::getCreateTime,pageDomain.getIsAsc())
                .build();

        return beanSearcher.search(WeiXinSouGou.class, params);

    }


    @Override
    public int deleteRepeatData() {
        return weiXinSouGouMapper.deleteRepeatData();
    }


    //------------------------------代码生成--------------------------------

    /**
     * 查询爬虫微信搜狗搜索列表
     *
     * @param weiXinSouGou 爬虫微信搜狗搜索
     * @return 爬虫微信搜狗搜索
     */
    @Override
    public List<WeiXinSouGou> selectWeiXinSouGouList(WeiXinSouGou weiXinSouGou) {
        return weiXinSouGouMapper.selectWeiXinSouGouList(weiXinSouGou);
    }

    /**
     * 批量删除爬虫微信搜狗搜索
     *
     * @param ids 需要删除的爬虫微信搜狗搜索主键
     * @return 结果
     */
    @Override
    public int deleteWeiXinSouGouByIds(Long[] ids) {
        return weiXinSouGouMapper.deleteWeiXinSouGouByIds(ids);
    }

    /**
     * 删除爬虫微信搜狗搜索信息
     *
     * @param id 爬虫微信搜狗搜索主键
     * @return 结果
     */
    @Override
    public int deleteWeiXinSouGouById(Long id) {
        return weiXinSouGouMapper.deleteWeiXinSouGouById(id);
    }
}
