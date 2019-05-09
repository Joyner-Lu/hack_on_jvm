package com.joyner.hack.jvm.chapter3_gc;

public class TestPretenureSizeThreshold {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数：
     *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
     * 这里指定使用SerialGC(要不然和书上GC不一致)
     */

    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[6 * _1MB];  //直接分配在老年代中
    }

    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }

}
