package test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Map;

public class TestStaticCq {
    private static  Thread[] lstThreads = null;
    static {
        long start = System.currentTimeMillis();
        // 根据启动参数，获取weblogic线程池数
        String weblogicPoolSizeStr = System.getProperty("weblogic.threadpool.MaxPoolSize");
        int wPoolSizeFromConf = 0;
        if(weblogicPoolSizeStr != null && !"".equals(weblogicPoolSizeStr.trim())) {
            try {
                wPoolSizeFromConf = Integer.parseInt(weblogicPoolSizeStr);
            } catch (NumberFormatException e) {
                // TODO: handle exception
            }
        }
        int wPoolSizeFromJvm = 0;
        // 获取JVM总线程数，遍历得出weblogic线程池数
        Map<Thread, StackTraceElement[]> stackMap = Thread.currentThread().getAllStackTraces();
        for (Thread thread : stackMap.keySet()) {
            if(thread.getName().indexOf("weblogic.kernel.Default") > -1) {
                wPoolSizeFromJvm++;
            }
        }
        int weblogicPoolSize = 0;
        // 比较两者大小，取最较大值即为weblogic线程数
        if(wPoolSizeFromJvm < wPoolSizeFromConf) {
            weblogicPoolSize = wPoolSizeFromConf;
        } else {
            weblogicPoolSize = wPoolSizeFromJvm;
        }
        // 从配置文件获取健康值和通行量

        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start));

    }

    public static Thread[] getThreads() {
        return lstThreads;
    }

    public static void main(String[] args) {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        for (String arg : arguments) {
            System.out.println(arg);
        }
        String s = System.getProperty("weblogic.threadpool.MaxPoolSize");
        System.out.println(s);
    }
}
