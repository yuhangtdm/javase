package com.dity.se.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @author:yuhang
 * @Date:2019/1/31
 */
public class SendClient implements Runnable {
    private DatagramSocket client ;
    public SendClient(int port,String toIp,int toPort){
        try {
            client = new DatagramSocket(port);
            client.connect(new InetSocketAddress(toIp,toPort));
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        // 键盘输入的字节流 转换为字符流
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String line = null;
            try {
                line = reader.readLine();
                if ("bye".equals(line)){
                    break;
                }
                byte[] datas = line.getBytes();
                DatagramPacket packet = new DatagramPacket(datas,0,datas.length);
                client.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        client.close();
    }
}
