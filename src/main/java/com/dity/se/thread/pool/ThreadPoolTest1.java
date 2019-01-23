package com.dity.se.thread.pool;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:yuhang
 * @Date:2019/1/22
 */
public class ThreadPoolTest1 {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 只有一个线程
//        ExecutorService executorService = Executors.newFixedThreadPool(5); // 有5个线程
        ExecutorService executorService = Executors.newCachedThreadPool(); // 有10个线程
        for (int i =1;i<=10;i++){
            // 执行线程
            executorService.execute(new MyThread());
//            executorService.submit(new MyThread());
        }
        // 关闭线程池
        executorService.shutdown();
    }

    @Test
    public void test1(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new MyThread());
        /*for (int i =1;i<=10;i++){
            // 执行线程
            executorService.execute(new MyThread());
        }*/
        // 关闭线程池
        executorService.shutdown();
    }
}
