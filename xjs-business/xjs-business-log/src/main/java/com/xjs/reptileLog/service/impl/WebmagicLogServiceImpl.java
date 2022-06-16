package com.xjs.reptileLog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.other.LogNumberVo;
import com.xjs.reptileLog.domain.WebmagicLog;
import com.xjs.reptileLog.mapper.WebmagicLogMapper;
import com.xjs.reptileLog.service.WebmagicLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.xjs.consts.CommonConst.TODAY_END;
import static com.xjs.consts.CommonConst.TODAY_START;

/**
 * @author xiejs
 * @since 2022-02-17
 */
@Service
public class WebmagicLogServiceImpl extends ServiceImpl<WebmagicLogMapper, WebmagicLog> implements WebmagicLogService {

    @Resource
    private WebmagicLogMapper webmagicLogMapper;

    @Override
    public LogNumberVo getCount() {
        LogNumberVo logNumberVo = new LogNumberVo();

        long total = super.count();
        logNumberVo.setTotal(total);

        LambdaQueryWrapper<WebmagicLog> wrapper = new LambdaQueryWrapper<>();
        String startDate = DateUtil.today() + " "+TODAY_START;
        String endDate = DateUtil.today() + " "+TODAY_END;
        wrapper.between(WebmagicLog::getCreateTime, startDate, endDate);
        long todayCount = super.count(wrapper);
        logNumberVo.setTodayNumber(todayCount);

        return logNumberVo;

    }


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
