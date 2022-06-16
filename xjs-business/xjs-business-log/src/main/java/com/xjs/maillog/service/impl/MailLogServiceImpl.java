package com.xjs.maillog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.maillog.domain.MailLog;
import com.xjs.maillog.mapper.MailLogMapper;
import com.xjs.maillog.service.MailLogService;
import com.xjs.other.LogNumberVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.xjs.consts.CommonConst.TODAY_END;
import static com.xjs.consts.CommonConst.TODAY_START;

/**
 * 邮件日志Service业务层处理
 *
 * @author xiejs
 * @since 2022-04-14
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper,MailLog> implements MailLogService {
    @Resource
    private MailLogMapper mailLogMapper;


    @Override
    public LogNumberVo getCount() {
        LogNumberVo logNumberVo = new LogNumberVo();

        long total = super.count();
        logNumberVo.setTotal(total);

        LambdaQueryWrapper<MailLog> wrapper = new LambdaQueryWrapper<>();
        String startDate = DateUtil.today() + " "+TODAY_START;
        String endDate = DateUtil.today() + " "+TODAY_END;
        wrapper.between(MailLog::getCreateTime, startDate, endDate);
        long todayCount = super.count(wrapper);
        logNumberVo.setTodayNumber(todayCount);

        return logNumberVo;

    }

    //---------------------------代码生成-----------------------------------


    /**
     * 查询邮件日志
     *
     * @param id 邮件日志主键
     * @return 邮件日志
     */
    @Override
    public MailLog selectMailLogById(Long id) {
        return mailLogMapper.selectMailLogById(id);
    }

    /**
     * 查询邮件日志列表
     *
     * @param mailLog 邮件日志
     * @return 邮件日志
     */
    @Override
    public List<MailLog> selectMailLogList(MailLog mailLog) {
        return mailLogMapper.selectMailLogList(mailLog);
    }

    /**
     * 批量删除邮件日志
     *
     * @param ids 需要删除的邮件日志主键
     * @return 结果
     */
    @Override
    public int deleteMailLogByIds(Long[] ids) {
        return mailLogMapper.deleteMailLogByIds(ids);
    }

    /**
     * 删除邮件日志信息
     *
     * @param id 邮件日志主键
     * @return 结果
     */
    @Override
    public int deleteMailLogById(Long id) {
        return mailLogMapper.deleteMailLogById(id);
    }
}
