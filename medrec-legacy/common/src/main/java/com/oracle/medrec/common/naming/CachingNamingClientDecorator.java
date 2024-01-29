package com.oracle.medrec.common.naming;

import com.oracle.medrec.common.core.MethodInvocationCachingInterceptor;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

/**
 * Note that if it's used as POJO, cache won't take effect.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CachingNamingClientDecorator implements NamingClient {

    @EJB(beanName = "NamingClientImpl")
    private NamingClient namingClient;

    public void setNamingClient(NamingClient namingClient) {
        this.namingClient = namingClient;
    }

    @Interceptors({ MethodInvocationCachingInterceptor.class })
    public <T> T lookup(Class<T> clazz, String name) {
        return namingClient.lookup(clazz, name);
    }
}
