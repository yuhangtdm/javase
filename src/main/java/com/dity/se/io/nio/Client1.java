package com.dity.se.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 客户端B
 * @author:yuhang
 * @Date:2019/1/31
 */
public class Client1 {
    public static void main(String[] args) {
        SocketChannel client = null;
        try {
            client = SocketChannel.open(new InetSocketAddress(9999));
            client.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            System.out.println("client started");
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入消息:");
            while (scanner.hasNext()){
                System.out.println("请输入消息:");
                String line = scanner.nextLine();
                buffer.put(line.getBytes());
                buffer.flip();
                client.write(buffer);
                buffer.clear();
                if (line.equals("bye")){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           if (client!=null){
               try {
                   client.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
    }
}
