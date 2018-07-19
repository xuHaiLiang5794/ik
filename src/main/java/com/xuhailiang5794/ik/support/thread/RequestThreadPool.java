package com.xuhailiang5794.ik.support.thread;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <pre>
 * 自定义一个线程池
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/19
 */
@Component
public class RequestThreadPool {
    @Value("${maximumPoolSize}")
    private int maximumPoolSize;

    ThreadPoolExecutor executor;

    public synchronized void execute(TaskThread thread) {
        if (executor == null) {
            initPool();
        }
        executor.execute(thread);
    }

    public BlockingQueue<Runnable> getQueue() {
        return executor.getQueue();
    }

    public long getCompletedTaskCount() {
        return executor.getCompletedTaskCount();
    }

    public int getPoolSize() {
        return executor.getPoolSize();
    }

    public void shutdown() {
        executor.shutdown();
    }

    private void initPool() {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(maximumPoolSize);
    }

    @PreDestroy
    public void PreDestroy() {
        executor.shutdown();
    }
}
