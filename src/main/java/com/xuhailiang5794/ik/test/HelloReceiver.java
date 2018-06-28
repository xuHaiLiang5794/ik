package com.xuhailiang5794.ik.test;

import com.xuhailiang5794.ik.support.cons.AppConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/6/28 14:30
 */
@Component
@RabbitListener(queues = AppConstant.QUEUE_NAME)
public class HelloReceiver {
    @RabbitHandler
    public void process(String message) {
        try {
            Thread.sleep(100L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            new File("E:\\test" + File.separator + message).createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
