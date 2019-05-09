package com.joyner.hack.jvm.chapter8_stack_frame;

public class SlotTest {

    public static void main(String[] args){
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }


}
