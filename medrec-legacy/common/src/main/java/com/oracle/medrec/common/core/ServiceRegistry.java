package com.oracle.medrec.common.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class ServiceRegistry {

    private static final ServiceRegistry INSTANCE = new ServiceRegistry();

    private final Map<Object, Object> services = new ConcurrentHashMap<Object, Object>();

    public static ServiceRegistry instance() {
        return INSTANCE;
    }

    private ServiceRegistry() {
    }

    public void registerService(Object key, Object service) {
        services.put(key, service);
    }

    public Object getService(Object key) {
        Object service = services.get(key);
        if (service == null) {
            throw new ServiceNotFoundException(key);
        } else {
            return service;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getServiceByClass(Class<T> serviceClass) {
        return (T) getService((Object) serviceClass);
    }
}
