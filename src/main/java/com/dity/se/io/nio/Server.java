package com.dity.se.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author:yuhang
 * @Date:2019/1/31
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(9999));
            server.configureBlocking(false);
            Selector selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server started");
            while (selector.select()>0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey sk = iterator.next();
                    if (sk.isValid()){
                        if (sk.isAcceptable()){
                            ServerSocketChannel serverChannel = (ServerSocketChannel) sk.channel();
                            SocketChannel client = serverChannel.accept();
                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);
                        }
                        if (sk.isReadable()){
                            SocketChannel client = (SocketChannel) sk.channel();
                            ByteBuffer buff = ByteBuffer.allocate(1024);
                            while (client.read(buff)>0){
                                buff.flip();
                                System.out.println("from:"+client.getRemoteAddress()+":"+new String(buff.array()));
                                buff.clear();
                            }
                            buff = null;
                        }
                    }
                    iterator.remove();
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
