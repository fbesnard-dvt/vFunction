package com.oracle.medrec.common.core;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Interceptors({ MethodParameterValidatingInterceptor.class })
public class MethodInvocationCacheImpl implements MethodInvocationCache {

    private static final Logger LOGGER = Logger
            .getLogger(MethodInvocationCacheImpl.class.getName());

    private static final Map<Method, Map<ParameterList, Object>> returnValues = new ConcurrentHashMap<Method, Map<ParameterList, Object>>();

    private static final Object NULL_RESULT = new Object();

    public Object findResult(Method method, Object[] parameters)
            throws ResultNotCachedException {
        Map<ParameterList, Object> parameterLists = returnValues.get(method);
        if (parameterLists == null) {
            throw new ResultNotCachedException();
        }

        Object result = parameterLists.get(new ParameterList(parameters));

        if (result == null) {
            throw new ResultNotCachedException();
        }
        LOGGER.log(Level.FINER, "Found result in cache");
        LOGGER.log(Level.FINER, "Method: " + method);
        LOGGER.log(Level.FINER, "Parameters: " + Arrays.toString(parameters));
        LOGGER.log(Level.FINER, "Result: " + result);
        if (result == NULL_RESULT) {
            return null;
        }
        return result;
    }

    public void addResult(Method method, @Nullable Object result,
            Object[] parameters) {
        Map<ParameterList, Object> parameterLists = returnValues.get(method);
        if (parameterLists == null) {
            parameterLists = new ConcurrentHashMap<ParameterList, Object>();
            returnValues.put(method, parameterLists);
        }
        if (result == null) {
            result = NULL_RESULT;
        }
        parameterLists.put(new ParameterList(parameters), result);
        LOGGER.log(Level.FINER, "Added result to cache");
        LOGGER.log(Level.FINER, "Method: " + method);
        LOGGER.log(Level.FINER, "Parameters: " + Arrays.toString(parameters));
        LOGGER.log(Level.FINER, "Result: " + result);
    }

    public void invalidateAllResults() {
        returnValues.clear();
    }

    public void invalidateResultsByMethod(Method method) {
        returnValues.remove(method);
    }

    public void invalidateResultsByMethodAndParameters(Method method,
            Object[] parameters) {
        Map<ParameterList, Object> parameterLists = returnValues.get(method);
        if (parameterLists != null) {
            parameterLists.remove(new ParameterList(parameters));
        }
    }

    private static class ParameterList {

        private Object[] parameters;

        public ParameterList(Object[] parameters) {
            this.parameters = parameters;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            if (Arrays.equals(parameters, ((ParameterList) o).parameters)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hashCode = 1;
            for (Object parameter : parameters) {
                if (parameter != null) {
                    hashCode = 29 * hashCode + parameter.hashCode();
                }
            }
            return hashCode;
        }
    }
}
