package com.oracle.medrec.common.core;

import junit.framework.JUnit4TestAdapter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * {@link ServiceRegistry} test case.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 * @since Jul 17, 2007
 */
public class ServiceRegistryTestCase {

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ServiceRegistryTestCase.class);
    }

    @Test
    public void verifySingleton() throws Exception {
        ServiceRegistry inst1 = ServiceRegistry.instance();
        ServiceRegistry inst2 = ServiceRegistry.instance();
        assertNotNull(inst1);
        assertSame(inst1, inst2);

        Class cls = Class.forName(ServiceRegistry.class.getName());
        assertNotNull(cls);
        assertEquals(0, cls.getConstructors().length);
    }

    @Test
    public void getService() {
        ServiceRegistry reg = ServiceRegistry.instance();
        Object key = new Object();
        Object service = new Object();
        reg.registerService(key, service);
        assertSame(service, reg.getService(key));
    }

    @Test
    public void getServiceByClass() {
        ServiceRegistry reg = ServiceRegistry.instance();
        FooService anotherService = new FooService();
        reg.registerService(FooService.class, anotherService);
        assertSame(anotherService, reg.getServiceByClass(FooService.class));
    }

    @Test
    public void getNonregisteredService() {
        ServiceRegistry reg = ServiceRegistry.instance();

        try {
            reg.getService("bogus");
            fail();
        } catch (ServiceNotFoundException e) {
            assertEquals("No service is registered with the key: bogus",
                    e.getMessage());
        }

    }

    public static class FooService {

    }
}
