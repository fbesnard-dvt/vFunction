package com.oracle.medrec.common.core;

import java.lang.reflect.Method;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface MethodInvocationCache {

    Object findResult(Method method, Object[] parameters)
            throws ResultNotCachedException;

    void addResult(Method method, Object returnValue, Object[] parameters);

    void invalidateAllResults();

    void invalidateResultsByMethod(Method method);

    void invalidateResultsByMethodAndParameters(Method method,
            Object[] parameters);
}
