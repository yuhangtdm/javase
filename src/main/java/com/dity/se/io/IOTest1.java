package com.dity.se.io;

import java.io.*;

/**
 * 利用IO流完成文件的拷贝
 * 其中包括 字符流和字节流
 * @author:yuhang
 * @Date:2019/1/28
 */
public class IOTest1 {

    public static void main(String[] args) {
//        copy("pom.xml","pom2.xml");
        copyFile("D:/image/5.jpg","D:/image/6.jpg");
    }

    /**
     * 字符流的拷贝
     * 文本文件的拷贝 可以一行一行读
     * @param srcPath
     * @param destPath
     */
    public static void copy(String srcPath,String destPath){
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(srcPath));
            writer = new BufferedWriter(new FileWriter(destPath));
            String line = null;
            while ((line = reader.readLine())!=null){
                writer.write(line);
                // 开启换行
                writer.newLine();
                writer.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 字节流的拷贝
     * @param srcPath
     * @param destPath
     */
    public static void copyFile(String srcPath,String destPath){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcPath));
            bos = new BufferedOutputStream(new FileOutputStream(destPath));
            int len = 0;
            // 一次读取1024个字节
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf))!=-1){
                bos.write(buf,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
