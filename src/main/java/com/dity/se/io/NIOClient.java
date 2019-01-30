package com.dity.se.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author:yuhang
 * @Date:2019/1/30
 */
public class NIOClient  {

    public static void main(String[] args) {
        SocketChannel channel = null;
        try {
            channel = SocketChannel.open(new InetSocketAddress(9999));
            channel.configureBlocking(false);
            Scanner scanner = new Scanner(System.in);
            // 循环的发消息 发一次 接收一次
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (true){
                System.out.print("send msg:");
                String next = scanner.nextLine();
                // 当输出 exit时 中断循环 退出 退出后关闭连接
                if ("exit".equals(next)){
                    break;
                }
                buf.put(next.getBytes());
                buf.flip();
                channel.write(buf);
                buf.clear();
                if (channel.read(buf)==-1){
                    break;// 服务器什么也没发送 可能中断连接 则客户端断开
                }
                buf.flip();
                System.out.println(new String(buf.array()));
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (channel!=null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
