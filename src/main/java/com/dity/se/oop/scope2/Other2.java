package com.dity.se.oop.scope2;

import com.dity.se.oop.scope.Father;

/**
 * 不同包的类 只能调用public的方法
 * @author:yuhang
 * @Date:2019/1/30
 */
public class Other2 {

    public static void main(String[] args) {
        Father father = new Father();
        father.publicMethod();
        Children children = (Children) new Father();
    }
}
