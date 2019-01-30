package com.dity.se.oop.finals;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:yuhang
 * @Date:2019/1/30
 */
public class Variable {
    public final int a = 3;
    public final String str = "a";
    public final List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Variable v = new Variable();
        // 编译报错 final修饰的基本类型不能修改值
//        v.a = 5;
        // 编译报错 final修饰的引用类型不能修改地址
//        v.str = "b";
        // 编译报错 final修饰的引用类型不能修改地址
//        v.list = new ArrayList<>();
        // final修饰的引用类型 修改该对象的属性是可以的
        v.list.add("a");
        v.list.add("b");
        System.out.println(v.list);// [a, b]
    }
}
