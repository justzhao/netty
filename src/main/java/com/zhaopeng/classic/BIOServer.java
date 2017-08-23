package com.zhaopeng.classic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.zhaopeng.timeserver.protocol.netty.NettyConstant.PORT;

/**
 * Created by zhaopeng on 2017/8/20.
 */
public class BIOServer implements Runnable {

    private static int MAX_INPUT = 1024;
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            while (!Thread.interrupted())
                new Thread(new Handler(ss.accept())).start();

        } catch (IOException ex) { /* ... */ }
    }
    static class Handler implements Runnable {
        final Socket socket;
        Handler(Socket s) {
            socket = s;
        }
        public void run() {
            try {
                byte[] input = new byte[MAX_INPUT];
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            } catch (IOException ex) { /* ... */ }
        }

        private byte[] process(byte[] cmd) {
            /* ... */
            return null;
        }
    }

}
