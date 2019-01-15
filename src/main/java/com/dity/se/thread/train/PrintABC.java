package com.dity.se.thread.train;

/**
 * ABC三个线程依次输出ABCABCABC...... 如此循环20次
 * 同步+ wait/notify实现 指定输出次数
 * @author:yuhang
 * @Date:2019/1/15
 */
public class PrintABC {

    public static void main(String[] args) {
        Print p = new Print();
        A a = new A(p);
        B b = new B(p);
        C c = new C(p);
        new Thread(a,"A").start();
        new Thread(b,"B").start();
        new Thread(c,"C").start();
    }

}

class A implements Runnable{
    private Print p;
    public A(Print p){
        this.p=p;
    }
    @Override
    public void run() {
        for (int i = 1; i<=20;i++){
            p.printA();
        }
    }
}

class B implements Runnable{
    private Print p;
    public B(Print p){
        this.p=p;
    }
    @Override
    public void run() {
        for (int i = 1; i<=20;i++){
            p.printB();
        }
    }
}

class C implements Runnable{
    private Print p;
    public C(Print p){
        this.p=p;
    }
    @Override
    public void run() {
        for (int i = 1; i<=20;i++){
            p.printC();
        }
    }
}

class Print{
    private  int flag = 1;
    public synchronized void printA(){
        // 该处不能用if  if 可能会导致死锁 程序中断不了
        while (flag!=1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("A");
        flag = 2;
        notifyAll();
    }

    public synchronized void printB(){
        while (flag!=2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("B");
        flag = 3;
        notifyAll();
    }

    public synchronized void printC(){
        while (flag!=3){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.print("C");
        flag = 1;
        notifyAll();
    }
}



