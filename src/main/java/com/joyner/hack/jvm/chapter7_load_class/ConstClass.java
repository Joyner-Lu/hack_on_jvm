package com.joyner.hack.jvm.chapter7_load_class;

public class ConstClass {

    static {
        System.out.println("ConstClass init!");
    }

    public static final int value = 123;

}
