package com.dity.se.collection.list.singleLinkedList;


import com.dity.se.collection.list.List;

/**
 * 单链表的实现
 * @author: yuhang
 * @date: 2019/4/27
 */
public class SingleLinkedList implements List {

    // 头结点 不封装数据
    private Node head = new Node();
    // 链表的长度 不包含头结点
    private int size = 0;

    @Override
    public void add(Object e) {
       /* Node next = head;
        while (next.getNext()!=null){
            next =next.getNext();
        }
        Node newNode = new Node(e);
        next.setNext(newNode);
        size++;*/
       this.add(size,e);
    }

    @Override
    public void add(int i, Object e) {
        check(i,e);
        Node node = head;
        // 获取第i个元素
        for (int index=0 ; index< i; index++){
            node = node.getNext();
        }
        // 获取第i+1个元素
        Node next = node.getNext();
        Node newNode = new Node(e,next);
        node.setNext(newNode);
        size++;
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head.getNext()==null;
    }



    @Override
    public boolean contains(Object o) {
        Node node = head;
        for (int index=0 ; index< size; index++){
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
        if(isEmpty()){
            return null;
        }
        Node node = head;
        for (int index=0 ; index< i; index++){
            node = node.getNext();
        }
        return node.getNext().getValue();
    }

    @Override
    public void remove(int i) {
        Node node = head;
        Node next = null;
        for (int index=0 ; index< size; index++){
            next = node.getNext();
            if (i == index){
                node.setNext(next.getNext());
                size--;
                break;
            }
            node = next;
        }
    }

    @Override
    public void removeObj(Object e) {
        Node node = head;
        Node next = null;
        for (int index=0 ; index< size; index++){
            next = node.getNext();
            if (get(index).equals(e)){
                node.setNext(next.getNext());
                size--;
                break;
            }
            node = next;
        }
    }

    @Override
    public String toString() {
        if (isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node node = head;
        for (int index=0 ; index< size(); index++){
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

    private void check(int i, Object e) {
        check(i);
        if (e==null){
            throw new NullPointerException("不可添加null");
        }
    }

    private void check(int i) {
        if (i<0 || i>size()){
            throw new IndexOutOfBoundsException("索引越界异常:"+i);
        }
    }
}
