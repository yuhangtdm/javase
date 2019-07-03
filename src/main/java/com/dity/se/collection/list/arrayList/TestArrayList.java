package com.dity.se.collection.list.arrayList;


import java.util.List;

/**
 * @author: yuhang
 * @date: 2019/4/14
 */
public class TestArrayList {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(6);
        list.add(5);
        list.add(6);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(6);
        list.add(11);
        list.add(0,0);
        list.remove(7);
        list.removeObj(6);

        System.out.println(list.contains(5));
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list);
        System.out.println(list.get(5));

        List<Integer> list1 = new java.util.ArrayList<>();
    }
}
