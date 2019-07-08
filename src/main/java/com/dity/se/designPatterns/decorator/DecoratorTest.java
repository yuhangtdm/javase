package com.dity.se.designPatterns.decorator;

/**
 * 装饰者设计模式
 * 装饰类和目标类实现同一个接口
 * 装饰类引入目标类
 * 执行目标方法前后加装饰的方法
 */
public class DecoratorTest {
  
    public static void main(String[] args) {  
        Sourceable source = new Source();  
        Sourceable obj = new Decorator(source);  
        obj.method();  
    }  
}  