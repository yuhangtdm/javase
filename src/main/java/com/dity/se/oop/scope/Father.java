package com.dity.se.oop.scope;

/**
 * 四种作用域的方法自身都可以调用
 * public
 * protected
 * default
 * private
 *
 * public      >  protected >   default     > private
 * 全都可以        子类可以     同包可以        自身可以
 * @author:yuhang
 * @Date:2019/1/30
 */
public class Father {
    public void  publicMethod(){
        System.out.println("public method");
    }

    protected void protectedMethod(){
        System.out.println("protected method");
    }

    void defaultMehtod(){
        System.out.println("default method");
    }

    private void privateMethod(){
        System.out.println("private method");
    }

    public static void main(String[] args) {
        Father father = new Father();
        father.publicMethod();
        father.protectedMethod();
        father.defaultMehtod();
        father.privateMethod();
    }
}
