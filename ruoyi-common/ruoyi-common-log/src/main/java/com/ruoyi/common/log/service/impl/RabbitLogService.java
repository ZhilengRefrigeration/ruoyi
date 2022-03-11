package com.ruoyi.common.log.service.impl;

import com.rabbitmq.client.Channel;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.log.mq.rabbit.RabbitLogDirect;
import com.ruoyi.common.log.service.ILogService;
import com.ruoyi.system.api.RemoteLogService;
import com.ruoyi.system.api.domain.SysOperLog;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * MQ调用日志服务
 *
 * @author ruoyi
 */
@Component
@RabbitListener(queues = RabbitLogDirect.queueName)//监听的队列名称
public class RabbitLogService implements ILogService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 消息创造者
     * @param sysOperLog
     */
    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        // 将消息保存队列
        rabbitTemplate.convertAndSend(RabbitLogDirect.exchangeName, RabbitLogDirect.routingKeyName, sysOperLog);
    }

    /**
     * 消息消费者
     * @param sysOperLog
     * @param channel
     * @param tag
     * @Description 当一个消费者向 RabbitMQ 注册后，会建立起一个 Channel ，
     *              RabbitMQ 会用 basic.deliver 方法向消费者推送消息，
     *              这个方法携带了一个 delivery tag， 它代表了 RabbitMQ 向该 Channel 投递的这条消息的唯一标识 ID，
     *              是一个单调递增的正整数，delivery tag 的范围仅限于 Channel
     */
    @RabbitHandler
    public void process(SysOperLog sysOperLog, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        R<Boolean> result = remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
        try {
            if(result.getCode() == 200) {
                // 第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
                channel.basicAck(tag, true);
            } else {
                // 第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
                channel.basicReject(tag, true);
            }
        } catch (Exception e) {
            channel.basicReject(tag, false);
        }
    }

}
