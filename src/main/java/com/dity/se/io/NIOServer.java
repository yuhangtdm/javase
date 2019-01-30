package com.dity.se.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * NIO的服务端
 * 实现客户端连上服务端 发消息
 * @author:yuhang
 * @Date:2019/1/30
 */
public class NIOServer implements Runnable {

    private Selector selector;


    public NIOServer(int port){
        init(port);
    }

    private void init(int port) {
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(port));
            serverChannel.configureBlocking(false);
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server started");
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

    }

    public static void main(String[] args) {
        new Thread(new NIOServer(9999)).start();
    }

    @Override
    public void run() {
        // 不断的轮询客户端的连接
        try {
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey sk = iterator.next();
                    if (sk.isValid()) {
                        if (sk.isAcceptable()) {
                            accept(sk);
                        }
                        if (sk.isReadable()) {
                            read(sk);
                        }
                        if (sk.isWritable()) {
                            write(sk);
                        }
                    }
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }


    private void accept(SelectionKey sk) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) sk.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        channel.register(selector,SelectionKey.OP_READ);
    }

    private void read(SelectionKey sk) throws IOException {
        SocketChannel clientChannel = (SocketChannel) sk.channel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int read = clientChannel.read(buf);
        if (read == -1){
            // 客户端什么都没发 则关闭客户端
            sk.channel();
            sk.channel().close();
            return;
        }
        buf.flip();
        System.out.println("from client"+clientChannel.getRemoteAddress()+" :"+new String(buf.array()));
        buf.clear();
        clientChannel.register(selector,SelectionKey.OP_WRITE);
    }

    private void write(SelectionKey sk) throws IOException {
        SocketChannel clientChannel = (SocketChannel) sk.channel();
        clientChannel.configureBlocking(false);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("server receive msg".getBytes());
        buf.flip();
        clientChannel.write(buf);
        buf.clear();
        clientChannel.register(selector,SelectionKey.OP_READ);
    }


}
