package com.dity.se.oop.scope2;

import com.dity.se.oop.scope.Father;

/**
 * 不同包 子类可以调用父包的 public和protected的方法
 * @author:yuhang
 * @Date:2019/1/30
 */
public class Children extends Father {

    public void m1(){
        protectedMethod();
        publicMethod();
    }

    public static void main(String[] args) {
        Children children = new Children();
        children.m1();
    }
}
