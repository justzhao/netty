package com.zhaopeng.timeserver.aio;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by zhaopeng on 2016/10/10.
 */
public class WriteCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {


    private AsynchronousSocketChannel channel;

    public WriteCompletionHandler(AsynchronousSocketChannel channel) {
        if (this.channel == null)
            this.channel = channel;
    }

    @Override
    public void completed(Integer buf, ByteBuffer buffer) {
        // 如果没有发送完成，继续发送
        System.out.println("疯狂输出!");

        if (buffer.hasRemaining())
            channel.write(buffer, buffer, this);

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            channel.close();
        } catch (IOException e) {
            // ingnore on close
        }

    }
}
