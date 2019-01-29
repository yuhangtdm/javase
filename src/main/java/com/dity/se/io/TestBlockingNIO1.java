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

        //3. 读取本地文件，并发送到服务端
        while(inChannel.read(buf) != -1){
            buf.flip();
            sChannel.write(buf);
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

        SocketChannel socketChannel = serverSocketChannel.accept();

        ByteBuffer buff = ByteBuffer.allocate(1024);

        while (socketChannel.read(buff)!=-1){
            buff.flip();
            outChannel.write(buff);
            buff.clear();
        }

        socketChannel.close();
        serverSocketChannel.close();
        outChannel.close();


    }

}
