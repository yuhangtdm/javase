package com.dity.se.designPatterns.adapter;

/**
 * @author yuhang   2019/7/5 15:18
 */
public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source){
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is a Targetable method2 of Wrapper");

    }
}
