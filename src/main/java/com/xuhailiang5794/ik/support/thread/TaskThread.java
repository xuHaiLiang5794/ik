package com.xuhailiang5794.ik.support.thread;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/19
 */
public abstract class TaskThread implements Runnable {
    @Override
    public void run() {
        handle();
    }
    protected abstract void handle();
}
