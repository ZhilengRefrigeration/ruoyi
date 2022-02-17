package com.xjs.reptileLog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.reptileLog.domain.WebmagicLog;

import java.util.List;

/**
 * 爬虫日志 Service接口
 * @author xiejs
 * @since 2022-02-17
 */
public interface WebmagicLogService extends IService<WebmagicLog> {

    //------------------------代码生成-------------------------

    /**
     * 查询爬虫日志列表
     *
     * @param webmagicLog 爬虫日志
     * @return 爬虫日志集合
     */
    public List<WebmagicLog> selectWebmagicLogList(WebmagicLog webmagicLog);

    /**
     * 批量删除爬虫日志
     *
     * @param ids 需要删除的爬虫日志主键集合
     * @return 结果
     */
    public int deleteWebmagicLogByIds(Long[] ids);

    /**
     * 删除爬虫日志信息
     *
     * @param id 爬虫日志主键
     * @return 结果
     */
    public int deleteWebmagicLogById(Long id);

}
