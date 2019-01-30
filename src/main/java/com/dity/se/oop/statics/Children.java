package com.dity.se.oop.statics;

/**
 * @author:yuhang
 * @Date:2019/1/30
 */
public class Children extends Father {
    static {
        System.out.println("children static code");
    }

    public Children(){
        System.out.println("children constructor code ");
    }

    /**
     * 静态代码块是严格按照 父类代码块 子类代码块的顺序加载的 且只加载一次
     * Father static code
       children static code
       Father constructor code
       children constructor code
       Father constructor code
       children constructor code
     */
    public static void main(String[] args) {
        Children children = new Children();
        Children children2 = new Children();
    }
}
