package com.zhaopeng.timeserver.netty.delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaopeng on 2016/10/30.
 */
public class NettyClient {

    public static final int connectTimeout = 2000;
    String host="127.0.0.1";
    int port=8080;
    Channel channel=null;
    Bootstrap bootstrap=new Bootstrap();
    boolean connected = false;
    public synchronized void connect() {

        if(this.connected){
            return;
        }

       System.out.println("client is connecting to " + this.host + ":" + this.port);
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(new InetSocketAddress(host, port));
            if (future.awaitUninterruptibly(connectTimeout, TimeUnit.MILLISECONDS)) {
                if (future.isSuccess()) {
                    Channel newChannel = future.channel();
                    try {
                        // 关闭旧的连接
                        Channel oldChannel = this.channel;
                        if (oldChannel != null) {
                            System.out.println("close old netty channel " + oldChannel);
                            try {
                                oldChannel.close();
                            } catch (Throwable t) {
                            }
                        }
                    } finally {
                        this.channel = newChannel;
                    }
                    System.out.println("client is connected to " + this.host + ":" + this.port);
                    this.connected = true;
                } else {
                    System.out.println("client is not connected to " + this.host + ":" + this.port);
                }
            } else {
                System.out.println("timeout while connecting to " + this.host + ":" + this.port);
            }
        } catch (Throwable e) {
            System.out.println("error while connecting to " + this.host + ":" + this.port + e);
        }
    }

    public  static  void  main(String args[]){
        NettyClient client=new NettyClient();
        client.connect();
        ChannelFuture future = null;
        try {
            future = client.channel.write("Hi, zp. Welcome to Netty.$_".getBytes());
        } catch (Exception e) {
           e.printStackTrace();
        }


    }
}
