package com.zhaopeng.timeserver.codec;


import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;


public final class ProtoStuffUtils {


    private ProtoStuffUtils() {
    }


    public static byte[] serialize(WrapObject obj) {

        if (obj == null) {
            return null;
        }
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        WrapObject o = new WrapObject();

        Schema<WrapObject> schema = RuntimeSchema.createFrom(WrapObject.class);

        // 序列化 user 类
        byte[] bytes = ProtostuffIOUtil.toByteArray(o, schema, buffer);

        return bytes;

    }


    @SuppressWarnings("unchecked")
    public static WrapObject deserialize(byte[] data) {


        if (data == null) {
            return null;
        }
        Schema<WrapObject> schema = RuntimeSchema.createFrom(WrapObject.class);
        WrapObject o = new WrapObject();
        ProtostuffIOUtil.mergeFrom(data, o, schema);

        return o;
    }


    public static void decode(){

    }

    public static void  encode(){

    }

    public static void main(String args[]){

    }

    public static class WrapObject {

        public int a;
        public String b;


    }


}
