package com.joyner.hack.jvm;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.util.NoSuchElementException;
import java.util.concurrent.*;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {
        int oneMinute = 1000*60;
                SleepTask task1 = new SleepTask(oneMinute);
        SleepTask task2 = new SleepTask(oneMinute);
        SleepTask task3 = new SleepTask(oneMinute);
        SleepTask task4 = new SleepTask(oneMinute);
        SleepTask task5 = new SleepTask(oneMinute);

        ThreadPoolExecutor t = new ThreadPoolExecutor(3, 10, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(3));
        t.submit(task1);
        sleep(1);
        System.out.println("after task1,queue_element:" + checkQueue(t) + ",active_theads:" + t.getActiveCount());

        t.submit(task2);
        sleep(1);
        System.out.println("after task2,queue_element:" + checkQueue(t) + ",active_theads:" + t.getActiveCount());

        t.submit(task3);
        sleep(1);
        System.out.println("after task3,queue_element:" + checkQueue(t) + ",active_theads:" + t.getActiveCount());

        t.submit(task4);
        sleep(1);
        System.out.println("after task4,queue_element:" + checkQueue(t) + ",active_theads:" + t.getActiveCount());

        Future f = t.submit(task5);
        sleep(1);
        System.out.println("after task5,queue_element:" + checkQueue(t) + ",active_theads:" + t.getActiveCount());


        int i = 1;
        while (i == 1 ) {
            sleep(1);
            System.out.println("处理任务的线程数:" +t.getActiveCount() + ",当前线程池的线程数" + t.getPoolSize());
        }


        t.shutdown();

    }

    /**
     * 休息，单位是秒
     * @param l
     */
    public static void sleep(long l) {
        try {
            Thread.sleep(l*1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static String checkQueue(ThreadPoolExecutor threadPoolExecutor) {
        String result = "";
        try {
            result = threadPoolExecutor.getQueue().element().toString();
        } catch (NoSuchElementException ex) {
            result = "empty";
        }

        return result;
    }
}
