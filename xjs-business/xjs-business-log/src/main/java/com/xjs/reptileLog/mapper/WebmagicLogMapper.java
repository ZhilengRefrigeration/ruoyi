package com.xjs.reptileLog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.reptileLog.domain.WebmagicLog;

import java.util.List;

/**
 * WebmagicLog mapper
 * @author xiejs
 * @since 2022-02-17
 */
public interface WebmagicLogMapper extends BaseMapper<WebmagicLog> {


    //--------------------代码生成-----------------------

    /**
     * 查询爬虫日志列表
     *
     * @param webmagicLog 爬虫日志
     * @return 爬虫日志集合
     */
    public List<WebmagicLog> selectWebmagicLogList(WebmagicLog webmagicLog);

    /**
     * 删除爬虫日志
     *
     * @param id 爬虫日志主键
     * @return 结果
     */
    public int deleteWebmagicLogById(Long id);

    /**
     * 批量删除爬虫日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWebmagicLogByIds(Long[] ids);
}
