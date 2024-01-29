package com.oracle.medrec.common.core;

import java.lang.reflect.Method;

/**
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface MethodParameterValidator {

    void validateParameters(Method method, Object[] params);
}
