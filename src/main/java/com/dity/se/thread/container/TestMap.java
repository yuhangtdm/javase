package com.dity.se.thread.container;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:yuhang
 * @Date:2019/1/16
 */
public class TestMap {
    Map<String,Object> map = new ConcurrentHashMap<>();

    Map<String,Object> hashMap = new HashMap<>();

    @Test
    public void testMap(){
        // put元素时 如果键已存在 则put不进去 返回该键已经存在的值
        Object o = map.putIfAbsent("aa", "ss");
        System.out.println(o);
        Object o1 = map.putIfAbsent("aa", "mm");
        System.out.println(o1);
        System.out.println(map);
        Object put = map.put("aa", "bb");
        System.out.println(put);
        System.out.println(map);
    }

    @Test
    public void testHashMap(){
        Object put = hashMap.put("ss", "a");
        System.out.println(put);
        Object put1 = hashMap.put("ss", "b");
        System.out.println(put1);
        System.out.println(hashMap);


    }
}
