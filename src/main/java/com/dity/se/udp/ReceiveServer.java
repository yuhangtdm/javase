package com.dity.se.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * @author:yuhang
 * @Date:2019/1/31
 */
public class ReceiveServer implements Runnable {
    private DatagramSocket server;
    private String from;
    public ReceiveServer(int port,String from){
        this.from = from;
        try {
            server = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {

        while (true){
            try {
                byte[] datas = new byte[1024];
                DatagramPacket packet = new DatagramPacket(datas,0,datas.length);
                server.receive(packet);
                String line = new String(packet.getData());
                System.out.println(from +":"+line);
                packet = null;
                datas = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
