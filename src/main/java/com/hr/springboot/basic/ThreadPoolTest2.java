package com.hr.springboot.basic;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest2 {
    //缓存线程池
    private static Executor executor = Executors.newCachedThreadPool();
    //固定线程个数的线程池
    private static Executor executor1 = Executors.newFixedThreadPool(5);
    //计划任务的线程池
    private static Executor executor2 = Executors.newScheduledThreadPool(5);
    //单个线程
    private static Executor executor3 = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        //自己创建的线程池
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(100);  //100是工作队列的最大值

        ThreadFactory threadFactory = new ThreadFactory(){

            //原子操作 线程安全的int包装类
            AtomicInteger atomicInteger = new AtomicInteger(0);

            @Override
            public Thread newThread(@NotNull Runnable r) {
                //创建一个线程,然后把r赋值给线程
                Thread thread = new Thread(r);
                thread.setName("MyThread="  + atomicInteger.incrementAndGet());
                return thread;
            }
        };
        /**
         * 参数1:核心池个数
         * 参数2: 最大线程池上限个数
         * 参数3: 任务执行完毕之后  等待核心池回收延迟, 如果核心池为0的话, 程序能运行完毕
         * 参数4
         * 参数5: 用于存储任务的工作队列
         * 参数6: 线程工厂,用于创建线程
         */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,10,1, TimeUnit.SECONDS,blockingQueue, threadFactory);


        for (int i = 0; i < 110; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    method();
                }
            });
            //系统线程池
//            executor1.execute(new Runnable() {
//                @Override
//                public void run() {
//                    method();
//                }
//            });
        }
    }

    public static void method() {
        System.out.println("线程" + Thread.currentThread().getName() + "进来了");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "出来了");
    }
}
