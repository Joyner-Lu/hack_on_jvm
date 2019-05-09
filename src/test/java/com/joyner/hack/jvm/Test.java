package com.joyner.hack.jvm;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) throws  Exception{

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://10.10.15.196:9999/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        ObjectName mbeanName = new ObjectName("test:type=Hello");
        System.out.println(mbeanName.toString());
        MBeanInfo info = mbsc.getMBeanInfo(mbeanName);

        MBeanAttributeInfo[] attrs = info.getAttributes();
        for (MBeanAttributeInfo attr : attrs) {
            System.out.println(attr.getName());
        }

        Object customC = mbsc.getAttribute(mbeanName,"CacheSize");
        System.out.println("my ---:" + customC);
    }

    public Thread[] findAllThread(){
        ThreadGroup currentGroup =Thread.currentThread().getThreadGroup();

        while (currentGroup.getParent()!=null){
            // 返回此线程组的父线程组
            currentGroup=currentGroup.getParent();
        }
        //此线程组中活动线程的估计数
        int noThreads = currentGroup.activeCount();

        Thread[] lstThreads = new Thread[noThreads];
        //把对此线程组中的所有活动子组的引用复制到指定数组中。
        currentGroup.enumerate(lstThreads);

        for (Thread thread : lstThreads) {
            System.out.println("线程数量："+noThreads+" 线程id：" + thread.getId() + " 线程名称：" + thread.getName() + " 线程状态：" + thread.getState());
        }
        return lstThreads;
    }



}
