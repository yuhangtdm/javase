package com.dity.se.oop.statics;

/**
 * @author:yuhang
 * @Date:2019/1/30
 */
public class Father {
    static {
        System.out.println("Father static code");
    }

    public Father(){
        System.out.println("Father constructor code");
    }
}
