package com.dity.se.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author:yuhang
 * @Date:2019/1/31
 */
public class TestMap {
    @Test
    public void test1(){
        Map<String,String> map = new HashMap<>(4);
        map.put("a","a");
        map.put("b","b");
        map.put("c","c");
        map.put("d","d");
        map.put("e","e");
        for (Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();iterator.hasNext();) {
            System.out.println(iterator.next());
        }
    }
}
