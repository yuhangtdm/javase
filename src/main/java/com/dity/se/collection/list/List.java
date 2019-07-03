package com.dity.se.collection.list;

/**
 * @author: yuhang
 * @date: 2019/4/14
 */
public interface List {
    int size();
    boolean isEmpty();
    void add(Object e);
    void add(int i, Object e);
    boolean contains(Object o);
    Object get(int i);
    void remove(int i);
    void removeObj(Object e);
}
