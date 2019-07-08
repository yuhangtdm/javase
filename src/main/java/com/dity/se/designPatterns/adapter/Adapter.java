package com.dity.se.designPatterns.adapter;

/**
 * 类的适配器模式
 *
 * @author yuhang   2019/7/5 14:36
 */
public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
        System.out.println("this is a targetable method of Adapter");
    }
}
