package com.ale.ttt;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/** 
 * @author ĳ��: 
 * @version ����ʱ�䣺2015��8��17�� ����3:04:14 
 * ��˵�� 
 */
public class Connect {
    private static final ThreadLocal<Socket> threadConnect = new ThreadLocal<Socket>(); 
    
    private static final String HOST = "192.168.121.12";

    private static final int PORT = 2561;
    
    private static Socket client;
    
    private static DataOutputStream outStr = null;
    
    private static InputStream inStr = null;
    
    private static Thread tRecv = new Thread(new RecvThread());
    private static Thread tKeep = new Thread(new KeepThread());

    public static void connect() throws UnknownHostException, IOException  {
        client = threadConnect.get();
        if(client == null){
            client = new Socket(HOST, PORT);
            threadConnect.set(client);
            tKeep.start();
            System.out.println("========���ӿ�ʼ��========");
        }
        outStr = new DataOutputStream(client.getOutputStream()); 
        inStr = client.getInputStream();
        String c="@FFFF";
            byte[] buf=c.getBytes();
            outStr.write(buf);
    }
    
    public static void disconnect() {
        try {
            outStr.close();
            inStr.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static class KeepThread implements Runnable {
        public void run() {
            try {
                System.out.println("=====================��ʼ����������==============");
                String c="$FFFF";
                while (true) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("�����������ݰ�");
                    byte[] buf=c.getBytes();
                    outStr.write(buf);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    private static class RecvThread implements Runnable {
        public void run() {
            try {
                System.out.println("==============��ʼ��������===============");
                while (true) {
                    InputStream in = client.getInputStream();
                    System.out.println(in.read());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    public static void main(String[] args) {
        try {
            Connect.connect();
            tRecv.start();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}