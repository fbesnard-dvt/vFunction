package com.oracle.physician.service.delegate;

import com.oracle.medrec.common.core.MethodInvocationCache;
import com.oracle.medrec.facade.model.RecordToCreate;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

/**
 * Interceptor that could only be applied to one paticular method.
 * 
 * Actually, typed interceptor would be much better... The use of interceptor
 * for such logic isn't necessarily needed
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Interceptor
@RecordCache
public class RecordCacheUpdateInterceptor {

    private static final Method GET_RECORD_SUMMARY_METHOD;

    static {
        try {
            GET_RECORD_SUMMARY_METHOD = RecordServiceDelegate.class.getMethod(
                    "getRecordSummaryByPatientId", Long.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
    }

    @Inject
    private MethodInvocationCache methodInvocationCache;

    public void setMethodInvocationCache(
            MethodInvocationCache methodInvocationCache) {
        this.methodInvocationCache = methodInvocationCache;
    }

    @AroundInvoke
    public Object createRecord(InvocationContext invocationContext)
            throws Exception {
        RecordToCreate record = (RecordToCreate) invocationContext
                .getParameters()[0];
        Object result = invocationContext.proceed();
        methodInvocationCache.invalidateResultsByMethodAndParameters(
                GET_RECORD_SUMMARY_METHOD,
                new Object[] { record.getPatientId() });
        return result;
    }

}
