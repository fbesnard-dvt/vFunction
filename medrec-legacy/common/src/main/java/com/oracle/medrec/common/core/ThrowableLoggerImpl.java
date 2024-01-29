package com.oracle.medrec.common.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.oracle.medrec.common.util.ThrowableUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

/**
 * TODO customize logger namespace?
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Interceptors({ MethodParameterValidatingInterceptor.class })
public class ThrowableLoggerImpl implements ThrowableLogger {

    private static final Logger LOGGER = Logger
            .getLogger(ThrowableLoggerImpl.class.getName());

    public void log(Throwable t) {
        LOGGER.log(Level.SEVERE, "Exception occured", t);
        LOGGER.log(Level.SEVERE, ThrowableUtils.getStackTrace(t));
    }
}
