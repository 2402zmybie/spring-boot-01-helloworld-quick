package com.hr.springboot.basic;

import java.util.concurrent.Semaphore;

public class ThreadPoolTest {
    //信号量  同时拥有5把锁
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        method();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void method() throws InterruptedException {
        //获取一把锁
        semaphore.acquire();
        System.out.println("线程" + Thread.currentThread().getName() + "进来了");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "出来了");
        //释放一把锁
        semaphore.release();
    }
}
