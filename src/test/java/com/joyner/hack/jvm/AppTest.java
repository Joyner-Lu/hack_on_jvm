package com.joyner.hack.jvm;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.joyner.hack.jvm.AppTest.sleep;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static void sleep() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        while ((end - start) < 5000) {
            end = System.currentTimeMillis();
        }
    }

    public static void main(String[] args) throws  Exception {
        Registry registry=LocateRegistry.createRegistry(1099);


    }

}

class MyThread implements Runnable {
    @Override
    public void run() {
        try {
            sleep();
            System.out.println(Thread.currentThread().getName() + "执行完毕。。。");
        } catch (Exception e) {

        }
    }


}

class SynAddRunalbe implements Runnable {
    int a, b;

    public SynAddRunalbe(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        sleep();
        synchronized (Integer.valueOf(a)) {
            synchronized (Integer.valueOf(b)) {
                System.out.println(a + b);
            }
        }
    }
}