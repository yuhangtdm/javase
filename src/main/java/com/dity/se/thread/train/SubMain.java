package com.dity.se.thread.train;

/**
 * 有两个线程，子线程先执行10次，
 * 然后主线程执行5次，然后再切换到子线程执行10，再主线程执行5次……如此往返执行50次。
 * @author:yuhang
 * @Date:2019/1/15
 */
public class SubMain {
    private int flag = 1;
    public static void main(String[] args) {
        SubMain subMain = new SubMain();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=50;i++){
                    subMain.print2();
                }
            }
        },"sub").start();

        for (int i=1;i<=50;i++){
            subMain.print1();
        }

    }

    public synchronized void print1() {
        while (flag!=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i =1;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
        flag = 1;
        notifyAll();
    }

    public synchronized void print2() {
        while (flag!=1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i =1;i<=10;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
        flag = 0;
        notifyAll();
    }


}
