package com.dity.se.collection.list.singleLinkedList;


import com.dity.se.collection.list.List;

/**
 * @author: yuhang
 * @date: 2019/4/27
 */
public class TestSingleLinkedList {

    public static void main(String[] args) {

        List list = new SingleLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(3, 10);
        System.out.println(list);
        System.out.println(list.get(6));
        list.remove(3);
        System.out.println(list);
        list.removeObj(7);
        System.out.println(list);
    }
}
