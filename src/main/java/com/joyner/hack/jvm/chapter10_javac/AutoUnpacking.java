package com.joyner.hack.jvm.chapter10_javac;

/**
 * https://blog.csdn.net/woshiwu/article/details/6637310
 */
public class AutoUnpacking {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);//tue -128~127从缓存取
        System.out.println(e == f);//false
        System.out.println(c == (a + b));//true 有计算会做自动拆箱，比较值的大小
        System.out.println(c.equals(a + b));//true
        System.out.println(g == (a + b));//true 有计算会做自动拆箱，比较值的大小
        System.out.println(g.equals(a + b));//false。 equals不会进行类型转换。
    }
}
