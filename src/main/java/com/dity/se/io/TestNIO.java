package com.dity.se.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author:yuhang
 * @Date:2019/1/29
 */
public class TestNIO {


    /*
        Paths 提供的 get() 方法用来获取 Path 对象：
            Path get(String first, String … more) : 用于将多个字符串串连成路径。
        Path 常用方法：
            boolean endsWith(String path) : 判断是否以 path 路径结束
            boolean startsWith(String path) : 判断是否以 path 路径开始
            boolean isAbsolute() : 判断是否是绝对路径
            Path getFileName() : 返回与调用 Path 对象关联的文件名
            Path getName(int idx) : 返回的指定索引位置 idx 的路径名称
            int getNameCount() : 返回Path 根目录后面元素的数量
            Path getParent() ：返回Path对象包含整个路径，不包含 Path 对象指定的文件路径
            Path getRoot() ：返回调用 Path 对象的根路径
            Path resolve(Path p) :将相对路径解析为绝对路径
            Path toAbsolutePath() : 作为绝对路径返回调用 Path 对象
            String toString() ： 返回调用 Path 对象的字符串表示形式
     */
    @Test
    public void test1(){
        Path paths = Paths.get("D:","/code/aigou-parent");
        System.out.println(paths.startsWith("D:"));
        System.out.println(paths.endsWith("aigou-parent"));
        System.out.println(paths.isAbsolute());
        for (int i = 0; i<paths.getNameCount();i++){
            System.out.println(paths.getName(i));
        }
        System.out.println(paths.getParent());
        System.out.println(paths.getRoot());
        System.out.println(paths.toAbsolutePath());
    }

    /*
		Files常用方法：
			Path copy(Path src, Path dest, CopyOption … how) : 文件的复制
			Path createDirectory(Path path, FileAttribute<?> … attr) : 创建一个目录
			Path createFile(Path path, FileAttribute<?> … arr) : 创建一个文件
			void delete(Path path) : 删除一个文件
			Path move(Path src, Path dest, CopyOption…how) : 将 src 移动到 dest 位置
			long size(Path path) : 返回 path 指定文件的大小
	 */
    @Test
    public void test2() throws IOException {
        Path paths = Paths.get("D:","/code/es.init");
        System.out.println(Files.size(paths));
    }
}
