package com.zhaopeng.classic;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static com.zhaopeng.timeserver.protocol.netty.NettyConstant.PORT;

/**
 * Created by zhaopeng on 2017/8/29.
 */
public class Client {

    public static void main(String args[]) throws IOException, InterruptedException {



        Socket socket = new Socket("127.0.0.1",  8080);
        System.out.println("Connecting to server ip:  127.0.0.1, port: " + PORT);
    /*    DataOutputStream dout = new DataOutputStream(socket.getOutputStream());*/

        OutputStream dout=socket.getOutputStream();

        for(int i=0;i<100;i++){

            dout.write(("hello World "+i).getBytes());
            dout.flush();

            System.out.println("");
        }




        socket.close();

    }
}
