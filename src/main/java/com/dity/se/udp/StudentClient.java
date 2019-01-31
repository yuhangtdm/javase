package com.dity.se.udp;

/**
 * @author:yuhang
 * @Date:2019/1/31
 */
public class StudentClient {
    public static void main(String[] args) {
        new Thread(new SendClient(8888,"localhost",9999)).start();
        new Thread(new ReceiveServer(9090,"老师")).start();
    }
}
