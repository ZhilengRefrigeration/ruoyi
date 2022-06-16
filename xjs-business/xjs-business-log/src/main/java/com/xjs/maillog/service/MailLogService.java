package com.xjs.maillog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.maillog.domain.MailLog;
import com.xjs.other.LogNumberVo;

import java.util.List;

/**
 * 邮件日志Service接口
 *
 * @author xiejs
 * @since 2022-04-14
 */
public interface MailLogService extends IService<MailLog> {
    /**
     * 获取次数
     * @return LogNumberVo
     */
    LogNumberVo getCount();


    //---------------------------代码生成-----------------------------------

    /**
     * 查询邮件日志
     *
     * @param id 邮件日志主键
     * @return 邮件日志
     */
    MailLog selectMailLogById(Long id);

    /**
     * 查询邮件日志列表
     *
     * @param mailLog 邮件日志
     * @return 邮件日志集合
     */
    List<MailLog> selectMailLogList(MailLog mailLog);

    /**
     * 批量删除邮件日志
     *
     * @param ids 需要删除的邮件日志主键集合
     * @return 结果
     */
    int deleteMailLogByIds(Long[] ids);

    /**
     * 删除邮件日志信息
     *
     * @param id 邮件日志主键
     * @return 结果
     */
    int deleteMailLogById(Long id);


}
