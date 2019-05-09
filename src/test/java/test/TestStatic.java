package test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class TestStatic {
    private static  Thread[] lstThreads = null;
    static {
        long start = System.currentTimeMillis();
        ThreadGroup currentGroup =Thread.currentThread().getThreadGroup();

        while (currentGroup.getParent()!=null){
            // 返回此线程组的父线程组
            currentGroup=currentGroup.getParent();
        }
        //此线程组中活动线程的估计数
        int noThreads = currentGroup.activeCount();

        lstThreads = new Thread[noThreads];
        //把对此线程组中的所有活动子组的引用复制到指定数组中。
        currentGroup.enumerate(lstThreads);
        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start));

    }

    public static Thread[] getThreads() {
        return lstThreads;
    }

    public static void main(String[] args) {
        System.out.println(83/10);
    }
}
