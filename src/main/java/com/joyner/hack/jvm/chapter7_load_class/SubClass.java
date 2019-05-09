package com.joyner.hack.jvm.chapter7_load_class;

public class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init!");
    }

}