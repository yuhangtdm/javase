package com.dity.se.thread.cooperation;

/**
 * 线程间的通信
 * 模型：生产者消费者
 * 方式一 管程法
 * 有一个缓冲区 类似于队列 生产者往队列里放数据 消费者从队列里拿数据
 * @author: yuhang
 * @date: 2019/1/7
 */
public class CoTest01 {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Thread(new Producer(container),"小凯").start();
        new Thread(new Consumer(container),"小余").start();
    }
}

/**
 * 生产者
 */
class Producer implements Runnable{
    private SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }
    @Override
    public void run() {
        for (int i=1;i<=100;i++){
            Buns bun = new Buns(i);
            container.push(bun);
            System.out.println(Thread.currentThread().getName()+"生产了"+bun);
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable{
    private SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i=1;i<=100;i++){
            Buns pop = container.pop();
            System.out.println(Thread.currentThread().getName()+"获取了"+pop);
        }
    }
}

/**
 * 缓冲区
 */
class SynContainer{
    // 馒头数组
    private Buns[] buns = new Buns[10];
    // 计数器
    private int count = 0 ;
    private boolean flag =false;

    /**
     * 放馒头
     * 什么时候应该等待 就是生产者不能生产了
     * @param bun
     */
    public synchronized  void push(Buns bun){
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buns[count++]=bun;
        this.notifyAll();
        flag=!flag;
    }

    /**
     * 消费者取馒头
     * 就是取不到馒头的时候应该等待
     * @return
     */
    public synchronized Buns pop(){
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Buns bun = buns[--count];
        this.notifyAll();
        flag=!flag;
        return bun;
    }

}
/**
 * 馒头
 */
class Buns{
    private int id;

    @Override
    public String toString() {
        return "我是"+id+"号小馒头";
    }
    public Buns(int id) {
        this.id = id;
    }
}


