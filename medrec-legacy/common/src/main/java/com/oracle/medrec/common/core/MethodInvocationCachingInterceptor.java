package com.oracle.medrec.common.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Now all the instances of a intecepted class just share the same cache, so
 * it's suitable for stateless objects.
 * 
 * TODO more flexible
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Interceptor
@MethodCache
public class MethodInvocationCachingInterceptor {

    private static final Logger LOGGER = Logger
            .getLogger(MethodInvocationCachingInterceptor.class.getName());

    @Inject
    private MethodInvocationCache methodInvocationCache;

    public void setMethodReturnValueCache(
            MethodInvocationCache methodInvocationCache) {
        this.methodInvocationCache = methodInvocationCache;
    }

    @AroundInvoke
    public Object checkInCache(InvocationContext invocationContext)
            throws Exception {
        LOGGER.log(Level.FINER, "Checking method invocation cache...");
        Object result = null;
        try {
            result = methodInvocationCache.findResult(
                    invocationContext.getMethod(),
                    invocationContext.getParameters());
            return result;
        } catch (ResultNotCachedException e) {
        }

        // For now never cache exception
        result = invocationContext.proceed();
        methodInvocationCache.addResult(invocationContext.getMethod(), result,
                invocationContext.getParameters());
        return result;
    }
}
