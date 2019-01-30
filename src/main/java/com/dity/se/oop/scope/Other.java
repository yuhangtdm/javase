package com.dity.se.oop.scope;

/**
 * 同包下不能调用其他类私有的方法 另外三种是可以调用的
 * @author:yuhang
 * @Date:2019/1/30
 */
public class Other {
    public static void main(String[] args) {
        Father father = new Father();
        father.publicMethod();
        father.protectedMethod();
        father.defaultMehtod();
    }
}
