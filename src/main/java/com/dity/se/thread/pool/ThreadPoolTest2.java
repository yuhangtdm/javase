package com.dity.se.thread.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author:yuhang
 * @Date:2019/1/23
 */
public class ThreadPoolTest2 {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(1);
        Future<Integer> submit = executorService.submit(new MyCallable());

        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

    }
}
