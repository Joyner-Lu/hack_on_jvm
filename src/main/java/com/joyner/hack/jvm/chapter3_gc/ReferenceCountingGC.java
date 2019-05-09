package com.joyner.hack.jvm.chapter3_gc;

public class ReferenceCountingGC {

    public Object instance = null;
    //2*2M
    private byte[] bigsize = new byte[2*1024*1024];

    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }
}