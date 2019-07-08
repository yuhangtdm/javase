package com.dity.se.designPatterns.decorator;

/**
 * @author yuhang   2019/7/5 15:46
 */
public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
