package com.dity.se.io;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 阻塞的使用NIO的api
 * @author:yuhang
 * @Date:2019/1/28
 */
public class TestBlockingNIO2 {

    @Test
    public void client() throws IOException {
        SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress(9999));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("我是客户端".getBytes());
        buffer.flip();
        clientChannel.write(buffer);
        buffer.clear();

        clientChannel.shutdownOutput();

        int len = 0;
        while ( (len = clientChannel.read(buffer))!=-1){
            buffer.flip();
            System.out.println(new String(buffer.array()));
            buffer.clear();
        }

        clientChannel.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel  serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(9999));

        SocketChannel accept = serverChannel.accept();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int len = 0;
        while ((len = accept.read(buffer))!=-1){
            buffer.flip();
            System.out.println(new String(buffer.array(),0,len));
            buffer.clear();
        }

        buffer.put("服务端收到了".getBytes());
        buffer.flip();
        // 写出
        accept.write(buffer);
        buffer.clear();
        accept.close();
        serverChannel.close();
    }
}
