package com.dity.se.collection.list.linkedList;


import com.dity.se.collection.list.List;

/**
 * 双向链表的实现
 * @author: yuhang
 * @date: 2019/4/23
 */
public class LinkedList implements List {
    // 头结点
    private Node head = new Node();
    // 末尾结点
    private Node last = new Node();
    // 链表长度
    private int size = 0;

    @Override
    public void add(Object e) {
       /* Node node = head;
        while (node.getNext()!=null){
            node = node.getNext();
        }
        Node newNode = new Node(e,node,null);
        node.setNext(newNode);
        size++;*/
       this.add(size,e);
    }

    @Override
    public void add(int i, Object e) {
        check(i,e);
        Node node = head;
        // 通过head找到要替换的元素
        for (int index = 0;index < i; index++){
            node = node.getNext();
        }
        // 获取要替换的元素的下一个元素 有可能为null
        Node next = node.getNext();
        // 创建新元素 指定上一个元素为要替换的元素 下一个元素为替换元素的下一个元素
        Node newNode = new Node(e,node,next);
        // 替换元素设置下一个元素
        node.setNext(newNode);
        if (next!=null){
            // 替换元素下一个元素设置上一个元素
            next.setPrev(newNode);
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }



    @Override
    public boolean contains(Object o) {
        Node node = head;
        for (int index = 0 ; index<size; index++){
            node = node.getNext();
            if (node.getValue().equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(int i) {
        check(i);
        Node node = head;
        for (int index = 0 ; index<size; index++){
            node = node.getNext();
            if (i == index){
                return node.getValue();
            }
        }
        return null;
    }

    @Override
    public void remove(int i) {
        check(i);
        Node node = head;
        for (int index = 0 ; index<size; index++){
            node = node.getNext();
            if (index == i){
                Node prev = node.getPrev();
                Node next = node.getNext();
                prev.setNext(next);
                if (next!=null){
                    next.setPrev(prev);
                }
                size--;
                break;
            }
        }
    }

    @Override
    public void removeObj(Object e) {
        Node node = head;
        for (int index = 0 ; index<size; index++){
            node = node.getNext();
            if (node.getValue().equals(e)){
                Node prev = node.getPrev();
                Node next = node.getNext();
                prev.setNext(next);
                if (next!=null){
                    next.setPrev(prev);
                }
                size--;
            }
        }
    }

    private void check(int i, Object e) {
        check(i);
        if (e==null){
            throw new NullPointerException("不可添加null");
        }
    }

    private void check(int i) {
        if (i<0 || i>size){
            throw new IndexOutOfBoundsException("索引越界异常:"+i);
        }
    }

    @Override
    public String toString() {
        if (isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node node = head;
        for (int index = 0 ; index< size; index++){
            node = node.getNext();
            if (index!=size-1){
                sb.append(node.getValue()).append(",").append(" ");
            }else {
                sb.append(node.getValue());
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
