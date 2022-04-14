package com.xjs.maillog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.maillog.domain.MailLog;

import java.util.List;

/**
 * 邮件日志Mapper接口
 *
 * @author xiejs
 * @since 2022-04-14
 */
public interface MailLogMapper extends BaseMapper<MailLog> {


    //---------------------------代码生成-----------------------------------

    /**
     * 查询邮件日志
     *
     * @param id 邮件日志主键
     * @return 邮件日志
     */
    public MailLog selectMailLogById(Long id);

    /**
     * 查询邮件日志列表
     *
     * @param mailLog 邮件日志
     * @return 邮件日志集合
     */
    public List<MailLog> selectMailLogList(MailLog mailLog);

    /**
     * 删除邮件日志
     *
     * @param id 邮件日志主键
     * @return 结果
     */
    public int deleteMailLogById(Long id);

    /**
     * 批量删除邮件日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailLogByIds(Long[] ids);
}
