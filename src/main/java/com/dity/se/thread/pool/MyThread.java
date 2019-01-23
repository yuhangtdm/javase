package com.dity.se.thread.pool;

/**
 * @author:yuhang
 * @Date:2019/1/22
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"正在执行");
    }
}
