package com.xuhailiang5794.ik.test;

import com.xuhailiang5794.ik.support.cons.AppConstant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/6/28
 */
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String message) {
        amqpTemplate.convertAndSend(AppConstant.QUEUE_NAME, message);
    }
}
