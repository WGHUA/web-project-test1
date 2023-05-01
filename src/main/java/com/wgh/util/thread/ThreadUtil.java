package com.wgh.util.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author wughua
 * @Description ThreadUtil
 * @Date 2023/5/1
 */
@Slf4j
public class ThreadUtil {

    public static ThreadPoolExecutor threadPool;

    static {
        threadPool = new ThreadPoolExecutor(
                // 核心线程数
                10,
                // 最大线程数
                100,
                // 线程存活时间
                60,
                // 线程存活时间单位
                TimeUnit.SECONDS,
                // 阻塞队列
                new ArrayBlockingQueue<>(20),
                // 线程工厂
                new NameThreadFactory(),
                // 拒绝策略
                new MyIgnorePolicy()
        );
        // 预启动所有核心线程
//        threadPool.prestartAllCoreThreads();
    }

    /**
     * 自定义线程工厂
     */
    static class NameThreadFactory implements ThreadFactory {
        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            log.info(t.getName() + " has been created");
            return t;
        }
    }

    /**
     * 自定义拒绝策略
     */
    static class MyIgnorePolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            doLog(r, executor);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            log.info("线程" + r.toString() + " 被拒绝");
            log.info("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    public static void main(String[] args) {

        ThreadPoolExecutor threadPool = ThreadUtil.threadPool;

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Runnable runnable1 = () -> {
                System.out.println("线程执行" + finalI);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
            threadPool.execute(runnable1);
        }
        threadPool.shutdown();
    }

}
