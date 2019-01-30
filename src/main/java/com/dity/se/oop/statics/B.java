package com.dity.se.oop.statics;

/**
 * @author:yuhang
 * @Date:2019/1/30
 */
public class B {

    static {
        c = 3;
        // 编译失败 静态代码块对于定义在它之后的静态变量，可以赋值，但是不能访问。
//        System.out.println("this is a  static"+c);
    }

    private static int c;

    static {
        c = 3;
        // 编译成功 静态代码块对于在代码块之前定义的变量 既可以赋值 也可以访问
        System.out.println("this is a  static"+c);
    }
}
