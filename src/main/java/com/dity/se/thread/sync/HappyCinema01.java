package com.dity.se.thread.sync;

/**
 * 多线程之同步---影院抢票
 * @author: yuhang
 * @date: 2019/1/5
 */
public class HappyCinema01 {
    public static void main(String[] args) {
        CineMa cineMa = new CineMa(10,"快乐影院");
        new Thread(new Customer(cineMa,5),"小余").start();
        new Thread(new Customer(cineMa,6),"小芳").start();
    }
}

/**
 * 线程主体---客户
 * 客户主要用到影院对象 选的位置
 */
class Customer implements Runnable{
    private CineMa cineMa;
    private int seats;
    public Customer(CineMa cineMa,int seats){
        this.cineMa = cineMa;
        this.seats = seats;
    }

    /**
     * 需要线程的不停执行才会使用while死循环
     */
    @Override
    public void run() {
        boolean b = cineMa.buyTicket(seats);
        if (b){
            System.out.println("出票成功"+Thread.currentThread().getName()+"->位置为:"+seats);
        }else {
            System.out.println("出票失败"+Thread.currentThread().getName()+"->位置不够");
        }
    }
}

/**
 * 影院
 */
class CineMa{
    // 可用的位置
    private int available;
    // 影院名称
    private String name;

    public CineMa(int available, String name) {
        this.available = available;
        this.name = name;
    }

    /**
     * 买票
     * @param seats 选的位置数
     * @return true 买票成功 false 买票失败 票数不足
     */
    public synchronized boolean buyTicket(int seats){
        if (seats>available){
            return false;
        }
        /**
         * 用于模拟网络延迟 如果没有同步 则会出现票为负数的情况
         */
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        available-=seats;
        return true;
    }
}