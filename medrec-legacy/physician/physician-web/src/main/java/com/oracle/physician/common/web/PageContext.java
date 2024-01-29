package com.oracle.physician.common.web;

import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * TODO move to common components
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public interface PageContext {

    FacesContext getFacesContext();

    Map<String, Object> getSessionMap();

    Map<String, Object> getRequestMap();

    String getRequestParam(String name);

    void invalidateSession();

    void addGlobalOnlyMessage(String message);

    void addGlobalOnlyErrorMessage(String message);

    void addGlobalOnlyInfoMessage(String message);

    void addGlobalOnlyMessageWithKey(String messageKey, Object... parameters);

    void addGlobalOnlyErrorMessageWithKey(String messageKey,
            Object... parameters);

    void addGlobalOnlyInfoMessageWithKey(String messageKey,
            Object... parameters);

    String getMessage(String messageKey, Object... parameters);

    ClassLoader getWebappClassLoader();
}
