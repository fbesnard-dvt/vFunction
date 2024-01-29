package com.oracle.medrec.common.util;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class ServerPropertiesUtils {
    public static String getServerProperty(String key, String defualt) {
        if ("medrecAppServerAddress".equals(key)) {
            return getServerAddress();
        } else if ("physicianAppServerAddress".equals(key)) {
            return "localhost";
        } else if ("medrecAppServerPort".equals(key)) {
            return getServerPort();
        } else if ("physicianAppServerPort".equals(key)) {
            return getServerPort();
        }
        return null;
    }

    private static String getServerPort() {
        Integer listenPort = null;
        try {
            MBeanServer mbeanServer = getMBeanServer();
            ObjectName serverRuntime =  getServerRuntime(mbeanServer);

            listenPort = (Integer) mbeanServer.getAttribute(serverRuntime,
                    "ListenPort");
        } catch (Exception e) {
            System.out
                    .println("Unable to obtain listen address; using default port"
                            + " 7001. : " + e.getMessage());
        }
        if (listenPort != null) {
            System.out.println("Listen port is " + listenPort.toString());
        }
        return listenPort == null ? "7001" : listenPort.toString();
    }
    
    private static String getServerAddress() {
        String address = null;
        try {
            MBeanServer mbeanServer = getMBeanServer();
            ObjectName serverRuntime =  getServerRuntime(mbeanServer);

            address = (String) mbeanServer.getAttribute(serverRuntime,
                    "ListenAddress");
        } catch (Exception e) {
            System.out
                    .println("Unable to obtain listen address; using default address"
                            + " localhost. : " + e.getMessage());
        }
        if (address != null) {
            System.out.println("Address is " + address);
        }
        return address != null &&
                !address.equals("") ?
                address.substring(address.indexOf('/') + 1) : "localhost";
    }
    
    private static MBeanServer getMBeanServer() throws NamingException {
            InitialContext ctx = new InitialContext();
            try {
                return (MBeanServer) ctx
                        .lookup("java:comp/env/jmx/runtime");
            } catch (NamingException e) {
                return (MBeanServer) ctx.lookup("java:comp/jmx/runtime");
            }
    }
    
    private static ObjectName getServerRuntime(MBeanServer mbeanServer) throws Exception {
        String runtimeServiceName = "com.bea:Name=RuntimeService,Type="
                + "weblogic.management.mbeanservers.runtime.RuntimeServiceMBean";

        // Create Objectname for the runtime service
        ObjectName runtimeService = new ObjectName(runtimeServiceName);

        // Get the ObjectName for the ServerRuntimeMBean
        return (ObjectName) mbeanServer.getAttribute(
                runtimeService, "ServerRuntime");
    }
    
}
