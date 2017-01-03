package com.zhaopeng.timeserver.netty.delimiter;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhaopeng on 2016/10/30.
 */
public class ChannelFutureListener implements GenericFutureListener {
    private  Object result;

    @Override
    public void operationComplete(Future future) throws Exception {

        if(future.isDone()){
            System.out.println("listener++++++++++++++++=" +future.get());
            System.out.println("is Done");
        }

        if(future.isSuccess()){
            System.out.println("listener++++++++++++++++=" +future.get());
            System.out.println("is success");
        }



        result=future.get();

    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
