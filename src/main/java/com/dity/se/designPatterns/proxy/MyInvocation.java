package com.dity.se.designPatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 利用 InvocationHandler和 Proxy
 * 一定要传入目标对象
 * @author yuhang   2019/7/5 15:54
 */
public class MyInvocation implements InvocationHandler {

    private Object source;

    public MyInvocation(Object source) {
        this.source = source;
    }

    /**
     *
     * @param proxy 不能用该对象
     * @param method 目标对象的方法
     * @param args 目标对象的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dynamic proxy before source method");
        Object invoke = method.invoke(source, args);
        System.out.println("dynamic proxy after source method");
        return invoke;
    }

    public static Object getProxy(Object source){
        return Proxy.newProxyInstance(Source.class.getClassLoader(),
                Source.class.getInterfaces(),
                new MyInvocation(source));
    }


}
