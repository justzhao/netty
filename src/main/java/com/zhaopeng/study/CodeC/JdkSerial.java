package com.zhaopeng.study.CodeC;

import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.zhaopeng.study.Person;

import java.io.*;

/**
 * Created by zhaopeng on 2017/6/17.
 */
public class JdkSerial {


    private static <T> Schema<T> getSchema(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        Schema<T> schema = RuntimeSchema.getSchema(clazz);

        return schema;
    }

    public static <T> T deserializer(byte[] data) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ObjectInputStream in = new ObjectInputStream(bis);
            return (T) in.readObject();
        } catch (Exception e) {

        }
        return null;
    }

    public static <T> byte[] serializer(T obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private static void testdecoderate() {

        Person a = new Person();
        a.a = 1;
        a.b = "上海";
        long now = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            byte data[] = serializer(a);
        }
        long cost1 = System.currentTimeMillis() - now;
        System.out.println("jdk serialize cost " + cost1 + "ms");
        System.out.println("---------------------------------------------------------");
        now = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            byte data[] = ProtoStuffUtils.serializer(a);
        }
        long cost2 = System.currentTimeMillis() - now;
        System.out.println("protobuf serialize cost " + cost2 + "ms");

    }


    public static void main(String args[]) {

        testdecoderate();

        // encode();

        //decode();
    }

    public static void decode() {
        try {

            File f = new File("D://2");
            InputStream in = new FileInputStream(f);
            Long filelength = f.length(); // 获取文件长度
            byte[] filecontent = new byte[filelength.intValue()];
            in.read(filecontent);
            Person o = deserializer(filecontent);
            System.out.println(o.toString());

        } catch (Exception e) {

        }


    }

    public static void encode() {

        try {
            Person a = new Person();
            a.a = 1;
            a.b = "上海";
            byte data[] = serializer(a);
            File f = new File("D://2");
            OutputStream out = new FileOutputStream(f);
            out.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
