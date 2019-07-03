package com.dity.se.generic;

/**
 * @Description:
 * @Author: yuhang
 * @Date: 2019/6/3 15:40
 */
public class GenericTest {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>("hello");
        Box<Integer> integerBox = Box.success(35);
//        integerBox.getData()
        System.out.println(stringBox.getData());
    }

}
