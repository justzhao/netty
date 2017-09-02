package com.zhaopeng.classic;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

import static com.zhaopeng.timeserver.protocol.netty.NettyConstant.PORT;

/**
 * Created by zhaopeng on 2017/8/20.
 */
public class BIOServer implements Runnable {

    public static void main(String args[]) throws IOException {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            Socket s = null;
            while (!Thread.interrupted()) {
                s = ss.accept();
                new Thread(new Handler(s)).start();
            }

        } catch (IOException ex) { /* ... */ }

        System.in.read();
    }

    private static int MAX_INPUT = 1024;

    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            Socket s = null;
            while (!Thread.interrupted()) {
                s = ss.accept();
                new Thread(new Handler(s)).start();
            }

        } catch (IOException ex) { /* ... */ }
    }

    static class Handler implements Runnable {
        final Socket socket;

        Handler(Socket s) {
            socket = s;
        }

        public void run() {
            try {
                Reader reader = new InputStreamReader(socket.getInputStream());
                process(reader);

            } catch (IOException ex) { /* ... */ }
        }

        private byte[] process( Reader reader) throws IOException {
            char chars[] = new char[64];
            int len;
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((len=reader.read(chars)) != -1) {
                temp = new String(chars, 0, len);
                System.out.println("from client: " + temp);
            }
            return null;
        }
    }

}
