package com.zhaopeng.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by zhaopeng on 2017/8/16.
 */
public class Handler implements Runnable {

    final SocketChannel socketChannel;
    final SelectionKey selectionKey;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING = 1;
    int state = READING;
    String clientName = "";

    Handler(Selector selector, SocketChannel c) throws IOException {
        socketChannel = c;
        c.configureBlocking(false);
        selectionKey = socketChannel.register(selector, 0);
        selectionKey.attach(this);
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void read() throws IOException {
        socketChannel.read(input);
        if (inputIsComplete()) {
            process();
            state = SENDING;
            // Normally also do first write now
            selectionKey.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException {
        socketChannel.write(output);
        if (outputIsComplete()) selectionKey.cancel();
    }

    boolean inputIsComplete() {
        return true;
    }

    boolean outputIsComplete() { /* ... */
        return true;
    }

    void process() { /* ... */

    }

}