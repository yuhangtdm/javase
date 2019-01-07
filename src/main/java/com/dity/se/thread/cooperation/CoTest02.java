package com.dity.se.thread.cooperation;

/**
 * 生产者与消费者
 * 信号灯法 即用标记区分是生产者执行还是消费者执行
 * @author: yuhang
 * @date: 2019/1/7
 */
public class CoTest02 {
    public static void main(String[] args) {
        Tv tv = new Tv();
        new Thread(new Player(tv),"电视").start();
        new Thread(new Watcher(tv),"观众").start();
    }
}
class Player implements Runnable{
    private Tv tv;

    public Player(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            tv.play(String.valueOf(i));
        }
    }
}

class Watcher implements Runnable{
    private Tv tv;

    public Watcher(Tv tv) {
        this.tv = tv;
    }
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            tv.watch();
        }
    }
}
class Tv{
    private String voice;
    /**
     * True 演员表演
     * false 观众观看
     */
    private boolean flag;

    public synchronized void play(String voice){
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"表演了"+voice);
        this.voice = voice;
        this.notifyAll();
        this.flag=!this.flag;
    }

    public synchronized  void watch(){
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.voice!=null){
            System.out.println(Thread.currentThread().getName()+"观看了"+this.voice);
        }
        this.notifyAll();
        this.flag=!this.flag;
    }


}
