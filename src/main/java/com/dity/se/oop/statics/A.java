package com.dity.se.oop.statics;

public class A
{
    private static int a = B();
    
    static
    {
        System.out.println("Enter A.static block");
    }

    /**
     * Enter A.B()
       Enter A.static block
     结论 静态资源的加载顺序是严格按照静态资源的定义顺序的
     * @param args
     */
    public static void main(String[] args)
    {
        new A();
    }
    
    public static int B()
    {
        System.out.println("Enter A.B()");
        return 1;
    }
}