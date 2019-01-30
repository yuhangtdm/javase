package com.dity.se.oop.serializables;

import java.io.*;

/**
 * @author:yuhang
 * @Date:2019/1/30
 */
public class TestSerializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

       /* ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file.txt"));
        oos.writeObject(new SerializableObject("str1","str3"));
        oos.close();*/

        /**
         * 序列化对象进文件时 如果没有指定 serialVersionId 时
         * 对象修改了属性后反序列化会报错
         * Exception in thread "main" java.io.InvalidClassException: com.dity.se.oop.serializables.SerializableObject; local class incompatible: stream classdesc serialVersionUID = -8512376211803770396, local class serialVersionUID = -3765533026374107852
         * 当指定了 serialVersionId 反序列化成功
         */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file.txt"));
        SerializableObject obj = (SerializableObject) ois.readObject();
        System.out.println(obj); // SerializableObject{str1='str1', str2='null'}
    }
}
