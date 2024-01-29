package com.oracle.medrec.common.naming;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.oracle.medrec.common.core.MethodParameterValidatingInterceptor;
import com.oracle.medrec.common.util.ClassUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.naming.InitialContext;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Interceptors({ MethodParameterValidatingInterceptor.class })
public class NamingClientImpl implements NamingClient {

    // TODO For now it could only lookup local JNDI objects.

    private static final Logger LOGGER = Logger
            .getLogger(NamingClientImpl.class.getName());

    private InitialContext context;

    @PostConstruct
    public void init() throws Exception {
        try {
            this.context = new InitialContext();
        } catch (javax.naming.NamingException e) {
            throw new NamingException("Cannot create JNDI context", e);
        }
    }

    @PreDestroy
    public void destroy() {
        if (context != null) {
            try {
                context.close();
            } catch (javax.naming.NamingException e) {
                LOGGER.log(Level.WARNING, "Cannot close JNDI context", e);
            }
        }
    }

    public <T> T lookup(Class<T> clazz, String name) {

        try {
            return ClassUtils.cast(clazz, context.lookup(name));
        } catch (javax.naming.NamingException e) {
            throw new NamingException("Cannot do JNDI lookup", e);
        }
    }
}
