package com.zhaopeng.timeserver.nio;

/**
 * Created by zhaopeng on 2016/10/8.
 */
public class TimeServer {

    public static void main(String args[]){

        int port=8999;
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();

    }

}
