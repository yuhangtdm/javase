package com.dity.se.designPatterns.bridge;

public class MyBridge extends Bridge {

    @Override
    public void method(){  
        getSource().method();  
    }

}  