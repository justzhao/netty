/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.zhaopeng.timeserver.netty.delimiter;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by zhaopeng on 2016/10/15.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

    private Object result;

    private int counter;

    static final String ECHO_REQ = "Hi, zp. Welcome to Netty.$_";

    /**
     * Creates a client-side handler.
     */
    public EchoClientHandler() {
    }


    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void channelActive(ChannelHandlerContext ctx) {
	// ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.buffer(ECHO_REQ
	// .getBytes().length);
	// buf.writeBytes(ECHO_REQ.getBytes());
	for (int i = 0; i < 10; i++) {
	    ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
	}
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
       // super.channelInactive(ctx);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg)
	    throws Exception {

        ChannelFuture future = ctx.channel().close();

        System.out.println(Thread.currentThread().getName()+"　"+ future+"  "+counter);

        future.addListener(new ChannelFutureListener());
        result=msg;


	System.out.println("This is " + ++counter + " times receive server : ["
		+ result + "]");
    }


    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	cause.printStackTrace();
	ctx.close();
    }
}
