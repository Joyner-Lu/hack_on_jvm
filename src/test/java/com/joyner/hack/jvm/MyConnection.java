package com.joyner.hack.jvm;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyConnection {
    private static MBeanServerConnection connection;
    private static JMXConnector connector;
    private static final ObjectName service = null;


    static ExecutorService pool = Executors.newFixedThreadPool(100);
    /*
     * Initialize connection to the Domain Runtime MBean Server.
     */
    public static void initConnection(String hostname, String portString,
                                      String username, String password) throws IOException,
            MalformedURLException {
        String protocol = "t3";
        Integer portInteger = Integer.valueOf(portString);
        int port = portInteger.intValue();
        String jndiroot = "/jndi/";
        String mserver = "weblogic.management.mbeanservers.domainruntime";
        JMXServiceURL serviceURL = new JMXServiceURL(protocol, hostname, port,
                jndiroot + mserver);
        Hashtable h = new Hashtable();
        h.put(Context.SECURITY_PRINCIPAL, username);
        h.put(Context.SECURITY_CREDENTIALS, password);
        h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
                "weblogic.management.remote");
        h.put("jmx.remote.x.request.waiting.timeout", new Long(10000));
        connector = JMXConnectorFactory.connect(serviceURL, h);
        connection = connector.getMBeanServerConnection();
    }
    public static void main(String[] args) throws Exception {
        String hostname = "localhost";
        String portString = "7001";
        String username = "weblogic";
        String password = "623582fade";
        MyConnection c= new MyConnection();
        initConnection(hostname, portString, username, password);


        connector.close();
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
    /*
     * Iterate through ServerRuntimeMBeans and get the name and state
     */

}