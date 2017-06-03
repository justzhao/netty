package com.zhaopeng.timeserver.codec;


import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.io.*;


public final class ProtoStuffUtils {

    private static <T> Schema<T> getSchema(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        Schema<T> schema = RuntimeSchema.getSchema(clazz);

        return schema;
    }

    public static <T> T deserializer(byte[] data, Class<T> clazz) {
        try {
            T obj = clazz.newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static <T> byte[] serializer(T obj) {
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    public static void decode() {

        try {


            File f = new File("D://1");

            InputStream in = new FileInputStream(f);

            Long filelength = f.length(); // 获取文件长度
            byte[] filecontent = new byte[filelength.intValue()];
            in.read(filecontent);

            WrapObject o = deserializer(filecontent, WrapObject.class);

            System.out.println(o.toString());
        } catch (Exception e) {


        }


    }

    public static void encode() {

        try {
            WrapObject o = new WrapObject();
            o.a = -1;
            o.b = "上海";

            byte data[] = serializer(o);
            File f = new File("D://1");
            OutputStream out = new FileOutputStream(f);
            out.write(data);
        } catch (Exception e) {

        }
    }

    public static void main(String args[]) {
        encode();
          decode();

   /*     int n=-1;
       int res= (n << 1) ^ (n >> 31);

        System.out.println(res);*/
    }

    public static class WrapObject {
        public int a;
        public String b;

        @Override
        public String toString() {
            return "WrapObject{" +
                    "a=" + a +
                    ", b='" + b + '\'' +
                    '}';
        }
    }

}
