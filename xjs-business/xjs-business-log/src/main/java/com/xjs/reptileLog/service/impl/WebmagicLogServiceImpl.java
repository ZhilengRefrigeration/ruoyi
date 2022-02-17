package com.xjs.reptileLog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.reptileLog.domain.WebmagicLog;
import com.xjs.reptileLog.mapper.WebmagicLogMapper;
import com.xjs.reptileLog.service.WebmagicLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiejs
 * @since 2022-02-17
 */
@Service
public class WebmagicLogServiceImpl extends ServiceImpl<WebmagicLogMapper, WebmagicLog> implements WebmagicLogService {

    @Resource
    private WebmagicLogMapper webmagicLogMapper;


    //------------------------代码生成-----------------------------

    /**
     * 查询爬虫日志列表
     *
     * @param webmagicLog 爬虫日志
     * @return 爬虫日志
     */
    @Override
    public List<WebmagicLog> selectWebmagicLogList(WebmagicLog webmagicLog) {
        return webmagicLogMapper.selectWebmagicLogList(webmagicLog);
    }

    /**
     * 批量删除爬虫日志
     *
     * @param ids 需要删除的爬虫日志主键
     * @return 结果
     */
    @Override
    public int deleteWebmagicLogByIds(Long[] ids) {
        return webmagicLogMapper.deleteWebmagicLogByIds(ids);
    }

    /**
     * 删除爬虫日志信息
     *
     * @param id 爬虫日志主键
     * @return 结果
     */
    @Override
    public int deleteWebmagicLogById(Long id) {
        return webmagicLogMapper.deleteWebmagicLogById(id);
    }

}
