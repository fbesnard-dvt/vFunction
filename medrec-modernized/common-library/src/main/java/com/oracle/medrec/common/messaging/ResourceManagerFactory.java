package com.oracle.medrec.common.messaging;

import com.oracle.medrec.common.naming.NamingClient;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public abstract class ResourceManagerFactory {

    private static ResourceManagerImpl resourceManager;

    public static synchronized ResourceManager getResourceManager(
            int connectionTestPeriod, String connectionFactoryName,
            NamingClient namingClient) {
        if (resourceManager == null) {
            resourceManager = new ResourceManagerImpl();
            resourceManager.setConnectionTestPeriod(connectionTestPeriod);
            resourceManager.setConntectionFactoryName(connectionFactoryName);
            resourceManager.setNamingClient(namingClient);
            resourceManager.init();
        }
        return resourceManager;
    }
}
