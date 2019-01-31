package com.dity.se.io;

import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 利用NIO的api 客户端给服务端发送数据
 * 阻塞
 * SocketChannel
 * ServerSocketChannel
 * @author:yuhang
 * @Date:2019/1/28
 */
public class TestBlockingNIO1 {

    @Test
    public void client() throws Exception {
        //1. 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        FileChannel inChannel = FileChannel.open(Paths.get("2.txt"), StandardOpenOption.READ);

        //2. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3. 读取本地文件，并发送到服务端 当读到文件末尾时 返回-1 其实是往buf里写数据
        //
        /**
         * inChannel.read(buf)
         * 其实执行的是  buf.put(var5);
         */
        while(inChannel.read(buf) != -1){
            // 修改buf为读模式
            buf.flip();
            sChannel.write(buf);
            // posion =0  limit = capity
            buf.clear();
        }

        //4. 关闭通道
        inChannel.close();
        sChannel.close();
    }

    @Test
    public void client1() throws Exception {
        //1. 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        FileChannel inChannel = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);

        //2. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3. 读取本地文件，并发送到服务端 当读到文件末尾时 返回-1 其实是往buf里写数据
        //
        /**
         * inChannel.read(buf)
         * 其实执行的是  buf.put(var5);
         */
        while(inChannel.read(buf) != -1){
            // 修改buf为读模式
            buf.flip();
            sChannel.write(buf);
            // posion =0  limit = capity
            buf.clear();
        }

        //4. 关闭通道
        inChannel.close();
        sChannel.close();
    }
    @Test
    public void server() throws IOException {
//        ServerSocket ss = new ServerSocket(9999); 空指针异常
//        ServerSocketChannel channel = ss.getChannel();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("3.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        serverSocketChannel.bind(new InetSocketAddress(9999));
        System.out.println("server started");
        // 阻塞模式时 accept()是阻塞的方法 没接收到客户端的请求 一直阻塞在这 接收到一个客户端 就需要启动一个线程来执行
        // 如果不启动线程的话 就需要一个客户端执行完 另一个客户端才能执行 阻塞模式下 只能连接一个客户端
        SocketChannel socketChannel = serverSocketChannel.accept();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("client connected");
                try {
                    // 睡眠10秒
                    Thread.sleep(10000);
                    ByteBuffer buff = ByteBuffer.allocate(1024);
                    while (socketChannel.read(buff)!=-1){
                        buff.flip();
                        outChannel.write(buff);
                        buff.clear();
                    }
                    socketChannel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
