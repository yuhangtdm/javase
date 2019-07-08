package com.dity.se.designPatterns.adapter;

/**
 * 接口的抽象方法太多
 * 实现类实现接口 必须重写所有抽象方法
 * 加一层抽象类做适配
 * 目标类实现抽象类即可重写想重写的方法
 */
public abstract class Wrapper2 implements Sourceable{
      
    public void method1(){}  
    public void method2(){}  
}  