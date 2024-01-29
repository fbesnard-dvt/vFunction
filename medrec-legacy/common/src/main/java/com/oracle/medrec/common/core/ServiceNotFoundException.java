package com.oracle.medrec.common.core;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class ServiceNotFoundException extends SystemException {

    private static final long serialVersionUID = 1L;

    public ServiceNotFoundException(Object key) {
        super("No service is registered with the key: " + key);
    }
}
