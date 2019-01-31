package com.dity.se.udp;

/**
 * @author:yuhang
 * @Date:2019/1/31
 */
public class TeacherClient  {
    public static void main(String[] args) {
        new Thread(new SendClient(8800,"localhost",9090)).start();
        new Thread(new ReceiveServer(9999,"学生")).start();
    }
}
