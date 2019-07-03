package com.dity.se.generic;

/**
 * @Description:
 * @Author: yuhang
 * @Date: 2019/6/3 15:39
 */
public class Box<T> {
    private T data;

    public Box(){}

    public Box(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /*
        当形参使用泛型时
        则需要在泛型方法中添加 <T>
        返回的对象是泛型对象时 需要在方法返回值上指明
     */
    public static <T> Box<T> success(T data){
        return new Box(data);
    }


}
