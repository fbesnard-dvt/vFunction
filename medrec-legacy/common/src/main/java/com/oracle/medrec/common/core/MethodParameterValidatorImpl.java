package com.oracle.medrec.common.core;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * TODO switch to varargs
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MethodParameterValidatorImpl implements MethodParameterValidator {

    public void validateParameters(Method method, Object[] params) {
        if (params == null) {
            throw new IllegalArgumentException("Param array is null");
        }
        int i = 0;
        for (Object param : params) {
            if (param == null) {
                boolean nullable = false;
                for (Annotation annotation : method.getParameterAnnotations()[i]) {
                    if (Nullable.class == annotation.annotationType()) {
                        nullable = true;
                    }
                }
                if (!nullable) {
                    throw new IllegalArgumentException("The number " + (i + 1)
                            + " parameter of type '"
                            + method.getParameterTypes()[i].getName()
                            + "' in method '"
                            + method.getDeclaringClass().getName() + "."
                            + method.getName() + "()' is null");
                }
            }
            i++;
        }
    }
}
