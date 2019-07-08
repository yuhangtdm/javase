package com.dity.se.designPatterns.adapter;

/**
 * @author yuhang   2019/7/5 14:58
 */
public class AdapterTest {

    public static void main(String[] args) {
        classAdapter();
        System.out.println("===================");
        objectAdapter();
    }

    // 类适配器
    public static void classAdapter(){
        // 将source的功能适配到Targetable接口上
        Targetable adapter = new Adapter();

        adapter.method1();
        adapter.method2();
    }

    // 方法适配器
    public static void objectAdapter(){
        Targetable adapter = new Wrapper(new Source());

        adapter.method1();
        adapter.method2();
    }

    // 接口适配器
    public static void interfaceAdapter(){
        Sourceable adapter = new SourceSub1();
        adapter.method1();
    }
}
