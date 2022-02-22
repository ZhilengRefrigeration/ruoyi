package com.xjs.weixin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.weixin.pojo.WeiXinSouGou;

import java.util.List;

/**
 * 微信搜狗mapper
 * @author xiejs
 * @since 2022-02-22
 */
public interface WeiXinSouGouMapper extends BaseMapper<WeiXinSouGou> {

    /**
     * 删除重复数据
     * @return int
     */
    int deleteRepeatData();


    //----------------------------代码生成-----------------------------



    /**
     * 查询爬虫微信搜狗搜索列表
     *
     * @param weiXinSouGou 爬虫微信搜狗搜索
     * @return 爬虫微信搜狗搜索集合
     */
    public List<WeiXinSouGou> selectWeiXinSouGouList(WeiXinSouGou weiXinSouGou);

    /**
     * 删除爬虫微信搜狗搜索
     *
     * @param id 爬虫微信搜狗搜索主键
     * @return 结果
     */
    public int deleteWeiXinSouGouById(Long id);

    /**
     * 批量删除爬虫微信搜狗搜索
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWeiXinSouGouByIds(Long[] ids);
}
