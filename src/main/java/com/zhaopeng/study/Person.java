package com.zhaopeng.study;

import java.io.Serializable;

/**
 * Created by zhaopeng on 2017/6/17.
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -5756630803888689062L;
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
