package com.dity.se.designPatterns.proxy;


import java.lang.reflect.Proxy;

public class ProxyTest {
  
    public static void main(String[] args) {  
        staticProxy();
        System.out.println("=====================");
        dynamicProxy();
    }

    /**
     * 静态代理
     * 和装饰者差不多
     */
    public static void  staticProxy(){
        Sourceable source = new ProxyObj(new Source());
        source.method();
    }

    public static void dynamicProxy(){
        Sourceable o = (Sourceable) MyInvocation.getProxy(new Source());
        o.method();
    }
  
}  