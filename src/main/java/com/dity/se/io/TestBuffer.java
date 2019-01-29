package com.dity.se.io;

import java.nio.ByteBuffer;

/**
 * NIO的缓冲区
 * Buffer下有多个实现类 常用的 是 ByteBuffer
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * ByteBuffer 常用方法有
 * allocate(1024) 定义一个指定大小的缓冲区
 * capacity() 缓冲区的容量
 * limit() 缓冲区的限制
 * position()缓冲区的位置
 * remaining() = limit-position
 * flip() 切换读模式 limit = position position = 0
 * clear() 清空缓冲区 position = 0; limit = capacity  不清空数据 回到原始数据
 * rewind() 重复读 position = 0
 *
 * 直接缓冲区与非直接缓冲区：
 * 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
 * 直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
 *
 * @author:yuhang
 * @Date:2019/1/28
 */
public class TestBuffer {

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        String msg = "abcde";
        // 定义一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        System.out.println("capacity:"+buffer.capacity());
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());

        System.out.println("---------------put----------------");

        // 写入
        buffer.put(msg.getBytes());

        System.out.println("capacity:"+buffer.capacity());
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());

        System.out.println("---------------flip----------------");

        // flip 将 limit = position position=0;
        buffer.flip();

        System.out.println("capacity:"+buffer.capacity());
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());

        System.out.println("--------------get-----------------");

        // 读取
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println("读取结果:"+new String(data));

        System.out.println("capacity:"+buffer.capacity());
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());

        System.out.println("--------------flip+get-----------------");

        buffer.flip();

        // 读取
        byte[] data1 = new byte[buffer.remaining()];
        buffer.get(data1);
        System.out.println("读取结果:"+new String(data1));

        System.out.println("capacity:"+buffer.capacity());
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());

        // 重复读 将position修改为0
        buffer.rewind();

        System.out.println("-----------------rewind----------------");
        System.out.println("capacity:"+buffer.capacity());
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());

        // 清空缓冲区 但是缓冲区的数据还存在
        buffer.clear();
        System.out.println("-----------------clear----------------");
        System.out.println("capacity:"+buffer.capacity());
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());

        System.out.println((char)buffer.get());



    }

    public static void test2(){
        //分配直接缓冲区
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);

        System.out.println(buf.isDirect());
    }
}
