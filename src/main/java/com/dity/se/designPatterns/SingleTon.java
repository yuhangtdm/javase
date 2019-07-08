package com.dity.se.designPatterns;

/**
 * 最完美的单例模式
 * @author yuhang   2019/7/5 14:22
 */
public class SingleTon {

    private SingleTon(){}

    private static class SingleTonFactory{
        private static SingleTon singleTon = new SingleTon();
    }

    public static SingleTon getInstance(){
        return SingleTonFactory.singleTon;
    }

}
