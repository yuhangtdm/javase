package com.dity.se.io;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/**
 * Channel 通道
 *
 * java.nio.channels.Channel 接口：
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 *
 * 获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 		本地 IO：
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 *
 * 		网络IO：
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 *
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法  如:FileChannel.open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 * 通道之间直接进行数据传输 可以不借助缓冲区 但是使用的是 直接缓冲区
 * transferFrom()
 * transferTo()
 *
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 * @author:yuhang
 * @Date:2019/1/28
 */
public class TestChannel {

    /**
     * 利用通道进行文件的传输
     */
    @Test
    public void test1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("pom.xml");
            fos = new FileOutputStream("pom3.xml");
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            // 定义缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while ((inChannel.read(buffer))!=-1){
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inChannel!=null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outChannel!=null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * FileChannel的open方法
     *
     * 使用直接缓冲区完成对文件的复制
     */
    @Test
    public void test2() throws IOException {
        // Paths 用于读取文件
        // StandardOpenOption 对文件的操作模式
        FileChannel inChannel = FileChannel.
                open(Paths.get("pom.xml"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel
                .open(Paths.get("pom4.xml"),StandardOpenOption.CREATE,StandardOpenOption.WRITE);

        MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
        MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE,0,inChannel.size());

        byte[] bytes = new byte[inBuffer.remaining()];
        inBuffer.get(bytes);
        outBuffer.put(bytes);

        inChannel.close();
        outChannel.close();
    }

    /**
     * 通道之间直接进行数据传输
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        FileChannel inChannel = FileChannel.
                open(Paths.get("pom.xml"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel
                .open(Paths.get("pom4.xml"),StandardOpenOption.CREATE,StandardOpenOption.WRITE);

//        outChannel.transferFrom(inChannel,0,inChannel.size());

        inChannel.transferTo(0,inChannel.size(),outChannel);
        inChannel.close();
        outChannel.close();
    }

    /**
     * 分散和聚集
     * 将文件读取到多个缓冲区上
     * 其实就是 Channel的read可以传入一个Buffer数组 write也可以传入一个Buffer数组
     */
    @Test
    public void test4() throws IOException {
        // 随机读取类
        RandomAccessFile raf = new RandomAccessFile("1.txt","rw");

        ByteBuffer buffer1 = ByteBuffer.allocate(3);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);

        ByteBuffer[] buf = new ByteBuffer[]{buffer1,buffer2};
        raf.getChannel().read(buf);

        for (ByteBuffer buffer : buf) {
            buffer.flip();
        }

        System.out.println(new String(buffer1.array(),0,buffer1.remaining()));
        System.out.println(new String(buffer2.array(),0,buffer2.remaining()));

        //4. 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();

        channel2.write(buf);

    }


    /**
     * Charset 得到支持的字符集列表
     */
    @Test
    public void test5(){
        Map<String, Charset> map = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }


}
