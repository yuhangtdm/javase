package com.dity.se.oop.serializables;

import java.io.Serializable;

/**
 * @author:yuhang
 * @Date:2019/1/30
 */
public class SerializableObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private String str1;
    private transient String str2;
    private static String str3 = "str3";
//    private String str4 ;

    public SerializableObject(String str1,String str2){
        this.str1 = str1;
        this.str2 = str2;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public static String getStr3() {
        return str3;
    }

    public static void setStr3(String str3) {
        SerializableObject.str3 = str3;
    }

    @Override
    public String toString() {
        return str1+" "+str2 +" "+str3;
    }
}
