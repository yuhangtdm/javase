package com.dity.se.collection.list.arrayList;


import com.dity.se.collection.list.List;

import java.util.Arrays;

/**
 * 数组的实现
 * @author: yuhang
 * @date: 2019/4/14
 */
public class ArrayList implements List {
    // 数组
    private Object[] elementData;
    // 容器数量
    private int size;

    public ArrayList(){
        elementData = new Object[10];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(Object e){
        // 扩容
//        if (size == elementData.length){
//            int len = elementData.length*2;
//            elementData = Arrays.copyOf(elementData,len);
//        }
//        elementData[size++] = e;
        this.add(size,e);
    }

    @Override
    public void add(int i, Object e) {
        checkIndex(i);
        // 扩容
        if (size == elementData.length){
            int len = elementData.length*2;
            elementData = Arrays.copyOf(elementData,len);
        }
        // 将i位置后面的元素全部后移
        for (int j = size ; j > i ; j--){
            elementData[j] = elementData[j-1];
        }
        elementData[i] = e;
        size++;
    }

    @Override
    public boolean contains(Object o) {
        if (o==null){
            return false;
        }
        return getIndex(o)>0;
    }

    @Override
    public Object get(int i) {
        checkIndex(i);
        return elementData[i];
    }

    @Override
    public void remove(int i) {
        checkIndex(i);
        for (int j = i;j < size;j++){
            elementData[j] = elementData [j+1];
        }
        size--;
    }

    @Override
    public void removeObj(Object e) {
        int i = -1 ;
        while ( (i = this.getIndex(e)) > 0){
            remove(i);
        }
    }
    private void checkIndex(int i){
        if (i <0 || i>size){
            throw  new IndexOutOfBoundsException("数组越界异常:"+i);
        }
    }

    private int getIndex(Object o){
        for(int i = 0;i<size;i++){
            if (o.equals(elementData[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (size == 0){
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        for (int i= 0; i<size; i++){
            if (i == size-1){
                builder.append(elementData[i]);
            }else {
                builder.append(elementData[i]).append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
