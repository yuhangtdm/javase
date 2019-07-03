package com.dity.se.thread.pool;

import java.util.concurrent.*;

/**
 * @author yuhang   2019/7/3 16:56
 */
public class ThreadPoolStudy {

    public static void main(String[] args) {
//        testFixedThreadPool();
        testSingleThreadPool();
    }

    /**
     * 固定大小的线程池
     * 工作线程数量固定
     * 多余的任务加入到无界队列中 先入先出
     */
    public static void testFixedThreadPool(){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 4; i++){
            Runnable runnable = new Runnable() {
                public void run() {
                    CountDownLatch countDownLatch = new CountDownLatch(1); //计数器，用于阻塞线程
                    System.out.println(Thread.currentThread().getName() + "正在执行");
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(runnable);
        }
    }


    /**
     * 缓冲线程池
     * 可以创建无数的线程执行任务
     * 如果线程池的规模大于当前任务数量 则会回收部分空闲线程 根据空闲时间来回收
     */
    public static void testCachedThreadPool(){
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++){
            Runnable runnable = new Runnable() {
                public void run() {
                    CountDownLatch countDownLatch = new CountDownLatch(1); //计数器，用于阻塞线程
                    System.out.println(Thread.currentThread().getName() + "正在执行");
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(runnable);
        }
    }

    /**
     * 按照添加任务的顺序执行任务 单个工作线程来执行
     */
    public static void testSingleThreadPool(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 4; i++){
            final int index = i;
            Runnable runnable = new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在执行工作任务--- >" + index);
                }
            };
            executor.execute(runnable);
        }
    }

    public static void testScheduledThreadPool(){

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 3; i++){
            final int index = i;
            Runnable runnable = new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "延时1s后，每5s执行一次工作任务--- >" + index);
                }
            };
            executor.scheduleAtFixedRate(runnable,1,5,TimeUnit.SECONDS);
        }

    }

}
