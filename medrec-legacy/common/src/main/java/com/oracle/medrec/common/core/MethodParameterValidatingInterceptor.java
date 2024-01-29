package com.oracle.medrec.common.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Interceptor that is mainly for the classes (EJB) acting as entry points. For
 * now, we won't apply it to internal classes.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public class MethodParameterValidatingInterceptor {

    private static final Logger LOGGER = Logger
            .getLogger(MethodParameterValidatingInterceptor.class.getName());

    @EJB
    private MethodParameterValidator methodParameterValidator;

    public void setMethodParameterValidator(
            MethodParameterValidator methodParameterValidator) {
        this.methodParameterValidator = methodParameterValidator;
    }

    @AroundInvoke
    public Object validateParameters(InvocationContext invocationContext)
            throws Exception {
        LOGGER.log(Level.FINER, "Validating method parameters...");
        methodParameterValidator.validateParameters(
                invocationContext.getMethod(),
                invocationContext.getParameters());
        return invocationContext.proceed();
    }
}
