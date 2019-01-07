package com.dity.se.thread;

/**
 * 死锁测试
 * 共享资源 书 和画
 * 两个人获得共享资源后 同步的嵌套容易出现死锁
 * @author: yuhang
 * @date: 2019/1/7
 */
public class DeadLockTest {
    public static void main(String[] args) {
        Book book  = new Book();
        Painting painting = new Painting();
        new Thread(new Person(false,book,painting),"小余").start();
        new Thread(new Person(true,book,painting),"小馒头").start();
    }
}

/**
 * 人的线程
 */
class Person implements Runnable{

    private boolean flag = false;
    private Book book;
    private Painting painting;

    public Person(boolean flag,Book book,Painting painting) {
        this.flag = flag;
        this.book = book;
        this.painting = painting;
    }



    @Override
    public void run() {
        if (flag){
            synchronized (book){
                System.out.println(Thread.currentThread().getName()+"拥有书,想要画");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (painting){
                    System.out.println(Thread.currentThread().getName()+"获得画");
                }
            }

           /*
            避免死锁的方式 就是不要同步嵌套
            synchronized (painting){
                System.out.println(Thread.currentThread().getName()+"获得画");
            }*/
        }else {
            synchronized (painting){
                System.out.println(Thread.currentThread().getName()+"拥有画,想要书");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (book){
                    System.out.println(Thread.currentThread().getName()+"获得书");
                }
            }
           /* synchronized (book){
                System.out.println(Thread.currentThread().getName()+"获得书");
            }*/
        }
    }
}

/**
 * 书
 */
class Book{

}

/**
 *画
 */
class Painting{

}
