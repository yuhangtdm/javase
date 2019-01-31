package com.dity.se.io;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author:yuhang
 * @Date:2019/1/29
 */
public class TestNonBlockingNIO {

    public static void main(String[] args) throws IOException {
//        server2();
        client();
    }

    /**
     *
     * @throws IOException
     */
    public static void client() throws IOException {
        SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));

        // 切换为非阻塞模式
        clientChannel.configureBlocking(false);

        ByteBuffer buf = ByteBuffer.allocate(1024);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()){
            buf.put(sc.next().getBytes());
            buf.flip();
            clientChannel.write(buf);
            buf.clear();
            while (clientChannel.read(buf)>0){
                buf.flip();
                System.out.println(new String(buf.array()));
                buf.clear();
            }
        }

        clientChannel.close();
    }


    public static void server1() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("127.0.0.1",9898));
        SocketChannel client = serverChannel.accept();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (client.read(buf)!=-1){
            buf.flip();
            System.out.println("服务端收到:"+new String(buf.array()));
            buf.clear();
        }
        client.close();
        serverChannel.close();
    }

    /**
     * 当有多个 不停的执行 不停的等待 能不能想起 while(true){} 为什么每一次都想不起来？？？？？？？？
     * @throws IOException
     */
    public static void server2() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("127.0.0.1",9898));

        // 切换为非阻塞模式
        serverChannel.configureBlocking(false);

        Selector selector = Selector.open();

        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        // selector.select() 阻塞
        while (selector.select()>0){

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()){

                SelectionKey next = iterator.next();

                if (next.isAcceptable()){
                    // 注册可读
                    SocketChannel client = serverChannel.accept();
                    client.configureBlocking(false);
                    client.register(selector,SelectionKey.OP_READ);
                } else if (next.isReadable()){
                    SocketChannel client = (SocketChannel) next.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    while (client.read(buf)>0){
                        buf.flip();
                        System.out.println("服务端收到:"+new String(buf.array()));
                        buf.clear();
                    }
                    client.register(selector,SelectionKey.OP_WRITE);
                }else if (next.isWritable()){
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    buf.put("服务端已收到".getBytes());
                    buf.flip();
                    channel.write(buf);
                    buf.clear();
                    channel.register(selector,SelectionKey.OP_READ);
                }

                iterator.remove();
            }
        }

        serverChannel.close();
    }
}
