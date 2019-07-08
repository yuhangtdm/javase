package com.dity.se.designPatterns.proxy;

public class ProxyObj implements Sourceable {
  
    private Source source;  
    public ProxyObj(Source source){
        super();  
        this.source = source;
    }  
    @Override  
    public void method() {  
        before();  
        source.method();  
        atfer();  
    }  
    private void atfer() {  
        System.out.println("after proxy!");  
    }  
    private void before() {  
        System.out.println("before proxy!");  
    }  
}  