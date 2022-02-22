package com.xjs.weixin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ejlchina.searcher.SearchResult;
import com.ruoyi.common.core.web.page.PageDomain;
import com.xjs.weixin.pojo.WeiXinSouGou;

import java.util.List;

/**
 * 微信搜狗service接口
 * @author xiejs
 * @since 2022-02-22
 */
public interface WeiXinSouGouService extends IService<WeiXinSouGou> {

    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();

    /**
     * BeanSearcher 分页条件查询
     * @param weiXinSouGou 实体类
     * @param pageDomain 分页参数
     * @return SearchResult
     */
    SearchResult<WeiXinSouGou> selectWeiXinSouGouList(WeiXinSouGou weiXinSouGou, PageDomain pageDomain);

    //------------------------代码生成-----------------------------

    /**
     * 查询爬虫微信搜狗搜索列表
     *
     * @param weiXinSouGou 爬虫微信搜狗搜索
     * @return 爬虫微信搜狗搜索集合
     */
    List<WeiXinSouGou> selectWeiXinSouGouList(WeiXinSouGou weiXinSouGou);

    /**
     * 批量删除爬虫微信搜狗搜索
     *
     * @param ids 需要删除的爬虫微信搜狗搜索主键集合
     * @return 结果
     */
    int deleteWeiXinSouGouByIds(Long[] ids);

    /**
     * 删除爬虫微信搜狗搜索信息
     *
     * @param id 爬虫微信搜狗搜索主键
     * @return 结果
     */
    int deleteWeiXinSouGouById(Long id);
}
