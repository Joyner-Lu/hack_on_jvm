package com.joyner.hack.jvm.chapter3_gc;

import java.util.concurrent.TimeUnit;

public class TestAllocation {
    private static final int _1MB = 1024*1024;

    /**
     * VM参数:
     *   -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     *   这里指定使用SerialGC(要不然和书上GC不一致)
     */
    public static void testAllocation() throws Exception {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];

        allocation4 = new byte[1 * _1MB];//出现一次Minor GC
    }

    public static void main(String[] args)throws Exception {

        testAllocation();
    }
}
