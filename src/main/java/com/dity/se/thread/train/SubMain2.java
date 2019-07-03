package com.dity.se.thread.train;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有两个线程，子线程先执行10次，
 * 然后主线程执行5次，然后再切换到子线程执行10，再主线程执行5次……如此往返执行50次。
 * @author:yuhang
 * @Date:2019/1/15
 */
public class SubMain2 {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =1;i<=50;i++){
                    business.print1();
                }
            }
        },"sub").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =1;i<=50;i++){
                    business.print2();
                }
            }
        },"main").start();
    }
}

class Business{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int flag = 1;

    public void print1(){
        try {
            lock.lock();
            while (flag!=1){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            flag = 0;
            condition.signal();
        }finally {
            lock.unlock();
        }

    }
    public void print2(){
        try {
            lock.lock();
            while (flag!=0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
            flag = 1;
            condition.signal();
        }finally {
            lock.unlock();
        }

    }
}
