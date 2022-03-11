package com.ruoyi.common.log.mq.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 操作日志消息队列创建者-直连型交换机
 *
 * @author ruoyi
 */
@Configuration
public class RabbitLogDirect {

    /**
     * 队列名
     */
    public final static String queueName = "OperaLogDirectQueue";

    /**
     * 交换机名
     */
    public final static String exchangeName = "OperaLogDirectExchange";

    /**
     * 路由键名
     */
    public final static String routingKeyName = "OperaLogDirectRouting";

    //队列 起名：OperaLogQueue
    @Bean
    public Queue operaLogQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // return new Queue(queueName,true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(queueName, true);
    }

    // Direct交换机 起名：OperaLogDirectExchange
    @Bean
    DirectExchange operaLogDirectExchange() {
        //  return new DirectExchange(exchangeName,true,true);
        return new DirectExchange(exchangeName, true, false);
    }

    // 绑定 将队列和交换机绑定, 并设置用于匹配键：OperaLogDirectRouting
    @Bean
    Binding operaLogBindingDirect() {
        return BindingBuilder.bind(operaLogQueue()).to(operaLogDirectExchange()).with(routingKeyName);
    }

}
