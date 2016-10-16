
package com.zhaopeng.timeserver.codec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by zhaopeng on 2016/10/6.
 */
public class TestUserInfo {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	UserInfo info = new UserInfo();
	info.buildUserID(100).buildUserName("Welcome to Netty");
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	ObjectOutputStream os = new ObjectOutputStream(bos);
	os.writeObject(info);
	os.flush();
	os.close();
	byte[] b = bos.toByteArray();
	System.out.println("The jdk serializable length is : " + b.length);
	bos.close();
	System.out.println("-------------------------------------");
	System.out.println("The byte array serializable length is : "
		+ info.codeC().length);

    }

}
