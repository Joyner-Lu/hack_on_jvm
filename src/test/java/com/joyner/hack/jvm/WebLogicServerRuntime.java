package com.joyner.hack.jvm;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

public class WebLogicServerRuntime {

    private static MBeanServerConnection connection;
    private static JMXConnector connector;
    private static final ObjectName service, ThreadPoolRuntimeservice;

    // Initializing the object name for DomainRuntimeServiceMBean
// so it can be used throughout the class.
    static {
        try {
            service = new ObjectName("com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean");
            ThreadPoolRuntimeservice = new ObjectName("com.bea:Name=ThreadPoolRuntime,ServerRunTime=AdminServer,Type=weblogic.management.mbeanservers.runtime.ThreadPoolRuntimeMBean");

        } catch (MalformedObjectNameException e) {
            throw new AssertionError(e.getMessage());
        }
    }

    /*
     * Initialize connection to the Domain Runtime MBean Server
     */
    public static void initConnection(String hostname, String portString,
                                      String username, String password) throws IOException,
            MalformedURLException {
        String protocol = "t3";
        Integer portInteger = Integer.valueOf(portString);
        int port = portInteger.intValue();
        String jndiroot = "/jndi/";
        String mserver = "weblogic.management.mbeanservers.domainruntime";
        JMXServiceURL serviceURL = new JMXServiceURL(protocol, hostname,
                port, jndiroot + mserver);
        Hashtable h = new Hashtable();
        h.put(Context.SECURITY_PRINCIPAL, username);
        h.put(Context.SECURITY_CREDENTIALS, password);
        h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
                "weblogic.management.remote");
        connector = JMXConnectorFactory.connect(serviceURL, h);
        connection = connector.getMBeanServerConnection();
    }

    /*
     * Print an array of ServerRuntimeMBeans.
     * This MBean is the root of the runtime MBean hierarchy, and
     * each server in the domain hosts its own instance.
     */
    public static ObjectName[] getServerRuntimes() throws Exception {
        return (ObjectName[]) connection.getAttribute(service,
                "ServerRuntimes");
    }

    public static ObjectName[] getTotalThread() throws Exception {
        return (ObjectName[]) connection.getAttribute(ThreadPoolRuntimeservice,
                "ExecuteThreadTotalCount");
    }

    public int printTotalThread() throws Exception {

        ObjectName[] runtimeService = getServerRuntimes();
        int ExecuteThreadTotalCount = 0;

        int length = (int) runtimeService.length;
        for (int i = 0; i < length; i++) {
            String name = (String) connection.getAttribute(runtimeService[i], "Name");
            ObjectName threadRT = (ObjectName) connection.getAttribute(runtimeService[i], "ThreadPoolRuntime");
            ExecuteThreadTotalCount = (Integer) connection.getAttribute(threadRT, "ExecuteThreadTotalCount");
            System.out.println("n……………….<" + name + " :.. ThreadPoolRuntime>…………….");
            System.out.println("CompletedRequestCount : " + connection.getAttribute(threadRT, "CompletedRequestCount"));
            System.out.println("ExecuteThreadTotalCount : " + connection.getAttribute(threadRT, "ExecuteThreadTotalCount"));
            System.out.println("ExecuteThreadIdleCount : " + connection.getAttribute(threadRT, "ExecuteThreadIdleCount"));
            System.out.println("HealthState : " + connection.getAttribute(threadRT, "HealthState"));
            System.out.println("HoggingThreadCount : " + connection.getAttribute(threadRT, "HoggingThreadCount"));
            System.out.println("PendingUserRequestCount : " + connection.getAttribute(threadRT, "PendingUserRequestCount"));
            System.out.println("QueueLength : " + connection.getAttribute(threadRT, "QueueLength"));
            System.out.println("SharedCapacityForWorkManagers: " + connection.getAttribute(threadRT, "SharedCapacityForWorkManagers"));
            System.out.println("StandbyThreadCount : " + connection.getAttribute(threadRT, "StandbyThreadCount"));
            System.out.println("Suspended : " + connection.getAttribute(threadRT, "Suspended"));
            System.out.println("Throughput : " + connection.getAttribute(threadRT, "Throughput"));
            System.out.println("………………………………………………………………n");
        }
        return (ExecuteThreadTotalCount);

    }


    public int getJvmRuntime() throws Exception {
        ObjectName[] serverRT = getServerRuntimes();
        int length = (int) serverRT.length;

        int heapused = 0;
        for (int i = 0; i < length; i++) {
            String name = (String) connection.getAttribute(serverRT[i], "Name");
            ObjectName jvmRT = (ObjectName) connection.getAttribute(serverRT[i], "JVMRuntime");
            heapused = 100 - (Integer) connection.getAttribute(jvmRT, "HeapFreePercent");

            System.out.println("n……………..<" + name + " : .JVMRuntime>…………….");
            System.out.println("HeapFreeCurrent :" + connection.getAttribute(jvmRT, "HeapFreeCurrent"));
            System.out.println("HeapFreePercent :" + connection.getAttribute(jvmRT, "HeapFreePercent"));
            System.out.println("HeapSizeCurrent :" + connection.getAttribute(jvmRT, "HeapSizeCurrent"));
            System.out.println("HeapSizeMax :" + connection.getAttribute(jvmRT, "HeapSizeMax"));
            System.out.println("JavaVendor :" + connection.getAttribute(jvmRT, "JavaVendor"));
            System.out.println("JavaVersion :" + connection.getAttribute(jvmRT, "JavaVersion"));
            System.out.println("Uptime :" + connection.getAttribute(jvmRT, "Uptime"));
            System.out.println("………………………………………………………………n");
        }
        return heapused;
    }

    public int getJvmTotalHeap() throws Exception {
        ObjectName[] serverRT = getServerRuntimes();
        int length = (int) serverRT.length;

        int HeapFreePercent = 0;
        for (int i = 0; i < length; i++) {
            String name = (String) connection.getAttribute(serverRT[i], "Name");
            ObjectName jvmRT = (ObjectName) connection.getAttribute(serverRT[i], "JVMRuntime");
            HeapFreePercent = (Integer) connection.getAttribute(jvmRT, "HeapFreePercent");

            System.out.println("n……………..<" + name + " : .JVMRuntime>…………….");
            System.out.println("HeapFreeCurrent :" + connection.getAttribute(jvmRT, "HeapFreeCurrent"));
            System.out.println("HeapFreePercent :" + connection.getAttribute(jvmRT, "HeapFreePercent"));
            System.out.println("HeapSizeCurrent :" + connection.getAttribute(jvmRT, "HeapSizeCurrent"));
            System.out.println("HeapSizeMax :" + connection.getAttribute(jvmRT, "HeapSizeMax"));
            System.out.println("JavaVendor :" + connection.getAttribute(jvmRT, "JavaVendor"));
            System.out.println("JavaVersion :" + connection.getAttribute(jvmRT, "JavaVersion"));
            System.out.println("Uptime :" + connection.getAttribute(jvmRT, "Uptime"));
            System.out.println("………………………………………………………………n");
        }
        return HeapFreePercent;
    }

    /*
     *
     * Iterate through ServerRuntimeMBeans and get the name and state
     */
    public void printNameAndState() throws Exception {
        ObjectName[] serverRT = getServerRuntimes();
        System.out.println("got server runtimes");
        int length = (int) serverRT.length;
        for (int i = 0; i < length; i++) {
            String name = (String) connection.getAttribute(serverRT[i],
                    "Name");
            String state = (String) connection.getAttribute(serverRT[i],
                    "State");
            System.out.println("Server name: " + name + ". Server state: "
                    + state);
        }
    }

    public static void main(String[] args) throws Exception {
/*String hostname = args[0];
String portString = args[1];
String username = args[2];
String password = args[3];
*/
        String hostname = "localhost";
        String portString = "7001";
        String username = "weblogic";
        String password = "623582fade";

        WebLogicServerRuntime s = new WebLogicServerRuntime();
        initConnection(hostname, portString, username, password);
//s.printNameAndState();
        s.printTotalThread();
        //s.getJvmRuntime();
        connector.close();
    }
}