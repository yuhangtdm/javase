package com.dity.se.thread.train;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock+Condition的实现
 * Lock功能类似synchronized 加同步锁
 * lock可以生成Condition
 * @author:yuhang
 * @Date:2019/1/15
 */
public class PrintABC3 {

    public static void main(String[] args) {
        OneLockPrint lockPrint = new OneLockPrint();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=20;i++){
                    lockPrint.printA(i);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=20;i++){
                    lockPrint.printB(i);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=20;i++){
                    lockPrint.printC(i);
                }
            }
        }).start();
    }

}

class OneLockPrint{
    private Lock  lock =new ReentrantLock();
    private int flag = 1;
    // 只有一个条件
    private Condition lock1 = lock.newCondition();

    public void printA(int i){
        try {
            lock.lock();
            if (flag!=1){
                try {
                    lock1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag=2;
            System.out.print("A");
            lock1.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printB(int i){
        try {
            lock.lock();
            if (flag!=2){
                try {
                    lock1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag=3;
            System.out.print("B");
            lock1.signal();
        }finally {
            lock.unlock();
        }
    }

    public void printC(int i){
        try {
            lock.lock();
            if (flag!=3){
                try {
                    lock1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            flag=1;
            System.out.print("C");
            lock1.signal();
        }finally {
            lock.unlock();
        }
    }
}