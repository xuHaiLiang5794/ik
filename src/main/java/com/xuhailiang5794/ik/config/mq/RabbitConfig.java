package com.xuhailiang5794.ik.config.mq;

import com.xuhailiang5794.ik.support.cons.AppConstant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/6/28
 */
@Configuration
public class RabbitConfig {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Bean
    public Queue queue() {
        if (amqpTemplate instanceof RabbitTemplate) {
            ((RabbitTemplate) amqpTemplate).setChannelTransacted(false);
            ((RabbitTemplate) amqpTemplate).setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
                @Override
                public void confirm(CorrelationData correlationData, boolean b, String s) {
                    if (!b) {
                        System.out.println(b);
                    }
                }
            });
        }
        return new Queue(AppConstant.QUEUE_NAME);
    }
}
