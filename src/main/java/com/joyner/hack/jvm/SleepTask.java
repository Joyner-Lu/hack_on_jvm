package com.joyner.hack.jvm;

public class SleepTask implements Runnable {

    private  int times;

    SleepTask(int times) {
        this.times = times;
    }

    @Override
    public void run() {
        try {
            System.out.println("begin task:" + Thread.currentThread().getName());
            Thread.sleep(times);
            System.out.println("end task:" + Thread.currentThread().getName());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
