package com.dity.se.thread.sync;

/**
 * 多线程之同步---12306
 * @author: yuhang
 * @date: 2019/1/5
 */
public class HappyWeb12306 {
    public static void main(String[] args) {
        Web12306 web12306 = new Web12306(10,"12306");
        new Passenger(web12306,"小余",5).start();
        new Passenger(web12306,"小馒头",7).start();
    }
}

/**
 * 线程主体---客户
 * 客户主要用到影院对象 选的位置
 */
class Passenger extends Thread{
    private int seats;
    public Passenger(Runnable runnable,String name,int seats){
        super(runnable,name);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }
}

/**
 * 影院
 */
class Web12306 implements Runnable{
    // 可用的位置
    private int available;
    // 影院名称
    private String name;

    public Web12306(int available, String name) {
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
        available-=seats;
        return true;
    }

    @Override
    public void run() {
        // 获取当前线程 用于获取选的位置
        Passenger passenger = (Passenger) Thread.currentThread();
        boolean b = this.buyTicket(passenger.getSeats());
        if (b){
            System.out.println("出票成功"+Thread.currentThread().getName()+"->位置:"+passenger.getSeats());
        }else {
            System.out.println("出票失败"+Thread.currentThread().getName()+"->位置不够");
        }


    }
}