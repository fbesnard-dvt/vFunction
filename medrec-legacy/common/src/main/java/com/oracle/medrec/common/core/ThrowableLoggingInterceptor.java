package com.oracle.medrec.common.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Interceptor that is mainly for application entry points. Before throwing
 * exceptions to outer world, we firstly write them into logs.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class ThrowableLoggingInterceptor {

    private static final Logger LOGGER = Logger
            .getLogger(ThrowableLoggingInterceptor.class.getName());

    @EJB
    private ThrowableLogger throwableLogger;

    public void setExceptionLogger(ThrowableLogger throwableLogger) {
        this.throwableLogger = throwableLogger;
    }

    @AroundInvoke
    public Object logException(InvocationContext invocationContext)
            throws Exception {
        LOGGER.log(Level.FINER, "Checking throwable...");
        try {
            return invocationContext.proceed();
        } catch (Throwable t) {
            throwableLogger.log(t);
            if (t instanceof Exception) {
                throw (Exception) t;
            } else {
                throw (Error) t;
            }
        }
    }
}
