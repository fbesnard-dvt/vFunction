package com.oracle.medrec.common.web;

/**
 * TODO move to common components
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
public abstract class PageControllerSupport {

    private PageContext pageContext = new PageContextImpl();

    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    protected PageContext getPageContext() {
        return pageContext;
    }
}
